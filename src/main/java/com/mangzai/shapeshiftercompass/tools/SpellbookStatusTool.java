package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

/** Supports registry-aware 1.21 books and newer optional spell calculation APIs. */
public final class SpellbookStatusTool implements CompassTool {
    private static final Class<?>[] STACK = {ItemStack.class};
    @Override public String name() { return "get_spellbook_status"; }
    @Override public String description() { return "读取佩戴的月尘魔法书等级、经验、法力、卷轴与同步冷却。接口提供时报告施放等级、实际耗蓝和已知施法障碍；仅有基础耗蓝时明确标注。不施法或更改书。"; }
    @Override public JsonObject parameters() { return ToolArguments.schema(); }
    @Override public String execute(JsonObject args) {
        var mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return "玩家当前不在游戏世界中。";
        if (!FabricLoader.getInstance().isModLoaded("ssc_addon")) return "当前未安装 SSCA，无法读取月尘魔法书。";
        try {
            Object raw = AddonAccess.call("client.SpellcastClient", "getEquippedBook", new Class<?>[0]);
            if (!(raw instanceof ItemStack equipped) || equipped.isEmpty())
                return "没有佩戴月尘魔法书。手持与佩戴是不同位置，请按需要放入对应饰品位。";
            ItemStack book = equipped.copy();
            int level = number("SpellbookData", "getLevel", book);
            int mana = number("SpellbookData", "getMana", book), max = number("SpellbookData", "getMaxMana", book);
            int slots = number("SpellbookData", "getSlotCount", book);
            double experience;
            try { experience = number("SpellbookData", "getExpTen", book) / 10.0; }
            catch (NoSuchMethodException olderBook) { experience = number("SpellbookData", "getExp", book); }
            StringBuilder out = new StringBuilder("佩戴的月尘魔法书：Lv").append(level).append("，经验 ").append(experience)
                    .append("\n书内法力：").append(mana).append('/').append(max).append(" 点；卷轴槽：").append(slots).append('\n');
            int selected = ((Number) AddonAccess.call("client.SpellcastClient", "getSelectedSlot", new Class<?>[0])).intValue();
            for (int slot = 0; slot < Math.min(slots, 7); slot++) {
                ItemStack scroll;
                try {
                    scroll = (ItemStack) AddonAccess.call("spell.SpellbookData", "getScroll",
                            new Class<?>[]{RegistryWrapper.WrapperLookup.class, ItemStack.class, int.class}, mc.world.getRegistryManager(), book, slot);
                } catch (NoSuchMethodException earlierSignature) {
                    scroll = (ItemStack) AddonAccess.call("spell.SpellbookData", "getScroll", new Class<?>[]{ItemStack.class, int.class}, book, slot);
                }
                out.append(slot == selected ? "▶ " : "  ").append(slot + 1).append("：");
                if (scroll.isEmpty()) { out.append("空槽\n"); continue; }
                Object spell = AddonAccess.call("spell.ScrollData", "getSpell", STACK, scroll);
                if (spell == null) { out.append("卷轴法术未识别，请检查数据同步。\n"); continue; }
                int scrollLevel = number("ScrollData", "getLevel", scroll);
                Integer castLevel = null;
                try { castLevel = number("ScrollData", "getCastLevel", scroll); }
                catch (NoSuchMethodException unavailable) { /* A scroll level is not proof of its final casting level. */ }
                long remaining;
                boolean shared = true;
                try {
                    long end = ((Number) AddonAccess.call("spell.SharedSpellCooldowns", "getEffectiveCooldownEnd",
                            new Class<?>[]{PlayerEntity.class, ItemStack.class}, mc.player, scroll)).longValue();
                    remaining = Math.max(0, end - mc.world.getTime());
                } catch (ClassNotFoundException | NoSuchMethodException earlierCooldown) {
                    shared = false;
                    remaining = Math.max(0, ((Number) AddonAccess.call("spell.SpellbookData", "getCooldownRemaining",
                            new Class<?>[]{ItemStack.class, int.class, net.minecraft.world.World.class}, book, slot, mc.world)).longValue());
                }
                Integer finalCost = null;
                if (castLevel != null) try {
                    finalCost = ((Number) AddonAccess.call("spell.SpellNumbers", "finalManaCost",
                            new Class<?>[]{AddonAccess.type("spell.Spell"), ItemStack.class, PlayerEntity.class, int.class, ItemStack.class},
                            spell, book, mc.player, castLevel, scroll)).intValue();
                } catch (ClassNotFoundException | NoSuchMethodException unavailable) { /* Report base cost separately below. */ }
                out.append(scroll.getName().getString()).append("；卷轴Lv").append(scrollLevel);
                if (castLevel != null) out.append(" / 施放Lv").append(castLevel);
                else out.append("；当前接口未提供最终施放等级");
                out.append(shared ? "；有效冷却 " : "；书槽冷却 ").append(remaining).append(" tick（").append(remaining / 20.0).append("秒）");
                if (finalCost != null && finalCost >= 0) {
                    out.append("；实际耗蓝 ").append(finalCost).append(" 点");
                    if (mana < finalCost) out.append("【法力不足，还差 ").append(finalCost - mana).append(" 点】");
                } else {
                    int base = ((Number) AddonAccess.type("spell.Spell").getMethod("getManaCost").invoke(spell)).intValue();
                    out.append("；基础耗蓝 ").append(base).append(" 点（实际耗蓝接口不可用，以服务器为准）");
                }
                if (remaining > 0) out.append("【冷却中】");
                out.append('\n');
            }
            for (var effect : mc.player.getStatusEffects()) {
                String id = String.valueOf(Registries.STATUS_EFFECT.getId(effect.getEffectType().value()));
                if (id.equals("ssc_addon:purified") || id.endsWith(":stun")) out.append("当前限制效果：").append(effect.getEffectType().value().getName().getString()).append('\n');
            }
            return out.append("\n依据：当前佩戴书与卷轴的客户端同步数据及本版SSCA提供的接口。只报告已读取的障碍；服务器还会校验施法间隔、目标、禁用技能和方案有效性。零冷却不等于服务器已允许施法。")
                    .toString();
        } catch (ReflectiveOperationException | LinkageError error) {
            com.mangzai.shapeshiftercompass.ShapeShifterCompass.LOGGER.debug("Spellbook read unavailable", error);
            return "当前 SSCA 的魔法书接口尚不可读取，请确认版本与进服同步。无法据此判断法力为零或法术就绪。";
        }
    }
    private static int number(String type, String method, ItemStack stack) throws ReflectiveOperationException {
        return ((Number) AddonAccess.call("spell." + type, method, STACK, stack)).intValue();
    }
}
