package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;

/** get_crafting_grid：读当前打开的合成栏（玩家 2x2 或工作台 3x3）及产物。 */
public class CraftingGridTool implements CompassTool {
    @Override
    public String name() {
        return "get_crafting_grid";
    }

    @Override
    public String description() {
        return "获取玩家当前打开的合成栏内容：可能是背包的 2x2 合成格，或工作台的 3x3 合成格，含摆放布局与产物。";
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
        ScreenHandler h = mc.player.currentScreenHandler;
        int side;
        if (h instanceof CraftingScreenHandler) {
            side = 3;
        } else if (h instanceof PlayerScreenHandler) {
            side = 2;
        } else {
            return "当前没有打开合成界面（请打开背包或工作台）。";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(side).append('x').append(side).append(" 合成格布局:\n");
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                int slotIndex = 1 + row * side + col; // slot 0 是产物，1.. 为输入
                ItemStack s = h.getSlot(slotIndex).getStack();
                sb.append('[').append(s.isEmpty() ? "空" : s.getName().getString()).append(']');
            }
            sb.append('\n');
        }
        ItemStack result = h.getSlot(0).getStack();
        sb.append("产物: ").append(result.isEmpty() ? "无（当前摆放无法合成）"
                : (result.getName().getString() + " x" + result.getCount()));
        return sb.toString();
    }
}
