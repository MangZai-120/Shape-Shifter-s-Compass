package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.ai.CheatGuard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作弊模式下用原生 /locate 命令定位最近的结构或生物群系，捕获游戏反馈解析坐标。
 * 需「作弊开启 + op 权限」。异步执行（不卡渲染）：发命令后等待聊天监听器回传反馈。
 */
public class LocateTool implements CompassTool {
    /** 待填充的 /locate 反馈（由聊天监听器 complete）。 */
    public static volatile CompletableFuture<String> pending;
    private static final Pattern COORD = Pattern.compile("(-?\\d+),\\s*(~|-?\\d+),\\s*(-?\\d+)");

    @Override
    public String name() {
        return "locate_structure";
    }

    @Override
    public boolean async() {
        return true;
    }

    @Override
    public String description() {
        return "作弊模式下定位最近的结构或生物群系（如村庄、要塞、远古城市、林地府邸）。仅当开启作弊且有 op 权限时可用。"
                + "type=structure 时 id 为结构（如 minecraft:village）；type=biome 时 id 为群系（如 minecraft:jungle）。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject type = new JsonObject();
        type.addProperty("type", "string");
        type.addProperty("description", "structure 或 biome");
        JsonObject id = new JsonObject();
        id.addProperty("type", "string");
        id.addProperty("description", "结构或群系 id，如 minecraft:village 或 minecraft:jungle");
        JsonObject props = new JsonObject();
        props.add("type", type);
        props.add("id", id);
        JsonArray req = new JsonArray();
        req.add("type");
        req.add("id");
        JsonObject schema = new JsonObject();
        schema.addProperty("type", "object");
        schema.add("properties", props);
        schema.add("required", req);
        return schema;
    }

    @Override
    public String execute(JsonObject args) {
        if (!CheatGuard.isCheatAllowed()) {
            return "拒绝：作弊功能未开启或你没有 op 权限。此限制由客户端强制校验，无法通过对话绕过。";
        }
        String type = args.has("type") ? args.get("type").getAsString() : "structure";
        String id = args.has("id") ? args.get("id").getAsString() : "";
        if (id.isEmpty()) {
            return "错误：id 为空";
        }
        String sub = "biome".equals(type) ? "biome" : "structure";
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.player.networkHandler == null) {
            return "错误：玩家未在世界中";
        }
        CompletableFuture<String> f = new CompletableFuture<>();
        pending = f;
        final String cmd = "locate " + sub + " " + id;
        // 开启命令反馈抑制窗口（/locate 反馈较慢，窗口自动延长到 6.5 秒）；坐标仍由 onGameMessage 捕获
        com.mangzai.shapeshiftercompass.ai.CommandFeedbackSuppressor.startSuppression("locate");
        mc.execute(() -> mc.player.networkHandler.sendChatCommand(cmd));
        try {
            String feedback = f.get(6, TimeUnit.SECONDS);
            Matcher m = COORD.matcher(feedback);
            if (m.find()) {
                return "最近的 " + id + " 位于坐标 [" + m.group(1) + ", " + m.group(2) + ", " + m.group(3)
                        + "]。原始反馈：" + feedback;
            }
            return "游戏反馈：" + feedback;
        } catch (Exception e) {
            return "未能在 6 秒内获得 /locate 结果（可能该结构在附近不存在、id 拼写错误或距离过远）。";
        } finally {
            pending = null;
        }
    }

    /** 聊天监听器调用：把 /locate 的反馈文本交给等待中的工具。 */
    public static void onGameMessage(Text message) {
        CompletableFuture<String> f = pending;
        if (f == null || f.isDone()) {
            return;
        }
        String s = message.getString();
        boolean looksLikeLocate = COORD.matcher(s).find()
                || s.contains("找不到") || s.toLowerCase().contains("could not find")
                || s.contains("方块") || s.toLowerCase().contains("blocks away")
                || s.contains("最近") || s.toLowerCase().contains("nearest");
        if (looksLikeLocate) {
            f.complete(s);
        }
    }
}
