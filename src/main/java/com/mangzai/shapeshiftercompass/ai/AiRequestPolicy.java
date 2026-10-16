package com.mangzai.shapeshiftercompass.ai;

import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.config.CompassConfig;
import java.net.URI;

/** Per-request recovery; never increases the token limit or changes the saved configuration. */
public final class AiRequestPolicy {
    private boolean thinkingSuppressed;

    public static boolean isDirectDeepSeek(CompassConfig cfg) {
        try { return "api.deepseek.com".equalsIgnoreCase(URI.create(cfg.endpoint()).getHost()); }
        catch (RuntimeException invalid) { return false; }
    }

    public void configure(JsonObject body, CompassConfig cfg) {
        if (isDirectDeepSeek(cfg)) {
            boolean thinking = cfg.thinkingEnabled && !thinkingSuppressed;
            JsonObject mode = new JsonObject(); mode.addProperty("type", thinking ? "enabled" : "disabled");
            body.add("thinking", mode);
            if (thinking && cfg.reasoningEffort != null && !cfg.reasoningEffort.isBlank())
                body.addProperty("reasoning_effort", cfg.reasoningEffort);
        } else if (cfg.thinkingEnabled && cfg.reasoningEffort != null && !cfg.reasoningEffort.isBlank()) {
            body.addProperty("reasoning_effort", cfg.reasoningEffort);
        }
    }

    public boolean recoverTruncatedThinking(CompassConfig cfg, String finishReason, String content,
                                             boolean hasCalls, boolean hasMutations) {
        if (thinkingSuppressed || !cfg.thinkingEnabled || !isDirectDeepSeek(cfg) || hasCalls || hasMutations
                || !"length".equals(finishReason) || (content != null && !content.isBlank())) return false;
        thinkingSuppressed = true;
        return true;
    }

    public static String emptyReason(String finishReason) {
        if ("length".equals(finishReason))
            return "模型达到本次输出上限，尚未返回正文。未自动增加 token 上限。";
        return "模型没有返回可显示的文字（finish_reason=" + finishReason + "）。";
    }
}
