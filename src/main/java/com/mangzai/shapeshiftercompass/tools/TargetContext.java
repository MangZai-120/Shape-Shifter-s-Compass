package com.mangzai.shapeshiftercompass.tools;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.hit.*;
import com.mangzai.shapeshiftercompass.mixin.client.HandledScreenAccessor;

/** An explicit capture on opening Compass; no world scanning and no polling. */
public final class TargetContext {
    private static Object world;
    private static java.util.UUID player;
    private static ItemStack item = ItemStack.EMPTY;
    private static String report = "尚未选取目标。请对准目标或把鼠标放在物品上，再打开罗盘。";
    private static long tick;
    private TargetContext() {}
    public static void capture(MinecraftClient mc) {
        world = mc.world; player = mc.player == null ? null : mc.player.getUuid();
        tick = mc.world == null ? 0 : mc.world.getTime(); item = ItemStack.EMPTY;
        report = "当前没有指向目标。";
        if (mc.player == null || mc.world == null) return;
        if (mc.currentScreen instanceof HandledScreen<?> screen) {
            var slot = ((HandledScreenAccessor) screen).compassFocusedSlot();
            if (slot != null && slot.hasStack()) {
                item = slot.getStack().copy(); report = "打开罗盘时鼠标悬停的物品：" + describe(item); return;
            }
        }
        if (mc.crosshairTarget instanceof BlockHitResult hit && hit.getType() == HitResult.Type.BLOCK) {
            var state = mc.world.getBlockState(hit.getBlockPos());
            item = new ItemStack(state.getBlock());
            report = "打开罗盘时指向的方块：" + state.getBlock().getName().getString() + " ["
                    + Registries.BLOCK.getId(state.getBlock()) + "]\n坐标：" + hit.getBlockPos().toShortString()
                    + "；维度：" + mc.world.getRegistryKey().getValue();
        } else if (mc.crosshairTarget instanceof EntityHitResult hit) {
            var entity = hit.getEntity();
            report = "打开罗盘时指向的实体：" + entity.getName().getString() + " [" + Registries.ENTITY_TYPE.getId(entity.getType()) + "]";
            if (entity instanceof net.minecraft.entity.ItemEntity dropped) item = dropped.getStack().copy();
            if (entity instanceof net.minecraft.entity.LivingEntity living) report += "\n可见生命值：" + living.getHealth() + "/" + living.getMaxHealth();
        } else if (!mc.player.getMainHandStack().isEmpty()) {
            item = mc.player.getMainHandStack().copy(); report = "当前无指向目标，已选取打开罗盘时的主手物品：" + describe(item);
        }
    }
    private static boolean valid() {
        var mc = MinecraftClient.getInstance();
        return world != null && mc.world == world && mc.player != null && mc.player.getUuid().equals(player);
    }
    public static ItemStack item() { return valid() ? item.copy() : ItemStack.EMPTY; }
    public static String report() {
        if (!valid()) return "目标记录已失效。请在当前世界重新选取目标后打开罗盘。";
        long elapsed = Math.max(0, MinecraftClient.getInstance().world.getTime() - tick);
        return report + "\n记录于打开罗盘时，距今约 " + elapsed / 20 + " 秒；没有读取远处或未加载区域。";
    }
    public static String describe(ItemStack stack) {
        net.minecraft.nbt.NbtCompound visible=new net.minecraft.nbt.NbtCompound();
        var custom=stack.getOrDefault(net.minecraft.component.DataComponentTypes.CUSTOM_DATA,
                net.minecraft.component.type.NbtComponent.DEFAULT).copyNbt();
        for(String key:java.util.List.of("Spell","Level","CastLevel","Uses")) {
            var value=custom.get(key); if(value!=null) visible.put(key,value.copy());
        }
        var potion=stack.get(net.minecraft.component.DataComponentTypes.POTION_CONTENTS);
        if(potion!=null) potion.potion().ifPresent(entry -> visible.putString("Potion",
                String.valueOf(Registries.POTION.getId(entry.value()))));
        // Do not export RuneScheme slots, written-book pages or arbitrary entity/private NBT.
        String data=visible.isEmpty()?"":visible.toString();
        return stack.getName().getString() + " [" + Registries.ITEM.getId(stack.getItem()) + "] ×" + stack.getCount()
                + (data.isEmpty() ? "" : "\n物品数据：" + data.substring(0, Math.min(1200, data.length())));
    }
}
