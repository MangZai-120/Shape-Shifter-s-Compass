package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.ai.CheatGuard;
import com.mangzai.shapeshiftercompass.ai.CommandFeedbackSuppressor;
import net.minecraft.client.MinecraftClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 作弊模式下快速修改玩家自身数据：生命上限 / 补满生命 / 补满饱食度 / 经验等级。
 * 需「作弊开启 + op 权限」，Java 层强校验；内部映射到原版命令执行，避免 AI 手动拼命令出错。
 */
public class SetPlayerDataTool implements CompassTool {

    @Override
    public String name() {
        return "set_player_data";
    }

    @Override
    public boolean async() {
        return true;
    }

    @Override
    public String description() {
        return "在作弊模式下快速修改玩家自己的数据。支持：max_health=设置生命上限（需 value），heal=补满生命值，"
                + "food=补满饱食度，xp_level=设置经验等级（需 value）。仅当玩家已开启作弊且拥有 op 权限时可用。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject field = new JsonObject();
        field.addProperty("type", "string");
        JsonArray en = new JsonArray();
        en.add("max_health");
        en.add("heal");
        en.add("food");
        en.add("xp_level");
        field.add("enum", en);
        field.addProperty("description",
                "要修改的字段：max_health=生命上限(需 value)，heal=补满生命，food=补满饱食度，xp_level=经验等级(需 value)");

        JsonObject value = new JsonObject();
        value.addProperty("type", "number");
        value.addProperty("description", "目标数值。max_health 与 xp_level 必填；heal、food 无需填写。");

        JsonObject props = new JsonObject();
        props.add("field", field);
        props.add("value", value);
        JsonArray req = new JsonArray();
        req.add("field");
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
        String field = args.has("field") && !args.get("field").isJsonNull()
                ? args.get("field").getAsString().trim().toLowerCase() : "";
        Double value = (args.has("value") && !args.get("value").isJsonNull())
                ? args.get("value").getAsDouble() : null;

        String cmd;
        String feedback;
        switch (field) {
            case "max_health" -> {
                if (value == null || value <= 0) {
                    return "错误：max_health 需要一个大于 0 的 value（生命上限，1 颗心 = 2 点）。";
                }
                cmd = "attribute @s minecraft:generic.max_health base set " + fmt(value);
                feedback = "已将生命上限设为 " + fmt(value) + "（" + fmt(value / 2) + " 颗心）";
            }
            case "heal" -> {
                // instant_health 恢复量 4<<amplifier 半心，amplifier 20 足以补满任何生命上限
                cmd = "effect give @s minecraft:instant_health 1 20 true";
                feedback = "已补满生命值";
            }
            case "food" -> {
                // saturation 效果每 tick 补 amplifier+1 饱和度并同步补满饥饿值
                cmd = "effect give @s minecraft:saturation 1 20 true";
                feedback = "已补满饱食度";
            }
            case "xp_level" -> {
                if (value == null || value < 0) {
                    return "错误：xp_level 需要一个不小于 0 的 value（经验等级）。";
                }
                int lv = (int) Math.round(value);
                cmd = "xp set @s " + lv + " levels";
                feedback = "已将经验等级设为 " + lv;
            }
            default -> {
                return "错误：不支持的 field '" + field + "'（可用：max_health / heal / food / xp_level）。";
            }
        }

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.player.networkHandler == null) {
            return "错误：玩家未在世界中";
        }
        final String c = cmd;
        String head = cmd.split("\\s+")[0].toLowerCase();
        // 抑制原版反馈避免刷屏，并追踪结果判定成败
        CompletableFuture<String> f = CommandFeedbackSuppressor.startSuppressionAndAwait(head);
        mc.execute(() -> mc.player.networkHandler.sendChatCommand(c));
        try {
            String fb = f.get(2, TimeUnit.SECONDS);
            if (CommandFeedbackSuppressor.isFailureFeedback(fb)) {
                return "修改失败：" + feedback + " 可能未生效 —— 游戏反馈：" + fb;
            }
        } catch (Exception ignored) {
            // 未在 2 秒内收到明确反馈，通常表示已成功
        }
        return feedback + "（已执行 /" + c + "）";
    }

    /** 数值格式化：整数不带小数点，否则保留原样。 */
    private static String fmt(double v) {
        if (v == Math.floor(v) && !Double.isInfinite(v)) {
            return String.valueOf((long) v);
        }
        return String.valueOf(v);
    }
}
