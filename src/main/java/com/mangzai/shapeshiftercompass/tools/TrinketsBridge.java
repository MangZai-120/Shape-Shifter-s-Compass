package com.mangzai.shapeshiftercompass.tools;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 反射封装 Trinkets 读取，避免对 Trinkets 的编译期硬依赖（未装时安全降级）。
 * 复刻附属 TrinketUtils 的调用方式：TrinketsApi.getTrinketComponent(player).getAllEquipped()。
 */
public final class TrinketsBridge {
    private TrinketsBridge() {}

    public static boolean available() {
        return FabricLoader.getInstance().isModLoaded("trinkets");
    }

    /** 已装备饰品的 ItemStack 列表；反射异常返回 null，无饰品返回空列表。 */
    public static List<ItemStack> getEquipped(PlayerEntity player) {
        try {
            Class<?> api = Class.forName("dev.emi.trinkets.api.TrinketsApi");
            Object opt = api.getMethod("getTrinketComponent", LivingEntity.class).invoke(null, player);
            if (!(opt instanceof Optional<?> optional) || optional.isEmpty()) {
                return Collections.emptyList();
            }
            Object comp = optional.get();
            Object listObj = comp.getClass().getMethod("getAllEquipped").invoke(comp);
            List<ItemStack> result = new ArrayList<>();
            for (Object pair : (List<?>) listObj) {
                Object stack = pair.getClass().getMethod("getRight").invoke(pair);
                if (stack instanceof ItemStack is && !is.isEmpty()) {
                    result.add(is);
                }
            }
            return result;
        } catch (Throwable t) {
            return null;
        }
    }
}
