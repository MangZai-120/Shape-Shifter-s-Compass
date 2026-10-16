package com.mangzai.shapeshiftercompass.tools;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNbtReader;
public final class ItemQuery {
    private ItemQuery() {}
    public static ItemStack resolve(String query) {
        if (query == null || query.isBlank()) return TargetContext.item();
        int nbt = query.indexOf('{');
        var item = RecipeQueryTool.resolveItem(nbt < 0 ? query.strip() : query.substring(0, nbt).strip());
        if (item == null) return ItemStack.EMPTY;
        ItemStack stack = new ItemStack(item);
        if (nbt >= 0) try { stack.setNbt(StringNbtReader.parse(query.substring(nbt))); }
        catch (com.mojang.brigadier.exceptions.CommandSyntaxException invalid) { return ItemStack.EMPTY; }
        return stack;
    }
}
