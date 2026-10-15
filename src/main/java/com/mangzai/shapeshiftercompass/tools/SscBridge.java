package com.mangzai.shapeshiftercompass.tools;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import com.mangzai.shapeshiftercompass.ShapeShifterCompass;

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
    private static final java.util.Set<String> WARNED = java.util.concurrent.ConcurrentHashMap.newKeySet();

    public record FormStatus(String id, String name) {}
    public record ResourceValue(int value, int max, boolean cooldown) {}
    public record ManaStatus(String type, double value, double max) {}

    private static void warn(String operation, Throwable error) {
        if (WARNED.add(operation)) ShapeShifterCompass.LOGGER.warn("SSC bridge failed to read {}", operation, error);
    }

    public static String version() {
        return FabricLoader.getInstance().getModContainer("shape-shifter-curse")
                .map(mod -> mod.getMetadata().getVersion().getFriendlyString()).orElse("未安装");
    }

    public static boolean formAvailable() {
        return FabricLoader.getInstance().isModLoaded("shape-shifter-curse");
    }

    public static boolean apoliAvailable() {
        return FabricLoader.getInstance().isModLoaded("apoli");
    }

    /** 当前形态 ID（如 shape-shifter-curse:xxx），失败返回 null。 */
    public static String getCurrentFormId(PlayerEntity player) {
        FormStatus status = getFormStatus(player);
        return status == null ? null : status.id();
    }

    public static FormStatus getFormStatus(PlayerEntity player) {
        try {
            Object form = SscReflection.currentForm(player, PlayerEntity.class, Class::forName);
            String id = SscReflection.formId(form);
            if (id == null) return null;
            String name = id;
            try {
                Object text = form.getClass().getMethod("getFormName").invoke(form);
                if (text instanceof Text translated) name = translated.getString();
            } catch (ReflectiveOperationException ignored) {
                // An ID is still useful if a third-party form has no display name.
            }
            return new FormStatus(id, name);
        } catch (ReflectiveOperationException | LinkageError t) {
            warn("form (SSC " + version() + ")", t);
            return null;
        }
    }

    public static ManaStatus getNativeMana(PlayerEntity player) {
        try {
            Class<?> utils = Class.forName("net.onixary.shapeShifterCurseFabric.mana.ManaUtils");
            Object component = utils.getMethod("getManaComponent", PlayerEntity.class).invoke(null, player);
            if (component == null) return null;
            Class<?> type = Class.forName("net.onixary.shapeShifterCurseFabric.mana.ManaComponent");
            Object id = type.getMethod("getManaTypeID").invoke(component);
            double value = ((Number) type.getMethod("getMana").invoke(component)).doubleValue();
            double max = ((Number) type.getMethod("getMaxMana").invoke(component)).doubleValue();
            return new ManaStatus(id == null ? "未启用" : id.toString(), value, max);
        } catch (ReflectiveOperationException | LinkageError error) {
            warn("SSC native mana", error);
            return null;
        }
    }

    /** Apoli variables and actual CooldownPower timers; null means failure, empty means no powers. */
    public static Map<String, ResourceValue> getResources(PlayerEntity player) {
        Map<String, ResourceValue> out = new LinkedHashMap<>();
        try {
            Class<?> phc = Class.forName("io.github.apace100.apoli.component.PowerHolderComponent");
            Object key = phc.getField("KEY").get(null);
            Object comp = SscReflection.component(key, player, Class::forName);
            Class<?> vip = Class.forName("io.github.apace100.apoli.power.VariableIntPower");
            Class<?> power = Class.forName("io.github.apace100.apoli.power.Power");
            Class<?> powerType = Class.forName("io.github.apace100.apoli.power.PowerType");
            Object listObj = phc.getMethod("getPowers", Class.class).invoke(comp, vip);
            for (Object p : (List<?>) listObj) {
                Object type = power.getMethod("getType").invoke(p);
                Object id = powerType.getMethod("getIdentifier").invoke(type);
                int value = (int) vip.getMethod("getValue").invoke(p);
                int max = (int) vip.getMethod("getMax").invoke(p);
                out.put(String.valueOf(id), new ResourceValue(value, max, false));
            }
            Class<?> cd = Class.forName("io.github.apace100.apoli.power.CooldownPower");
            for (Object p : (List<?>) phc.getMethod("getPowers", Class.class).invoke(comp, cd)) {
                Object type = power.getMethod("getType").invoke(p);
                String id = String.valueOf(powerType.getMethod("getIdentifier").invoke(type));
                int remaining = (int) cd.getMethod("getRemainingTicks").invoke(p);
                int duration = (int) cd.getField("cooldownDuration").get(p);
                out.put(id, new ResourceValue(remaining, duration, true));
            }
        } catch (ReflectiveOperationException | LinkageError error) {
            warn("Apoli resources", error);
            return null;
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
            Object comp = SscReflection.component(key, player, Class::forName);
            Class<?> mfp = Class.forName("io.github.apace100.apoli.power.ModifyFoodPower");
            Object listObj = phcClass.getMethod("getPowers", Class.class).invoke(comp, mfp);
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
