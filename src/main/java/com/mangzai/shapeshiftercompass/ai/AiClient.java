package com.mangzai.shapeshiftercompass.ai;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import com.mangzai.shapeshiftercompass.config.CompassConfig;
import com.mangzai.shapeshiftercompass.tools.ToolRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

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
    /** HTTP 层最大重试次数（5xx / 429 / 408 / 网络异常）。 */
    private static final int MAX_API_RETRIES = 2;
    /** 每次 HTTP 层重试的退避间隔（毫秒），避免连打。 */
    private static final long API_RETRY_DELAY_MS = 800L;
    /** 无效回复（乱码/工具调用残片/空回复但带工具意图）的「换方式重答」最大次数，防止无限循环。 */
    private static final int MAX_MEANING_RETRIES = 2;
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
        // 运行时注入玩家更正记忆（联网核对后仍坚持的更正，跨对话持久），供 AI 优先采用
        String memBlock = com.mangzai.shapeshiftercompass.memory.MemoryStore.promptBlock();
        if (memBlock != null) {
            JsonObject memMsg = new JsonObject();
            memMsg.addProperty("role", "system");
            memMsg.addProperty("content", memBlock);
            msgs.add(memMsg);
        }
        for (ChatMessage m : messages) {
            JsonObject o = new JsonObject();
            o.addProperty("role", m.role);
            o.addProperty("content", m.content);
            msgs.add(o);
        }
        doRound(cfg, msgs, 0, 0, 0, onOk, onErr);
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

    /** Function Calling 一轮：请求→若 AI 要调工具则主线程本地执行并回传→递归下一轮，直到得到最终回复。
     *  apiRetries：HTTP 层已重试次数（与 depth 独立，避免工具轮次和 HTTP 重试混算）；
     *  meaningRetries：对无效回复的「换方式重答」已用次数。 */
    private static void doRound(CompassConfig cfg, JsonArray msgs, int depth, int apiRetries, int meaningRetries,
                               Consumer<String> onOk, Consumer<String> onErr) {
        JsonObject body = new JsonObject();
        body.addProperty("model", cfg.model);
        body.addProperty("temperature", cfg.temperature);
        body.addProperty("max_tokens", cfg.maxTokens);
        body.add("messages", msgs);
        // 思考模式：仅开启时传 reasoning_effort（避免不支持该字段的厂商报 400）
        if (cfg.thinkingEnabled && cfg.reasoningEffort != null && !cfg.reasoningEffort.isBlank()) {
            body.addProperty("reasoning_effort", cfg.reasoningEffort);
        }
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
                        // 网络异常（连不上 / 断线 / 超时）：可重试，最多 MAX_API_RETRIES 次，退避 API_RETRY_DELAY_MS
                        if (apiRetries < MAX_API_RETRIES) {
                            ShapeShifterCompass.LOGGER.warn("[Compass] network error, retry {}/{}: {}",
                                    apiRetries + 1, MAX_API_RETRIES, err.getMessage());
                            scheduleApiRetry(() -> doRound(cfg, msgs, depth, apiRetries + 1, meaningRetries, onOk, onErr));
                            return;
                        }
                        CompassState.setBusy(false);
                        mc.execute(() -> onErr.accept(err.getMessage()));
                        return;
                    }
                    try {
                        if (resp.statusCode() / 100 != 2) {
                            int code = resp.statusCode();
                            // 5xx / 429 限流 / 408 超时 → 可重试；其余（401/403/404/400 参数错等）直接透传
                            boolean retryable = code >= 500 || code == 429 || code == 408;
                            if (retryable && apiRetries < MAX_API_RETRIES) {
                                ShapeShifterCompass.LOGGER.warn("[Compass] HTTP {} retryable, retry {}/{}",
                                        code, apiRetries + 1, MAX_API_RETRIES);
                                scheduleApiRetry(() -> doRound(cfg, msgs, depth, apiRetries + 1, meaningRetries, onOk, onErr));
                                return;
                            }
                            // 重试预算耗尽（或非可重试码）但还有一次「换方式」机会：把错误作为提示塞回 msgs
                            // 让 AI 换更简单的工具/参数再试一轮；这次仍失败才把错误抛给 UI
                            if (apiRetries <= MAX_API_RETRIES) {
                                ShapeShifterCompass.LOGGER.warn(
                                        "[Compass] HTTP {} fallthrough → feed back to AI for alt-approach",
                                        code);
                                JsonObject hint = new JsonObject();
                                hint.addProperty("role", "user");
                                hint.addProperty("content",
                                        "[系统提示] 上游 API 返回错误：HTTP " + code + " " + truncate(resp.body(), 200)
                                        + "。如果你刚才尝试调用某个工具失败，请换一种方式再试（换更简单的工具、精简参数、"
                                        + "或改用两三步小操作代替一步大操作）；如果多次尝试仍失败，请如实告诉玩家上游 API 出错并简述原因。");
                                msgs.add(hint);
                                doRound(cfg, msgs, depth, apiRetries + 1, meaningRetries, onOk, onErr);
                                return;
                            }
                            CompassState.setBusy(false);
                            mc.execute(() -> onErr.accept("HTTP " + code + ": " + truncate(resp.body(), 300)));
                            return;
                        }
                        JsonObject json = GSON.fromJson(resp.body(), JsonObject.class);
                        JsonObject message = json.getAsJsonArray("choices").get(0)
                                .getAsJsonObject().getAsJsonObject("message");
                        JsonArray toolCalls = (message.has("tool_calls") && !message.get("tool_calls").isJsonNull())
                                ? message.getAsJsonArray("tool_calls") : null;
                        // 宽容提取 content：兼容三方变体（分段数组 / 对象包裹 / null），避免结构差异直接抛异常中断
                        String content = extractContent(message.get("content"));
                        // 诊断日志：记录原始 content，便于排查模型返回的工具调用格式问题
                        if (!content.isEmpty()) {
                            ShapeShifterCompass.LOGGER.info("[Compass] AI raw content: {}", truncate(content, 500));
                        }
                        // 诊断：记录响应关键字段，排查支持 function calling 的模型为何未走标准 tool_calls
                        String finishReason = "?";
                        try {
                            JsonObject choice0 = json.getAsJsonArray("choices").get(0).getAsJsonObject();
                            if (choice0.has("finish_reason") && !choice0.get("finish_reason").isJsonNull()) {
                                finishReason = choice0.get("finish_reason").getAsString();
                            }
                            boolean hasRC = message.has("reasoning_content") && !message.get("reasoning_content").isJsonNull();
                            ShapeShifterCompass.LOGGER.info(
                                    "[Compass] resp finish_reason={} tool_calls={} reasoning_content={} content_len={}",
                                    finishReason, (toolCalls != null ? toolCalls.size() : "none"), hasRC, content.length());
                        } catch (Exception ignored) {
                        }

                        // 标准 tool_calls 为空时，尝试解析 DeepSeek 等模型的 DSML 文本格式工具调用
                        // （模型把 <｜｜DSML｜｜tool_calls> 塞进 content，不返回标准 tool_calls 字段）
                        java.util.List<DsmlCall> dsmlCalls = java.util.Collections.emptyList();
                        boolean hasStandardCalls = toolCalls != null && toolCalls.size() > 0 && depth < MAX_TOOL_ROUNDS;
                        if (!hasStandardCalls && depth < MAX_TOOL_ROUNDS) {
                            dsmlCalls = parseDsmlCalls(content);
                        }
                        boolean hasDsmlCalls = !dsmlCalls.isEmpty();

                        if (hasStandardCalls) {
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
                            doRound(cfg, msgs, depth + 1, apiRetries, meaningRetries, onOk, onErr);
                        } else if (hasDsmlCalls) {
                            // DSML 工具调用：assistant 消息保留原 content（含 DSML 标签，供模型上下文），
                            // 执行各工具并把结果以 role=user 形式回传（DSML 模式不依赖 tool_call_id 配对）
                            JsonObject asstMsg = new JsonObject();
                            asstMsg.addProperty("role", "assistant");
                            asstMsg.addProperty("content", content);
                            msgs.add(asstMsg);
                            for (DsmlCall call : dsmlCalls) {
                                String result = runTool(mc, call.name, call.args);
                                JsonObject toolMsg = new JsonObject();
                                toolMsg.addProperty("role", "user");
                                toolMsg.addProperty("content", "[tool result] " + call.name + " -> " + result);
                                msgs.add(toolMsg);
                            }
                            doRound(cfg, msgs, depth + 1, apiRetries, meaningRetries, onOk, onErr);
                        } else {
                            // 普通文字回复：清理残留的工具调用标签/碎片（DSML、裸 XML 等）
                            String cleaned = cleanToolCallLeftovers(content);
                            boolean meaningless = isMeaninglessReply(cleaned);
                            // 无效回复统一恢复通道：畸形工具调用残片、空 content 但模型想调工具、或纯乱码碎片，
                            // 只要还有恢复预算就把原回复放回上下文并追加纠错提示，让模型换标准 function calling
                            // 或直接用自然语言重新作答，而不是立刻把「模型未给出有效回复」抛给玩家。预算耗尽才兜底。
                            if (meaningless && depth < MAX_TOOL_ROUNDS && meaningRetries < MAX_MEANING_RETRIES) {
                                ShapeShifterCompass.LOGGER.warn(
                                        "[Compass] meaningless reply (depth={}, finish={}), recovery retry {}/{}",
                                        depth, finishReason, meaningRetries + 1, MAX_MEANING_RETRIES);
                                JsonObject asstMsg = new JsonObject();
                                asstMsg.addProperty("role", "assistant");
                                // 空 content 直接塞会被部分上游拒绝，用单空格兜底
                                asstMsg.addProperty("content", content.isEmpty() ? " " : content);
                                msgs.add(asstMsg);
                                JsonObject fixMsg = new JsonObject();
                                fixMsg.addProperty("role", "user");
                                fixMsg.addProperty("content",
                                        "[系统提示] 你刚才的回复无效（finish_reason=" + finishReason
                                                + "，内容为空或只是格式错误的工具调用碎片）。如果需要查询游戏信息，"
                                                + "请改用系统提供的标准工具调用功能（function calling / tools 字段）完成操作，"
                                                + "不要在正文里手写任何尖括号标签或命名空间 ID；"
                                                + "如果不需要调用工具，就直接用连贯的自然语言回答玩家的问题。请重新回答一次。");
                                msgs.add(fixMsg);
                                doRound(cfg, msgs, depth + 1, apiRetries, meaningRetries + 1, onOk, onErr);
                                return;
                            }
                            // 恢复预算耗尽仍无有效回复 → 回传友好提示而非残片
                            final String reply = meaningless
                                    ? Text.translatable("ssc_compass.msg.empty_reply").getString()
                                    : cleaned;
                            CompassState.setBusy(false);
                            mc.execute(() -> onOk.accept(reply));
                        }
                    } catch (Exception e) {
                        // 响应结构异常（网关返回非 JSON / 缺 choices 字段 / content 非字符串等）也走可重试通道：
                        // 这类多为上游瞬时抖动或代理错误页，退避后原样重发通常即可恢复
                        ShapeShifterCompass.LOGGER.error("Parse AI response failed", e);
                        if (apiRetries < MAX_API_RETRIES) {
                            scheduleApiRetry(() -> doRound(cfg, msgs, depth, apiRetries + 1, meaningRetries, onOk, onErr));
                            return;
                        }
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

    /** 延迟一小段时间后在 IO 线程重新触发 doRound（HTTP 层重试专用）。取消时 cancelled 标志会让重试自然中止。 */
    private static void scheduleApiRetry(Runnable r) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(API_RETRY_DELAY_MS);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                return;
            }
            if (cancelled) {
                return;
            }
            try {
                r.run();
            } catch (Throwable ex) {
                ShapeShifterCompass.LOGGER.error("[Compass] retry runner threw", ex);
            }
        }, "Compass-ApiRetry");
        t.setDaemon(true);
        t.start();
    }

    /** DSML 工具调用（DeepSeek 等模型的私有文本格式）。 */
    private static final class DsmlCall {
        final String name;
        final JsonObject args;
        DsmlCall(String name, JsonObject args) {
            this.name = name;
            this.args = args;
        }
    }

    /**
     * 解析 DeepSeek 等模型塞进 content 的 DSML 工具调用。
     * 格式：<｜｜DSML｜｜tool_calls> <｜｜DSML｜｜invoke name="xxx"> <｜｜DSML｜｜parameter name="k">v</...></...> </...>
     * 分隔符为全角竖线｜（U+FF5C）×2，非普通竖线。
     * @return 解析出的调用列表；空列表表示该 content 不是 DSML 工具调用。
     */
    private static java.util.List<DsmlCall> parseDsmlCalls(String content) {
        java.util.List<DsmlCall> calls = new java.util.ArrayList<>();
        if (content == null || content.isEmpty()) {
            return calls;
        }
        // DSML 分隔符：全角竖线 ｜｜DSML｜｜
        String SEP = "\uFF5C\uFF5CDSML\uFF5C\uFF5C";
        if (!content.contains(SEP + "tool_calls")) {
            return calls;
        }
        // 匹配每个 <｜｜DSML｜｜invoke name="工具名"> ... </｜｜DSML｜｜invoke>
        String invokeRe = SEP + "invoke\\s+name=\"([^\"]+)\"([\\s\\S]*?)" + SEP + "/invoke";
        java.util.regex.Pattern invokeP = java.util.regex.Pattern.compile(invokeRe);
        // 匹配 invoke 内的 <｜｜DSML｜｜parameter name="参数名">值</...>
        String paramRe = SEP + "parameter\\s+name=\"([^\"]+)\"[^>]*>([\\s\\S]*?)" + SEP + "/parameter";
        java.util.regex.Pattern paramP = java.util.regex.Pattern.compile(paramRe);

        java.util.regex.Matcher invokeM = invokeP.matcher(content);
        while (invokeM.find()) {
            String toolName = invokeM.group(1).trim();
            String invokeBody = invokeM.group(2);
            JsonObject args = new JsonObject();
            java.util.regex.Matcher paramM = paramP.matcher(invokeBody);
            while (paramM.find()) {
                String key = paramM.group(1).trim();
                String val = paramM.group(2).trim();
                args.addProperty(key, val);
            }
            calls.add(new DsmlCall(toolName, args));
        }
        return calls;
    }

    /**
     * 清理 content 里残留的工具调用标签/碎片，避免玩家看到 ＜｜｜DSML｜｜...＞ 或裸的尖括号残片。
     * 覆盖三种情况：① 完整 DSML 标签（含全角竖线分隔符）；② 被中间环节吞掉分隔符后的裸 XML 碎片；
     * ③ 模型把工具调用写成普通 XML 风格标签（invoke/parameter/function_calls 等）。
     */
    private static String cleanToolCallLeftovers(String content) {
        if (content == null || content.isEmpty()) {
            return content;
        }
        String SEP = "\uFF5C\uFF5CDSML\uFF5C\uFF5C";
        String result = content;
        // 1) 删除完整 DSML 块：<｜｜DSML｜｜xxx> ... </｜｜DSML｜｜xxx>
        String dsmlBlock = SEP + "[^>]*>[\\s\\S]*?" + SEP + "/[^>]+>";
        result = java.util.regex.Pattern.compile(dsmlBlock).matcher(result).replaceAll("");
        // 残留的 DSML 单标签
        result = java.util.regex.Pattern.compile(SEP + "[^<>]*>").matcher(result).replaceAll("");
        // 2) 删除常见工具调用的 XML 风格标签（含/不含属性，开/闭/自闭合）：
        //    invoke parameter function_calls tool_calls plugin_call antenna 等
        String tagNames = "invoke|parameter|function_calls|tool_calls|plugin_call|antenna|function|tools";
        result = java.util.regex.Pattern.compile("</?(" + tagNames + ")\\b[^>]*>")
                .matcher(result).replaceAll("");
        // 3) 清理孤立的尖括号残片：< 后紧跟换行/空白/另一个<、孤立的 </ 或 /> 不成对
        result = java.util.regex.Pattern.compile("<\\s*<+").matcher(result).replaceAll("");
        result = java.util.regex.Pattern.compile("</\\s*>").matcher(result).replaceAll("");
        result = java.util.regex.Pattern.compile("<\\s*>").matcher(result).replaceAll("");
        // 3.5) 清理畸形工具调用残片：整行以 < 开头且行内无闭合 >（如模型幻觉的 <ssc_addon debug form）、
        //      以及行尾/串尾孤立的 </ 闭合残片
        result = java.util.regex.Pattern.compile("(?m)^\\s*<[^>\\n]*$").matcher(result).replaceAll("");
        result = java.util.regex.Pattern.compile("</\\s*(?=\\n|$)").matcher(result).replaceAll("");
        // 连续空行压缩，首尾空白去除
        result = java.util.regex.Pattern.compile("\\n{3,}").matcher(result).replaceAll("\n\n");
        return result.trim();
    }

    /**
     * 判断清理后的回复是否无意义（应回传兜底提示）：
     * ① 完全空白或只剩标点/符号；② 破碎的工具调用残片——含命名空间 ID（xxx:yyy）或残留尖括号，
     *    但缺少连贯的中文/英文句子（没有 4 个以上连续汉字或 3 个以上连续英文单词）。
     */
    private static boolean isMeaninglessReply(String s) {
        if (s == null || s.isBlank()) {
            return true;
        }
        // 完全无字母数字 → 无意义
        String letters = s.replaceAll("[\\p{P}\\p{S}\\s<>\"'=/:|]+", "");
        if (letters.isEmpty()) {
            return true;
        }
        // 破碎工具调用残片优先判定：整行以 < 开头的未闭合标签（如 <ssc_addon debug form）或孤立 </ 残片，
        // 且无 4+ 连续汉字 → 判为无意义（优先于英文句子判断，避免“addon debug form”被误当连贯英文）
        boolean brokenToolTag = java.util.regex.Pattern.compile("(?m)^\\s*<[^>\\n]*$").matcher(s).find()
                || java.util.regex.Pattern.compile("</\\s*(?:\\n|$)").matcher(s).find();
        if (brokenToolTag && !java.util.regex.Pattern.compile("[\\u4e00-\\u9fff]{4,}").matcher(s).find()) {
            return true;
        }
        // 是否有连贯句子：4 个以上连续汉字（中日韩统一表意文字）
        boolean hasCjkSentence = java.util.regex.Pattern.compile("[\\u4e00-\\u9fff]{4,}").matcher(s).find();
        // 是否有 3 个以上连续英文单词（每个 ≥2 字母，空格分隔）
        boolean hasEnSentence = java.util.regex.Pattern.compile("[A-Za-z]{2,}(\\s+[A-Za-z]{2,}){2,}").matcher(s).find();
        if (hasCjkSentence || hasEnSentence) {
            return false;
        }
        // 没有连贯句子，但含有命名空间 ID（minecraft:xxx / ssc_addon:xxx）或残留尖括号 → 工具调用残片
        boolean hasNamespaceId = java.util.regex.Pattern.compile("[A-Za-z_][\\w]*:[\\w]+").matcher(s).find();
        boolean hasAngleBracket = s.contains("<") || s.contains(">") || s.contains("</");
        return hasNamespaceId || hasAngleBracket;
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

    /**
     * 宽容提取 message.content：兼容字符串之外的三方变体——
     * ① 数组分段（部分中转/厂商把 content 拆成 [{"type":"text","text":"..."}]）；
     * ② 对象包裹（{"text": "..."} 或 {"content": ...}）；③ null / 缺字段。
     * 避免因结构差异直接抛异常中断对话。
     */
    private static String extractContent(JsonElement contentE) {
        if (contentE == null || contentE.isJsonNull()) {
            return "";
        }
        if (contentE.isJsonPrimitive()) {
            return contentE.getAsString();
        }
        StringBuilder sb = new StringBuilder();
        if (contentE.isJsonArray()) {
            for (JsonElement part : contentE.getAsJsonArray()) {
                sb.append(extractContent(part));
            }
            return sb.toString();
        }
        if (contentE.isJsonObject()) {
            JsonObject o = contentE.getAsJsonObject();
            if (o.has("text") && !o.get("text").isJsonNull() && o.get("text").isJsonPrimitive()) {
                return o.get("text").getAsString();
            }
            if (o.has("content")) {
                return extractContent(o.get("content"));
            }
        }
        return sb.toString();
    }
}
