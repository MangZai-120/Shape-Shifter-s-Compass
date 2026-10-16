package com.mangzai.shapeshiftercompass.ai;

import com.google.gson.JsonObject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/** A request owns its results. Recovery must not repeat an experience grant or hide its outcome. */
public final class ToolOutcomes {
    private final Map<String, String> mutations = new LinkedHashMap<>();
    private final Map<String, String> latest = new LinkedHashMap<>();
    private static boolean mutates(String name) {
        return name.equals("run_command") || name.equals("set_player_data") || name.equals("add_spellbook_experience");
    }
    private static String key(String name, JsonObject args) {
        if (name.equals("add_spellbook_experience")) {
            try { return "command:" + com.mangzai.shapeshiftercompass.tools.AddSpellbookExperienceTool.command(args); }
            catch (RuntimeException invalid) { /* Invalid arguments have no side effect. */ }
        }
        if (name.equals("run_command") && args.has("command") && args.get("command").isJsonPrimitive()) {
            String command = args.get("command").getAsString().trim().replaceFirst("^/+\\s*", "");
            if (command.matches("ssc_addon\\s+moon_dust_spellbook\\s+exp\\s+addon\\s+[0-9]+(?:\\s+equipped)?"))
                command = command.replaceAll("\\s+", " ");
            return "command:" + command;
        }
        var sorted = new TreeMap<String, String>();
        args.entrySet().forEach(e -> sorted.put(e.getKey(), e.getValue().toString()));
        return name + sorted;
    }
    public String previousMutation(String name, JsonObject args) {
        return mutates(name) ? mutations.get(key(name, args)) : null;
    }
    public void record(String name, JsonObject args, String result) {
        if (mutates(name)) mutations.put(key(name, args), result);
        latest.remove(name);
        latest.put(name, result);
    }
    public boolean hasResults() { return !latest.isEmpty(); }
    public boolean hasMutations() { return !mutations.isEmpty(); }
    public String fallback(String reason) {
        if (latest.isEmpty()) return reason + "\n未执行任何游戏工具操作；请检查模型是否支持工具调用，或关闭思考模式后重试。";
        StringBuilder text = new StringBuilder(reason).append("\n以下是已收到的实际工具结果（不会自动重复执行）：");
        latest.forEach((name, result) -> {
            if (text.length() < 10000) text.append("\n\n").append(name).append("：\n")
                    .append(result, 0, Math.min(result.length(), 4000));
        });
        return text.toString();
    }
}
