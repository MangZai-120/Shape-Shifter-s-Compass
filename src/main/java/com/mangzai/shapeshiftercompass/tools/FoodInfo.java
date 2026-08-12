package com.mangzai.shapeshiftercompass.tools;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import com.mojang.datafixers.util.Pair;

import java.util.List;

/**
 * 食物数值解析器：参照 AppleSkin 的算法，把食物的「饥饿值 / 饱和度回复量 / 是否肉类 /
 * 能否在饱腹时吃 / 现在能否进食 / 附带状态效果」整理成 AI 可读的中文串。
 *
 * 核心公式（AppleSkin FoodValues.getSaturationIncrement）：
 *   饱和度回复量 = hunger × saturationModifier × 2
 * 实际吃到肚子里的饱和度受「饱和度 ≤ 饥饿值」上限约束（HungerManager.add 逻辑）。
 *
 * 这样 AI 在回答「使魔吃金胡萝卜有用吗 / 哪种食物更划算」时就有精确依据，
 * 而不是凭印象说「可以吃」。
 */
public final class FoodInfo {

    private FoodInfo() {}

    /** 若 stack 是食物，返回一段「(食物 饥饿+X 饱和+Y …)」附加说明；否则返回空串。 */
    public static String describeIfFood(ItemStack stack, PlayerEntity player) {
        if (stack == null || stack.isEmpty()) {
            return "";
        }
        return describeItem(stack.getItem(), player);
    }

    /**
     * 按物品直接解析食物数值（不依赖背包是否拥有该物品），从游戏注册表数据读取真实 FoodComponent。
     * player 为 null 时跳过「现在能否吃」的判断（仅查询用途）。
     * 若该物品不是食物，返回空串。
     */
    public static String describeItem(Item item, PlayerEntity player) {
        if (item == null || !item.isFood()) {
            return "";
        }
        FoodComponent food = item.getFoodComponent();
        if (food == null) {
            return "";
        }
        int baseHunger = food.getHunger();
        float baseSatMod = food.getSaturationModifier();

        // 套用当前形态的 apoli:modify_food 修正（素食/肉食/SP形态饮食加成等），得到真实回复量
        double hunger = baseHunger;
        double satMod = baseSatMod;
        boolean formAlwaysEdible = food.isAlwaysEdible();
        String formNote = "";
        if (player != null && com.mangzai.shapeshiftercompass.tools.SscBridge.apoliAvailable()) {
            try {
                net.minecraft.item.ItemStack probe = new net.minecraft.item.ItemStack(item);
                Object[] res = com.mangzai.shapeshiftercompass.tools.SscBridge
                        .applyFormFoodModifiers(player, probe, baseHunger, baseSatMod);
                hunger = (double) res[0];
                satMod = (double) res[1];
                formAlwaysEdible = formAlwaysEdible || (boolean) res[2];
                formNote = (String) res[3];
            } catch (Throwable ignored) {
            }
        }
        // 饱和度回复下限为 0（形态可能设为 set_total 0 让食物白吃）
        if (hunger < 0) hunger = 0;
        if (satMod < 0) satMod = 0;
        // AppleSkin 公式：饱和度回复量 = hunger * saturationModifier * 2
        double satGain = hunger * satMod * 2f;

        StringBuilder sb = new StringBuilder();
        sb.append("[食物] 饥饿值 +").append((int) Math.round(hunger))
                .append("，饱和度回复 +").append(String.format("%.1f", satGain))
                .append("（饱和系数 ").append(String.format("%.2f", satMod)).append("）");
        if (hunger == 0 && satMod == 0 && baseHunger > 0) {
            sb.append("，⚠当前形态吃它等于白吃（被 modify_food 清零）");
        } else if (formNote != null && !formNote.isEmpty()) {
            sb.append(formNote);
        }
        if (food.isMeat()) {
            sb.append("，肉类");
        }
        if (food.isSnack()) {
            sb.append("，零食");
        }
        if (formAlwaysEdible) {
            sb.append("，可饱腹时食用");
        }
        // 当前能不能吃：alwaysEdible 任何时候都能吃；否则需饥饿未满（HungerManager.isNotFull）
        if (player != null) {
            boolean canEatNow = formAlwaysEdible || player.getHungerManager().isNotFull();
            if (!canEatNow) {
                sb.append("，⚠现在不饿吃了浪费");
            }
        }
        // 附带状态效果（腐肉/蜘蛛眼的饥饿、金苹果的生命恢复等）
        List<Pair<StatusEffectInstance, Float>> effects = food.getStatusEffects();
        if (effects != null && !effects.isEmpty()) {
            sb.append("，效果: ");
            for (int i = 0; i < effects.size(); i++) {
                Pair<StatusEffectInstance, Float> pair = effects.get(i);
                StatusEffectInstance eff = pair.getFirst();
                if (eff != null) {
                    Identifier id = Registries.STATUS_EFFECT.getId(eff.getEffectType());
                    String idStr = id == null ? "unknown" : id.toString();
                    int dur = eff.getDuration();
                    String durStr = (dur < 0 || dur > 1_000_000) ? "无限" : (dur / 20 + "秒");
                    sb.append(idStr).append("(等级").append(eff.getAmplifier() + 1)
                            .append(",").append(durStr)
                            .append(",概率").append((int)(pair.getSecond() * 100)).append("%)");
                    if (i < effects.size() - 1) {
                        sb.append(";");
                    }
                }
            }
        }
        return sb.toString();
    }
}
