package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

import java.util.List;

/** get_trinkets：读玩家 Trinkets 饰品栏（软依赖 Trinkets，未装时提示）。 */
public class TrinketsTool implements CompassTool {
    @Override
    public String name() {
        return "get_trinkets";
    }

    @Override
    public String description() {
        return "获取玩家 Trinkets 饰品栏里已装备的所有饰品。需要安装 Trinkets 模组。";
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
        if (!TrinketsBridge.available()) {
            return "未安装 Trinkets 模组，无法读取饰品栏。";
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) {
            return "玩家当前不在游戏世界中。";
        }
        List<ItemStack> eq = TrinketsBridge.getEquipped(mc.player);
        if (eq == null) {
            return "读取饰品栏失败（Trinkets API 异常）。";
        }
        if (eq.isEmpty()) {
            return "饰品栏是空的。";
        }
        StringBuilder sb = new StringBuilder();
        for (ItemStack s : eq) {
            sb.append(s.getName().getString()).append(" x").append(s.getCount()).append('\n');
        }
        return sb.toString();
    }
}
