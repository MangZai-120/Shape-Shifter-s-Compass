package com.mangzai.shapeshiftercompass.tools;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStringReader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public final class ItemQuery {
    private ItemQuery() {}

    public static ItemStack resolve(String query) {
        if (query == null || query.isBlank()) return TargetContext.item();
        RegistryWrapper.WrapperLookup registries = null;
        if (query.indexOf('[') >= 0) {
            var mc = MinecraftClient.getInstance();
            if (mc != null && mc.world != null) registries = mc.world.getRegistryManager();
        }
        return resolve(query, registries);
    }

    /** Uses the same component parser as vanilla item commands; never executes a command. */
    public static ItemStack resolve(String query, RegistryWrapper.WrapperLookup registries) {
        if (query == null || query.isBlank()) return ItemStack.EMPTY;
        query = query.strip();
        int components = query.indexOf('['), nbt = query.indexOf('{');
        if (nbt >= 0 && components > nbt) components = -1; // A list inside legacy SNBT is not a component suffix.
        int split = components >= 0 ? components : nbt;
        var item = RecipeQueryTool.resolveItem(split < 0 ? query : query.substring(0, split).strip());
        if (item == null) return ItemStack.EMPTY;
        ItemStack stack = new ItemStack(item);
        try {
            if (components >= 0) {
                if (registries == null) registries = RegistryWrapper.WrapperLookup.of(
                        Registries.REGISTRIES.stream().map(Registry::getReadOnlyWrapper));
                StringReader reader = new StringReader(Registries.ITEM.getId(item) + query.substring(components));
                var parsed = new ItemStringReader(registries).consume(reader);
                if (reader.canRead()) return ItemStack.EMPTY;
                return new ItemStackArgument(parsed.item(), parsed.components()).createStack(1, false);
            }
            if (nbt >= 0) {
                // Preserve old queries for addon custom data; migrate the vanilla Potion field.
                var data = StringNbtReader.parse(query.substring(nbt));
                if (data.contains("Potion")) {
                    var id = Identifier.tryParse(data.getString("Potion"));
                    if (id == null) return ItemStack.EMPTY;
                    var potion = Registries.POTION.getEntry(id);
                    if (potion.isEmpty()) return ItemStack.EMPTY;
                    stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(potion.get()));
                    data.remove("Potion");
                }
                if (!data.isEmpty()) stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(data));
            }
            return stack;
        } catch (CommandSyntaxException | IllegalArgumentException invalid) {
            return ItemStack.EMPTY;
        }
    }
}
