package com.mangzai.shapeshiftercompass.ai;

/** OpenAI 兼容对话消息。role 取值 system / user / assistant。 */
public class ChatMessage {
    public String role;
    public String content;

    public ChatMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
