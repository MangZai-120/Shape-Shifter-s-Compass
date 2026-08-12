package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.ShapeShifterCompass;

import java.util.LinkedHashMap;
import java.util.Map;

/** 工具注册表：登记所有 CompassTool，向 AI 提供 tools 定义并按名分发执行。 */
public final class ToolRegistry {
    private static final Map<String, CompassTool> TOOLS = new LinkedHashMap<>();

    private ToolRegistry() {}

    public static void register(CompassTool tool) {
        TOOLS.put(tool.name(), tool);
    }

    public static boolean isEmpty() {
        return TOOLS.isEmpty();
    }

    public static CompassTool get(String name) {
        return TOOLS.get(name);
    }

    /** 构造 OpenAI 兼容的 tools 数组。 */
    public static JsonArray toolsJson() {
        JsonArray arr = new JsonArray();
        for (CompassTool t : TOOLS.values()) {
            JsonObject fn = new JsonObject();
            fn.addProperty("name", t.name());
            fn.addProperty("description", t.description());
            fn.add("parameters", t.parameters());
            JsonObject tool = new JsonObject();
            tool.addProperty("type", "function");
            tool.add("function", fn);
            arr.add(tool);
        }
        return arr;
    }

    /** 按名执行工具，异常安全（结果始终是可回传给 AI 的文本）。 */
    public static String execute(String name, JsonObject args) {
        CompassTool t = TOOLS.get(name);
        if (t == null) {
            return "错误：未知工具 " + name;
        }
        try {
            return t.execute(args);
        } catch (Exception e) {
            ShapeShifterCompass.LOGGER.error("Tool '{}' execute failed", name, e);
            return "错误：工具执行失败 - " + e.getMessage();
        }
    }
}
