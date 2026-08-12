package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * 列出玩家当前实际加载的模组（id / 名称 / 版本）。可选 filter 关键词按 id 或名称过滤。
 * 供 AI 在回答模组相关问题前确认玩家是否装了该模组、拿到准确版本号，再联网核对。
 */
public class ListModsTool implements CompassTool {

    @Override
    public String name() {
        return "list_mods";
    }

    @Override
    public String description() {
        return "列出玩家当前实际加载的模组（含 id、名称、版本）。可传 filter 关键词只看匹配的模组。"
                + "当玩家询问某个模组、或问自己装了哪些模组时，先用它确认加载情况和准确版本，再联网搜索核对。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject filter = new JsonObject();
        filter.addProperty("type", "string");
        filter.addProperty("description", "可选：按模组 id 或名称的关键词过滤（不区分大小写）；留空则列出全部模组。");
        JsonObject props = new JsonObject();
        props.add("filter", filter);
        JsonObject schema = new JsonObject();
        schema.addProperty("type", "object");
        schema.add("properties", props);
        schema.add("required", new JsonArray());
        return schema;
    }

    @Override
    public String execute(JsonObject args) {
        String filter = args.has("filter") && !args.get("filter").isJsonNull()
                ? args.get("filter").getAsString().trim().toLowerCase() : "";

        List<String> matched = new ArrayList<>();
        int total = 0;
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            ModMetadata meta = mod.getMetadata();
            total++;
            String id = meta.getId();
            String nm = meta.getName();
            String ver = meta.getVersion().getFriendlyString();
            if (filter.isEmpty()
                    || id.toLowerCase().contains(filter)
                    || (nm != null && nm.toLowerCase().contains(filter))) {
                matched.add(nm + " (" + id + ") v" + ver);
            }
        }
        if (matched.isEmpty()) {
            return filter.isEmpty()
                    ? "未检测到已加载的模组。"
                    : "未找到匹配 \"" + filter + "\" 的已加载模组（共加载 " + total + " 个模组）。";
        }
        StringBuilder sb = new StringBuilder();
        if (filter.isEmpty()) {
            sb.append("共加载 ").append(total).append(" 个模组：\n");
        } else {
            sb.append("匹配 \"").append(filter).append("\" 的模组（共加载 ").append(total).append(" 个）：\n");
        }
        for (String s : matched) {
            sb.append("- ").append(s).append("\n");
        }
        return sb.toString().trim();
    }
}
