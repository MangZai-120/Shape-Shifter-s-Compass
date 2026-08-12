package com.mangzai.shapeshiftercompass.tools;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射封装《幻形者诅咒》形态 / Apoli 资源读取，避免编译期硬依赖（未装时安全降级）。
 * 形态复刻 FormUtils.getPlayerForm；资源复刻附属 ClientResourceCache 的客户端读法
 * （遍历 PowerHolderComponent 的 VariableIntPower）。
 */
public final class SscBridge {
    private SscBridge() {}

    public static boolean formAvailable() {
        return FabricLoader.getInstance().isModLoaded("shape-shifter-curse");
    }

    public static boolean apoliAvailable() {
        return FabricLoader.getInstance().isModLoaded("apoli");
    }

    /** 当前形态 ID（如 shape-shifter-curse:xxx），失败返回 null。 */
    public static String getCurrentFormId(PlayerEntity player) {
        try {
            Class<?> fu = Class.forName("net.onixary.shapeShifterCurseFabric.player_form.utils.FormUtils");
            Object form = fu.getMethod("getPlayerForm", PlayerEntity.class).invoke(null, player);
            if (form == null) {
                return null;
            }
            Object id = form.getClass().getMethod("getFormID").invoke(form);
            return id == null ? null : id.toString();
        } catch (Throwable t) {
            return null;
        }
    }

    /** 玩家所有 Apoli VariableIntPower 资源（资源ID → [当前值, 最大值]）；失败返回空 map。 */
    public static Map<String, int[]> getResources(PlayerEntity player) {
        Map<String, int[]> out = new LinkedHashMap<>();
        try {
            Class<?> phc = Class.forName("io.github.apace100.apoli.component.PowerHolderComponent");
            Object key = phc.getField("KEY").get(null);
            Object comp = key.getClass().getMethod("get", Object.class).invoke(key, player);
            Class<?> vip = Class.forName("io.github.apace100.apoli.power.VariableIntPower");
            Object listObj = comp.getClass().getMethod("getPowers", Class.class).invoke(comp, vip);
            for (Object p : (List<?>) listObj) {
                Object type = p.getClass().getMethod("getType").invoke(p);
                Object id = type.getClass().getMethod("getIdentifier").invoke(type);
                int value = (int) p.getClass().getMethod("getValue").invoke(p);
                int max = (int) p.getClass().getMethod("getMax").invoke(p);
                out.put(String.valueOf(id), new int[]{value, max});
            }
        } catch (Throwable ignored) {
            // Apoli 未就绪或 API 变动时静默降级
        }
        return out;
    }

    /**
     * 应用当前形态的 apoli:modify_food power，计算某食物经形态修正后的真实回复量。
     * @param player 玩家（用于读取其持有的 ModifyFoodPower）
     * @param stack  食物物品堆（用于 doesApply 判定 item_condition）
     * @param baseHunger 食物基础饥饿值
     * @param baseSatMod 食物基础饱和系数
     * @return [修正后饥饿值, 修正后饱和系数, 是否被形态设为可饱腹时吃, 备注]; Apoli 未就绪时返回原值。
     */
    public static Object[] applyFormFoodModifiers(PlayerEntity player, net.minecraft.item.ItemStack stack,
                                                  int baseHunger, float baseSatMod) {
        String note = "";
        boolean alwaysEdible = false;
        double hunger = baseHunger;
        double satMod = baseSatMod;
        try {
            Class<?> phcClass = Class.forName("io.github.apace100.apoli.component.PowerHolderComponent");
            Object key = phcClass.getField("KEY").get(null);
            Object comp = key.getClass().getMethod("get", Object.class).invoke(key, player);
            Class<?> mfp = Class.forName("io.github.apace100.apoli.power.ModifyFoodPower");
            Object listObj = comp.getClass().getMethod("getPowers", Class.class).invoke(comp, mfp);
            java.util.List<?> powers = (java.util.List<?>) listObj;
            boolean anyApplied = false;
            for (Object power : powers) {
                // doesApply(ItemStack) 判断该 power 是否对此食物生效
                boolean does = (boolean) power.getClass().getMethod("doesApply", net.minecraft.item.ItemStack.class)
                        .invoke(power, stack);
                if (!does) {
                    continue;
                }
                anyApplied = true;
                // getFoodModifiers() → List<Modifier>，逐个 apply 到 hunger
                Object foodMods = power.getClass().getMethod("getFoodModifiers").invoke(power);
                for (Object mod : (java.util.List<?>) foodMods) {
                    hunger = (double) mod.getClass().getMethod("apply", net.minecraft.entity.Entity.class, double.class)
                            .invoke(mod, player, hunger);
                }
                // getSaturationModifiers() → 逐个 apply 到 satMod
                Object satMods = power.getClass().getMethod("getSaturationModifiers").invoke(power);
                for (Object mod : (java.util.List<?>) satMods) {
                    satMod = (double) mod.getClass().getMethod("apply", net.minecraft.entity.Entity.class, double.class)
                            .invoke(mod, player, satMod);
                }
                if ((boolean) power.getClass().getMethod("doesMakeAlwaysEdible").invoke(power)) {
                    alwaysEdible = true;
                }
            }
            if (anyApplied) {
                note = "（已按当前形态的 modify_food 加成修正）";
            }
        } catch (Throwable ignored) {
            // Apoli 未就绪或 API 变动：返回原值
        }
        return new Object[]{hunger, satMod, alwaysEdible, note};
    }
}
