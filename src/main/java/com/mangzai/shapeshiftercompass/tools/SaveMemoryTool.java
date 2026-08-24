package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.memory.MemoryStore;

/**
 * save_memory：玩家在联网核对后仍坚持知识库/网上数据有误、要求以其说法为准时，
 * 把该更正永久记入本地记忆（之后每次对话都会自动加载并优先采用）。
 */
public class SaveMemoryTool implements CompassTool {
    @Override
    public String name() {
        return "save_memory";
    }

    @Override
    public String description() {
        return "把玩家的一条更正永久记入本地记忆（之后每次对话都会自动加载并优先采用）。"
                + "仅在你已经用 web_search 联网核对、但玩家仍然明确且反复坚持知识库或网上的数据是错的、"
                + "要求以他说的为准时才调用。不要因为玩家一次随口质疑就记，也不要记与游戏无关的内容。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject props = new JsonObject();
        JsonObject content = new JsonObject();
        content.addProperty("type", "string");
        content.addProperty("description",
                "要永久记住的更正内容，尽量完整清晰，写明玩家坚持的正确说法与它纠正的对象，"
                        + "例如「玩家坚持 XX 形态的血量上限是 20 点，而非知识库所说的 16 点」。");
        props.add("content", content);
        JsonArray req = new JsonArray();
        req.add("content");
        JsonObject p = new JsonObject();
        p.addProperty("type", "object");
        p.add("properties", props);
        p.add("required", req);
        return p;
    }

    @Override
    public String execute(JsonObject args) {
        if (args == null || !args.has("content")) {
            return "缺少参数 content。";
        }
        String content = args.get("content").getAsString();
        boolean added = MemoryStore.add(content);
        if (added) {
            return "已永久记住这条更正（当前共 " + MemoryStore.size() + " 条记忆），之后每次对话都会自动加载并优先采用。";
        }
        return "这条更正已在记忆中（或内容为空），无需重复记录。当前共 " + MemoryStore.size() + " 条记忆。";
    }
}
