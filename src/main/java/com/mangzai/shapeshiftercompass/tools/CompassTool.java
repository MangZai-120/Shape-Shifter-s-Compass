package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;

/** AI 可调用的工具（Function Calling）。所有 execute 在客户端主线程执行，只读玩家本人数据。 */
public interface CompassTool {
    /** 工具名（对应 OpenAI function name）。 */
    String name();

    /** 给 AI 看的功能描述。 */
    String description();

    /** OpenAI JSON Schema 参数定义（无参时返回 {type:object, properties:{}}）。 */
    JsonObject parameters();

    /** 执行工具，返回给 AI 的结果文本（主线程调用，除非 async() 为 true）。 */
    String execute(JsonObject args);

    /** 是否在异步线程执行（如联网 HTTP，避免阻塞渲染主线程）；默认 false（主线程读游戏状态）。 */
    default boolean async() {
        return false;
    }
}
