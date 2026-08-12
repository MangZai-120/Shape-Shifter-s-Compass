package com.mangzai.shapeshiftercompass.ai;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import com.mangzai.shapeshiftercompass.config.CompassConfig;
import com.mangzai.shapeshiftercompass.tools.ToolRegistry;
import net.minecraft.client.MinecraftClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * OpenAI 兼容 Chat Completions 调用（Phase 1：非流式）。
 * 全程异步（不阻塞渲染线程），回调统一切回客户端主线程执行。
 */
public class AiClient {
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    /**
     * 异步发送对话。
     * @param onOk  收到回复文本（主线程回调）
     * @param onErr 出错时收到错误信息或 "no_key"（主线程回调）
     */
    private static final int MAX_TOOL_ROUNDS = 8;
    /** 当前进行中的请求（供取消生成）。 */
    private static volatile CompletableFuture<?> currentFuture;
    /** 取消标志：置 true 后正在递归的 doRound 不再发下一轮。 */
    private static volatile boolean cancelled = false;

    public static void sendChat(List<ChatMessage> messages, Consumer<String> onOk, Consumer<String> onErr) {
        CompassConfig cfg = CompassConfig.get();
        if (!cfg.hasKey()) {
            onErr.accept("no_key");
            return;
        }
        cancelled = false;
        CompassState.setBusy(true);
        JsonArray msgs = new JsonArray();
        // 运行时注入玩家当前游戏语言，供 AI 决定回复语言
        JsonObject langMsg = new JsonObject();
        langMsg.addProperty("role", "system");
        langMsg.addProperty("content", "The player's current Minecraft game language code is \""
                + currentGameLanguage() + "\". Follow the [Language] rule in your system instructions.");
        msgs.add(langMsg);
        // 运行时注入当前作弊开关 + 真实 op 权限状态，避免 AI 在作弊关闭/无权限时仍生成作弊指令
        JsonObject cheatMsg = new JsonObject();
        cheatMsg.addProperty("role", "system");
        cheatMsg.addProperty("content", cheatStatusPrompt(cfg));
        msgs.add(cheatMsg);
        for (ChatMessage m : messages) {
            JsonObject o = new JsonObject();
            o.addProperty("role", m.role);
            o.addProperty("content", m.content);
            msgs.add(o);
        }
        doRound(cfg, msgs, 0, onOk, onErr);
    }

    private static String currentGameLanguage() {
        try {
            String lang = MinecraftClient.getInstance().options.language;
            return lang == null ? "en_us" : lang;
        } catch (Exception e) {
            return "en_us";
        }
    }

    /** 依据「作弊开关 + 真实 op 权限」生成实时状态提示，每轮注入，让 AI 按真实环境决定能否作弊。 */
    private static String cheatStatusPrompt(CompassConfig cfg) {
        boolean cheatOn = cfg.cheatEnabled;
        boolean op = CheatGuard.isOp();
        if (cheatOn && op) {
            return "[Cheat status] Cheat mode is currently ENABLED and the player has OP permission. "
                    + "You MAY use get_seed, locate_structure and run_command when it genuinely helps the player.";
        }
        String reason;
        if (!cheatOn && !op) reason = "cheat mode is turned OFF and the player does NOT have OP permission";
        else if (!cheatOn) reason = "cheat mode is turned OFF";
        else reason = "the player does NOT have OP permission";
        return "[Cheat status] Cheat mode is currently UNAVAILABLE because " + reason + ". "
                + "You MUST NOT call run_command, get_seed or locate_structure, and you MUST NOT write out or suggest any "
                + "cheat command for the player to run. If the player asks for something that needs cheats, politely explain "
                + "that they must first enable Cheat mode in the settings and have OP permission.";
    }

    /** Function Calling 一轮：请求→若 AI 要调工具则主线程本地执行并回传→递归下一轮，直到得到最终回复。 */
    private static void doRound(CompassConfig cfg, JsonArray msgs, int depth,
                               Consumer<String> onOk, Consumer<String> onErr) {
        JsonObject body = new JsonObject();
        body.addProperty("model", cfg.model);
        body.addProperty("temperature", cfg.temperature);
        body.addProperty("max_tokens", cfg.maxTokens);
        body.add("messages", msgs);
        if (!ToolRegistry.isEmpty() && depth < MAX_TOOL_ROUNDS) {
            body.add("tools", ToolRegistry.toolsJson());
            body.addProperty("tool_choice", "auto");
        }

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(cfg.endpoint()))
                .timeout(Duration.ofSeconds(60))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + cfg.apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString(), StandardCharsets.UTF_8))
                .build();

        HTTP.sendAsync(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .whenComplete((resp, err) -> {
                    MinecraftClient mc = MinecraftClient.getInstance();
                    if (cancelled) {
                        mc.execute(() -> onErr.accept("cancelled"));
                        return;
                    }
                    if (err != null) {
                        CompassState.setBusy(false);
                        mc.execute(() -> onErr.accept(err.getMessage()));
                        return;
                    }
                    try {
                        if (resp.statusCode() / 100 != 2) {
                            CompassState.setBusy(false);
                            mc.execute(() -> onErr.accept("HTTP " + resp.statusCode() + ": " + truncate(resp.body(), 300)));
                            return;
                        }
                        JsonObject json = GSON.fromJson(resp.body(), JsonObject.class);
                        JsonObject message = json.getAsJsonArray("choices").get(0)
                                .getAsJsonObject().getAsJsonObject("message");
                        JsonArray toolCalls = (message.has("tool_calls") && !message.get("tool_calls").isJsonNull())
                                ? message.getAsJsonArray("tool_calls") : null;

                        if (toolCalls != null && toolCalls.size() > 0 && depth < MAX_TOOL_ROUNDS) {
                            msgs.add(message);
                            for (JsonElement tcE : toolCalls) {
                                JsonObject tc = tcE.getAsJsonObject();
                                String id = tc.has("id") ? tc.get("id").getAsString() : "";
                                JsonObject fn = tc.getAsJsonObject("function");
                                String toolName = fn.get("name").getAsString();
                                JsonObject toolArgs;
                                try {
                                    String argStr = fn.has("arguments") ? fn.get("arguments").getAsString() : "{}";
                                    toolArgs = (argStr == null || argStr.isBlank())
                                            ? new JsonObject() : GSON.fromJson(argStr, JsonObject.class);
                                } catch (Exception ex) {
                                    toolArgs = new JsonObject();
                                }
                                String result = runTool(mc, toolName, toolArgs);
                                JsonObject toolMsg = new JsonObject();
                                toolMsg.addProperty("role", "tool");
                                toolMsg.addProperty("tool_call_id", id);
                                toolMsg.addProperty("content", result);
                                msgs.add(toolMsg);
                            }
                            doRound(cfg, msgs, depth + 1, onOk, onErr);
                        } else {
                            JsonElement contentE = message.get("content");
                            String content = (contentE == null || contentE.isJsonNull()) ? "" : contentE.getAsString();
                            CompassState.setBusy(false);
                            mc.execute(() -> onOk.accept(content));
                        }
                    } catch (Exception e) {
                        ShapeShifterCompass.LOGGER.error("Parse AI response failed", e);
                        CompassState.setBusy(false);
                        mc.execute(() -> onErr.accept(e.getMessage()));
                    }
                });
    }

    /** 取消当前生成（若有）：停止递归、尝试中止 HTTP。 */
    public static void cancel() {
        cancelled = true;
        CompassState.setBusy(false);
        CompletableFuture<?> f = currentFuture;
        if (f != null) {
            f.cancel(true);
            currentFuture = null;
        }
    }

    /** 执行工具：async 工具在当前异步线程直接跑（如联网 HTTP，不卡渲染）；其余切主线程读取游戏状态。 */
    private static String runTool(MinecraftClient mc, String toolName, JsonObject toolArgs) {
        com.mangzai.shapeshiftercompass.tools.CompassTool tool = ToolRegistry.get(toolName);
        if (tool != null && tool.async()) {
            return ToolRegistry.execute(toolName, toolArgs);
        }
        try {
            java.util.concurrent.CompletableFuture<String> f = new java.util.concurrent.CompletableFuture<>();
            mc.execute(() -> f.complete(ToolRegistry.execute(toolName, toolArgs)));
            return f.get(30, java.util.concurrent.TimeUnit.SECONDS);
        } catch (Exception e) {
            return "错误：工具执行超时或失败 - " + e.getMessage();
        }
    }

    private static String truncate(String s, int n) {
        if (s == null) {
            return "";
        }
        return s.length() <= n ? s : s.substring(0, n) + "...";
    }
}
