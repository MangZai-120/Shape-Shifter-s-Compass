package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;

import java.util.Map;

/** get_form_status：读玩家当前形态 + 所有 Apoli 资源（技能CD/法力/能量等）。软依赖幻形者诅咒。 */
public class FormStatusTool implements CompassTool {
    @Override
    public String name() {
        return "get_form_status";
    }

    @Override
    public String description() {
        return "获取玩家当前在《幻形者诅咒》中的形态，以及所有 Apoli 资源（技能冷却 CD、法力、能量等）"
                + "的当前值与最大值（单位 tick，20 tick = 1 秒）。需要安装幻形者诅咒。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject p = new JsonObject();
        p.addProperty("type", "object");
        p.add("properties", new JsonObject());
        return p;
    }

    @Override
    public String execute(JsonObject args) {
        if (!SscBridge.formAvailable()) {
            return "未安装《幻形者诅咒》模组，无法读取形态。";
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) {
            return "玩家当前不在游戏世界中。";
        }
        StringBuilder sb = new StringBuilder();
        String form = SscBridge.getCurrentFormId(mc.player);
        sb.append("当前形态: ").append(form == null ? "未知（读取失败）" : form).append('\n');

        if (SscBridge.apoliAvailable()) {
            Map<String, int[]> res = SscBridge.getResources(mc.player);
            if (res.isEmpty()) {
                sb.append("（当前无可读取的 Apoli 资源，或该形态无资源/技能CD）");
            } else {
                sb.append("资源（含技能CD等，单位 tick，20tick=1秒）:\n");
                for (Map.Entry<String, int[]> e : res.entrySet()) {
                    sb.append("  ").append(e.getKey()).append(": ")
                            .append(e.getValue()[0]).append('/').append(e.getValue()[1]).append('\n');
                }
            }
        }
        return sb.toString();
    }
}
