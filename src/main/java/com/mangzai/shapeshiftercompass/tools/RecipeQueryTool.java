package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

/** query_recipe：查某物品的合成配方（读客户端已同步的 RecipeManager，覆盖原版及已注册配方）。 */
public class RecipeQueryTool implements CompassTool {
    @Override
    public String name() {
        return "query_recipe";
    }

    @Override
    public String description() {
        return "查询某个物品的合成配方，返回其材料。参数 item 可以是物品 ID（如 minecraft:chest）或中文/英文名称。"
                + "注意：仅能查到注册在配方系统里的配方，SSCA 的部分自定义合成（Apoli item_on_item）不在此列，那些请用 query_knowledge。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject props = new JsonObject();
        JsonObject item = new JsonObject();
        item.addProperty("type", "string");
        item.addProperty("description", "要查配方的物品 ID 或名称");
        props.add("item", item);
        JsonArray req = new JsonArray();
        req.add("item");
        JsonObject p = new JsonObject();
        p.addProperty("type", "object");
        p.add("properties", props);
        p.add("required", req);
        return p;
    }

    @Override
    public String execute(JsonObject args) {
        if (args == null || !args.has("item")) {
            return "缺少参数 item。";
        }
        String q = args.get("item").getAsString().trim();
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null) {
            return "玩家当前不在游戏世界中。";
        }

        Item target = resolveItem(q);
        if (target == null) {
            return "找不到物品：" + q + "（请尝试用物品 ID，如 minecraft:chest）。";
        }

        DynamicRegistryManager drm = mc.world.getRegistryManager();
        StringBuilder sb = new StringBuilder();
        int found = 0;
        for (Recipe<?> r : mc.world.getRecipeManager().values()) {
            ItemStack out;
            try {
                out = r.getOutput(drm);
            } catch (Exception e) {
                continue;
            }
            if (out.isEmpty() || out.getItem() != target) {
                continue;
            }
            found++;
            sb.append("【配方 ").append(found).append("】产物: ")
                    .append(out.getName().getString()).append(" x").append(out.getCount())
                    .append("（类型 ").append(r.getClass().getSimpleName()).append("）\n材料: ");
            for (Ingredient ing : r.getIngredients()) {
                ItemStack[] ms = ing.getMatchingStacks();
                sb.append('[').append(ms.length == 0 ? "空" : ms[0].getName().getString()).append("] ");
            }
            sb.append('\n');
            if (found >= 5) {
                sb.append("（配方较多，仅显示前 5 条）\n");
                break;
            }
        }
        return found == 0 ? ("没有找到 " + target.getName().getString() + " 的注册配方（可能是自定义合成，试试 query_knowledge）。")
                : sb.toString();
    }

    private static Item resolveItem(String q) {
        Identifier id = Identifier.tryParse(q);
        if (id != null && Registries.ITEM.containsId(id)) {
            return Registries.ITEM.get(id);
        }
        for (Item it : Registries.ITEM) {
            if (it.getName().getString().equalsIgnoreCase(q)) {
                return it;
            }
        }
        return null;
    }
}
