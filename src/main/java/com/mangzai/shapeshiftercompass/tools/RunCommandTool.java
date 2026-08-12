package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.ai.CheatGuard;
import com.mangzai.shapeshiftercompass.ai.CommandFeedbackSuppressor;
import net.minecraft.client.MinecraftClient;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 作弊模式下执行一条 Minecraft 命令（白名单限制）。需「作弊开启 + op 权限」，Java 层强校验，AI 无法绕过。
 */
public class RunCommandTool implements CompassTool {
    /** 高风险命令黑名单（运维/权限/封禁/服务器控制）；除这些外，作弊模式下其余命令均允许执行。 */
    private static final Set<String> BLACKLIST = Set.of(
            "op", "deop", "ban", "ban-ip", "pardon", "pardon-ip", "banlist", "kick",
            "stop", "save-off", "save-on", "save-all", "reload", "whitelist",
            "setidletimeout", "publish", "perf", "debug", "jfr", "datapack"
    );

    @Override
    public String name() {
        return "run_command";
    }

    @Override
    public boolean async() {
        return true;
    }

    @Override
    public String description() {
        return "在作弊模式下执行一条 Minecraft 命令（不含开头斜杠）。仅当玩家已在设置里开启作弊且拥有 op 权限时可用；"
                + "除少数高风险命令（op/deop/stop/ban/kick/whitelist 等运维命令）被禁止外，其余命令均可执行。"
                + "可用于给玩家或其他玩家发放物品、施加效果、传送、生成实体、执行 SSC/SSCA 指令等。目标可用 @s(自己)/@p/@a/玩家名。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject command = new JsonObject();
        command.addProperty("type", "string");
        command.addProperty("description", "要执行的命令（不含开头斜杠），例如：give @s minecraft:iron_ingot 64");
        JsonObject props = new JsonObject();
        props.add("command", command);
        JsonArray req = new JsonArray();
        req.add("command");
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
        String command = args.has("command") && !args.get("command").isJsonNull()
                ? args.get("command").getAsString().trim() : "";
        if (command.isEmpty()) {
            return "错误：command 为空";
        }
        while (command.startsWith("/")) {
            command = command.substring(1).trim();
        }
        String head = command.split("\\s+")[0].toLowerCase();
        if (BLACKLIST.contains(head)) {
            return "拒绝：命令 '" + head + "' 属于高风险运维/权限/封禁命令，出于安全不予执行。";
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.player.networkHandler == null) {
            return "错误：玩家未在世界中";
        }
        final String cmd = command;
        // 开启命令反馈抑制窗口并追踪结果：反馈仅进后台日志、不刷屏聊天框，同时用于判定命令成败
        CompletableFuture<String> f = CommandFeedbackSuppressor.startSuppressionAndAwait(head);
        mc.execute(() -> mc.player.networkHandler.sendChatCommand(cmd));
        try {
            String fb = f.get(2, TimeUnit.SECONDS);
            if (CommandFeedbackSuppressor.isFailureFeedback(fb)) {
                return "命令执行失败：/" + cmd + " —— 游戏反馈：" + fb;
            }
            return "已执行 /" + cmd + "，游戏反馈：" + fb;
        } catch (Exception e) {
            return "已执行 /" + cmd + "（未在 2 秒内收到明确反馈，通常表示已成功）。";
        }
    }
}
