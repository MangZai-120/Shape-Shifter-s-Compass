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
        return "获取玩家当前在《幻形者诅咒》中的形态、SSC 原生法力，以及 Apoli 资源（技能冷却 CD、法力、能量等）"
                + "的当前值与最大值，支持 SSC 1.9.2 和 1.10.x。只有冷却计时以 tick 表示，法力等是资源点。";
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
        SscBridge.FormStatus form = SscBridge.getFormStatus(mc.player);
        sb.append("SSC 版本: ").append(SscBridge.version()).append('\n');
        sb.append("当前形态: ").append(form == null ? "未知（客户端数据未就绪或读取失败；不能当成人类）"
                : form.name() + " [" + form.id() + "]").append('\n');
        SscBridge.ManaStatus mana = SscBridge.getNativeMana(mc.player);
        if (mana == null) sb.append("SSC 原生法力：读取失败，不能视为零。\n");
        else sb.append("SSC 原生法力: ").append(mana.value()).append('/').append(mana.max())
                .append(" 点，类型: ").append(mana.type()).append('\n');

        if (SscBridge.apoliAvailable()) {
            Map<String, SscBridge.ResourceValue> res = SscBridge.getResources(mc.player);
            if (res == null) {
                sb.append("Apoli 资源读取失败，不能据此判断技能已冷却；请稍后重试并检查日志。");
            } else if (res.isEmpty()) {
                sb.append("当前形态没有 Apoli 变量资源或 CooldownPower；这不代表法术书冷却为零。");
            } else {
                sb.append("Apoli 资源（原始值；法术书 NBT 法力/冷却不在此表中）:\n");
                for (Map.Entry<String, SscBridge.ResourceValue> e : res.entrySet()) {
                    var value = e.getValue();
                    sb.append("  ").append(e.getKey()).append(": ")
                            .append(value.value()).append('/').append(value.max())
                            .append(value.cooldown() ? " tick（剩余/总冷却，20 tick=1秒）" : "（资源原始值，单位由该资源定义）")
                            .append('\n');
                }
            }
        }
        return sb.toString();
    }
}
