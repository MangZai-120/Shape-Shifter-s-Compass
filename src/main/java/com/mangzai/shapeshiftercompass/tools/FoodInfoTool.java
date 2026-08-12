package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

/**
 * get_food_info：按物品 id 或中文名查询任意食物的准确数值（不依赖背包是否拥有）。
 *
 * 解决问题：AI 问「XX 食物多少饥饿值」时，若该食物不在背包，旧实现只能凭记忆答（MC 各版本
 * 数值不同易错）。本工具直接从游戏注册表取真实 Item，用 AppleSkin 同款算法读 FoodComponent，
 * 保证数值与当前游戏版本完全一致。
 *
 * 查询方式：优先按 item_id（如 minecraft:cooked_beef），其次按中文名模糊匹配（如「牛排」）。
 */
public class FoodInfoTool implements CompassTool {
    @Override
    public String name() {
        return "get_food_info";
    }

    @Override
    public String description() {
        return "查询任意食物的准确数值（饥饿值、饱和度回复、是否肉类、能否饱腹时吃、附带效果），"
                + "数据来自当前游戏注册表，与 AppleSkin 同款算法，不依赖该食物是否在背包。"
                + "当玩家问某食物的营养信息时用这个，不要凭记忆回答。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject query = new JsonObject();
        query.addProperty("type", "string");
        query.addProperty("description", "食物的物品 id（如 minecraft:cooked_beef）或中文名（如 牛排）");

        JsonObject props = new JsonObject();
        props.add("query", query);

        JsonArray required = new JsonArray();
        required.add("query");

        JsonObject schema = new JsonObject();
        schema.addProperty("type", "object");
        schema.add("properties", props);
        schema.add("required", required);
        return schema;
    }

    @Override
    public String execute(JsonObject args) {
        String q = args.has("query") && !args.get("query").isJsonNull()
                ? args.get("query").getAsString().trim() : "";
        if (q.isEmpty()) {
            return "错误：query 为空";
        }

        // 1) 优先按物品 id 直接查（minecraft:cooked_beef 或 cooked_beef）
        Item byId = null;
        try {
            Identifier id = q.contains(":") ? Identifier.tryParse(q) : Identifier.tryParse("minecraft:" + q);
            if (id != null) {
                byId = Registries.ITEM.get(id);
            }
        } catch (Exception ignored) {
        }
        if (byId != null && byId != net.minecraft.item.Items.AIR) {
            net.minecraft.entity.player.PlayerEntity player = net.minecraft.client.MinecraftClient.getInstance().player;
            String desc = FoodInfo.describeItem(byId, player);
            if (!desc.isEmpty()) {
                return Registries.ITEM.getId(byId) + " → " + desc;
            }
            return q + " 不是食物（在当前游戏注册表中无 FoodComponent）。";
        }

        // 2) 按中文名模糊匹配：遍历所有注册物品，找名字匹配且是食物的
        Item matched = null;
        for (Item item : Registries.ITEM) {
            try {
                String name = item.getName().getString();
                if (q.equals(name) || name.contains(q)) {
                    if (item.isFood() && item.getFoodComponent() != null) {
                        matched = item;
                        break;
                    }
                }
            } catch (Exception ignored) {
            }
        }
        if (matched != null) {
            net.minecraft.entity.player.PlayerEntity player = net.minecraft.client.MinecraftClient.getInstance().player;
            String desc = FoodInfo.describeItem(matched, player);
            if (!desc.isEmpty()) {
                return Registries.ITEM.getId(matched) + "（" + matched.getName().getString() + "）→ " + desc;
            }
        }

        return "未找到匹配的食物「" + q + "」。可尝试用物品 id（如 minecraft:cooked_beef）查询。";
    }
}
