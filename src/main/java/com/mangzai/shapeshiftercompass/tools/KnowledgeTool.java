package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.knowledge.KnowledgeBase;

/** query_knowledge：检索预置的 SSC/SSCA 知识库（形态/进化/机制/物品/指令；不含剧情故事）。 */
public class KnowledgeTool implements CompassTool {
    @Override
    public String name() {
        return "query_knowledge";
    }

    @Override
    public String description() {
        return "检索《幻形者诅咒》SSC 与附属 SSCA 的预置知识库，涵盖形态、进化途径、机制、物品、指令等。"
                + "遇到自定义合成（如月髓环等 Apoli 合成）或机制类问题优先用此工具。本知识库不包含剧情故事内容。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject props = new JsonObject();
        JsonObject query = new JsonObject();
        query.addProperty("type", "string");
        query.addProperty("description", "要检索的关键词或问题，如「月髓环」「使魔进化」「白名单」「美西螈湿润度」");
        props.add("query", query);
        JsonArray req = new JsonArray();
        req.add("query");
        JsonObject p = new JsonObject();
        p.addProperty("type", "object");
        p.add("properties", props);
        p.add("required", req);
        return p;
    }

    @Override
    public String execute(JsonObject args) {
        if (args == null || !args.has("query")) {
            return "缺少参数 query。";
        }
        return KnowledgeBase.search(args.get("query").getAsString(), 3);
    }
}
