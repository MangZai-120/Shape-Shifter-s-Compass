package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.*;

final class ToolArguments {
    private ToolArguments() {}
    static JsonObject schema(String... fields) {
        JsonObject root = new JsonObject(), properties = new JsonObject();
        root.addProperty("type", "object");
        for (String field : fields) {
            JsonObject property = new JsonObject(); property.addProperty("type", field.equals("count") ? "integer" : "string");
            if (field.equals("count")) { property.addProperty("minimum", 1); property.addProperty("maximum", 64); }
            properties.add(field, property);
        }
        root.add("properties", properties); return root;
    }
    static String string(JsonObject args, String field) {
        return args != null && args.has(field) && args.get(field).isJsonPrimitive() ? args.get(field).getAsString().strip() : "";
    }
}
