package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

/** get_equipment：读玩家装备栏（头/胸/腿/脚 + 副手）。 */
public class EquipmentTool implements CompassTool {
    private static final String[] ARMOR_NAMES = {"靴子", "护腿", "胸甲", "头盔"};

    @Override
    public String name() {
        return "get_equipment";
    }

    @Override
    public String description() {
        return "获取玩家当前穿戴的装备：头盔、胸甲、护腿、靴子，以及副手物品。";
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
        PlayerInventory inv = mc.player.getInventory();
        StringBuilder sb = new StringBuilder();
        for (int i = inv.armor.size() - 1; i >= 0; i--) {
            ItemStack s = inv.armor.get(i);
            sb.append(ARMOR_NAMES[i]).append(": ")
                    .append(s.isEmpty() ? "无" : s.getName().getString()).append('\n');
        }
        ItemStack off = mc.player.getOffHandStack();
        sb.append("副手: ").append(off.isEmpty() ? "无" : (off.getName().getString() + " x" + off.getCount()));
        // 副手若是食物，附带 AppleSkin 风格数值（手持食物时尤其有用）
        String offFood = FoodInfo.describeIfFood(off, mc.player);
        if (!offFood.isEmpty()) {
            sb.append('\n').append(offFood);
        }
        // 主手食物也一并报告（玩家常把食物拿在手里准备吃）
        ItemStack mainHand = mc.player.getMainHandStack();
        String mainFood = FoodInfo.describeIfFood(mainHand, mc.player);
        if (!mainFood.isEmpty()) {
            sb.append("\n主手").append(mainFood);
        }
        return sb.toString();
    }
}
