package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

/** get_inventory：读玩家背包（含快捷栏共 36 格）。 */
public class InventoryTool implements CompassTool {
    @Override
    public String name() {
        return "get_inventory";
    }

    @Override
    public String description() {
        return "获取玩家当前背包（含快捷栏，共 36 格）里的所有物品及数量。";
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
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) {
            return "玩家当前不在游戏世界中。";
        }
        var main = mc.player.getInventory().main;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < main.size(); i++) {
            ItemStack s = main.get(i);
            if (s.isEmpty()) {
                continue;
            }
            String where = i < 9 ? ("快捷栏" + (i + 1)) : "背包";
            sb.append(where).append(": ").append(s.getName().getString())
                    .append(" x").append(s.getCount()).append('\n');
            count++;
        }
        return count == 0 ? "背包是空的。" : sb.toString();
    }
}
