package com.mangzai.shapeshiftercompass.config;

/** AI 厂商预设：选中后自动填入 baseUrl 与候选 model；API Key 始终由玩家自行填写。 */
public enum AiProvider {
    OPENAI("OpenAI", "https://api.openai.com/v1", new String[]{"gpt-5.4", "gpt-5.5-mini"}),
    ZAI("z.ai (GLM)", "https://api.z.ai/api/paas/v4", new String[]{"glm-5", "glm-5.1"}),
    DEEPSEEK("DeepSeek", "https://api.deepseek.com", new String[]{"deepseek-v4-flash", "deepseek-v4-pro"}),
    MOONSHOT("Moonshot (Kimi)", "https://api.moonshot.cn/v1", new String[]{"kimi-k2.6", "kimi-k3"}),
    OPENROUTER("OpenRouter", "https://openrouter.ai/api/v1",
            new String[]{"anthropic/claude-sonnet-4.6", "openai/gpt-5.2", "deepseek/deepseek-chat"}),
    OPENCODE("OpenCode Zen", "https://opencode.ai/zen/v1",
            new String[]{"glm-5.2", "kimi-k2.6", "deepseek-v4-flash"}),
    CUSTOM("Custom", "", new String[]{});

    public final String displayName;
    public final String baseUrl;
    public final String[] defaultModels;

    AiProvider(String displayName, String baseUrl, String[] defaultModels) {
        this.displayName = displayName;
        this.baseUrl = baseUrl;
        this.defaultModels = defaultModels;
    }
}
