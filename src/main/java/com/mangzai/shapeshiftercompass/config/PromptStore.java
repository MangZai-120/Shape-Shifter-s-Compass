package com.mangzai.shapeshiftercompass.config;

import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import com.mangzai.shapeshiftercompass.conversation.Conversation;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 系统提示词外置：首次启动把内置默认提示词写到 config/ssc_compass/system_prompt.txt，
 * 之后每次启动读取该文件作为系统提示词（玩家可编辑、重启生效；删除文件即恢复默认）。
 */
public final class PromptStore {
    private static final Path DIR = FabricLoader.getInstance().getConfigDir().resolve("ssc_compass");
    private static final Path PATH = DIR.resolve("system_prompt.txt");
    private static String prompt = null; // null = 用内置默认

    private PromptStore() {}

    public static void load() {
        try {
            if (Files.exists(PATH)) {
                String s = Files.readString(PATH, StandardCharsets.UTF_8).strip();
                prompt = s.isEmpty() ? null : s;
                if (prompt != null) {
                    return;
                }
            }
            // 文件不存在或为空：写出当前默认提示词，供玩家查看/编辑
            Files.createDirectories(DIR);
            Files.writeString(PATH, Conversation.DEFAULT_SYSTEM, StandardCharsets.UTF_8);
            prompt = null;
        } catch (Exception e) {
            ShapeShifterCompass.LOGGER.error("Failed to load system prompt", e);
            prompt = null;
        }
    }

    /** 当前生效的系统提示词（外置文件优先，否则内置默认）。 */
    public static String get() {
        return prompt != null ? prompt : Conversation.DEFAULT_SYSTEM;
    }

    /** 提示词与聊天记录所在文件夹（config/ssc_compass），确保存在后返回。 */
    public static Path dir() {
        try {
            Files.createDirectories(DIR);
        } catch (Exception ignored) {
        }
        return DIR;
    }
}
