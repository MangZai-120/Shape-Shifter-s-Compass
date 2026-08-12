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
}
