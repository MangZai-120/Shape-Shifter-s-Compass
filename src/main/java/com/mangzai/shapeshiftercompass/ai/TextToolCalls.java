package com.mangzai.shapeshiftercompass.ai;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/** Complete DSML envelopes only. Never execute truncated calls or examples in code fences. */
public final class TextToolCalls {
    private TextToolCalls() {}
    public record Call(String name, JsonObject args) {}
    private static final String PREFIX = "(?:[｜|]{1,2}DSML[｜|]{1,2}\\s*)?";
    private static final Pattern ENVELOPE = Pattern.compile(
            "\\A\\s*<" + PREFIX + "(tool_calls|function_calls|calls)\\s*>(.*?)</" + PREFIX + "\\1\\s*>\\s*\\z", Pattern.DOTALL);
    private static final Pattern INVOKE = Pattern.compile(
            "\\s*<" + PREFIX + "invoke\\s+name=\"([A-Za-z_][A-Za-z0-9_]*)\"\\s*>(.*?)</" + PREFIX + "invoke\\s*>", Pattern.DOTALL);
    private static final Pattern PARAM = Pattern.compile(
            "\\s*<" + PREFIX + "parameter\\s+name=\"([^\"<>]+)\"(?:\\s+string=\"(true|false)\")?\\s*>(.*?)</" + PREFIX + "parameter\\s*>", Pattern.DOTALL);
    public static List<Call> parse(String content) {
        if (content == null) return List.of();
        var envelope = ENVELOPE.matcher(content);
        if (!envelope.matches()) return List.of();
        String body = envelope.group(2);
        List<Call> calls = new ArrayList<>();
        int position = 0;
        try {
            while (!body.substring(position).isBlank()) {
                var invoke = INVOKE.matcher(body).region(position, body.length());
                if (!invoke.lookingAt() || calls.size() == 16) return List.of();
                String parameters = invoke.group(2);
                JsonObject args = new JsonObject();
                int at = 0;
                while (!parameters.substring(at).isBlank()) {
                    var param = PARAM.matcher(parameters).region(at, parameters.length());
                    if (!param.lookingAt() || args.has(param.group(1))) return List.of();
                    if ("false".equals(param.group(2))) args.add(param.group(1), JsonParser.parseString(param.group(3)));
                    else args.addProperty(param.group(1), param.group(3));
                    at = param.end();
                }
                calls.add(new Call(invoke.group(1), args));
                position = invoke.end();
            }
        } catch (RuntimeException invalid) { return List.of(); }
        return List.copyOf(calls);
    }

    public static boolean containsMarkup(String content) {
        return content != null && Pattern.compile("<\\/?" + PREFIX + "(?:tool_calls|function_calls|calls|invoke|parameter)\\b").matcher(content).find();
    }

    public static JsonObject arguments(JsonElement value) {
        if (value == null || value.isJsonNull()) return new JsonObject();
        JsonElement parsed = value.isJsonPrimitive() && value.getAsJsonPrimitive().isString()
                ? JsonParser.parseString(value.getAsString()) : value;
        if (!parsed.isJsonObject()) throw new IllegalArgumentException("Expected an argument object");
        return parsed.getAsJsonObject();
    }
}
