package com.mangzai.shapeshiftercompass.memory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 玩家更正记忆：AI 联网核对后玩家仍坚持知识库有误时，把更正永久记下。
 * 每轮对话作为 system 消息注入，跨对话与重进游戏均保留。存于 config/ssc_compass_memory.json。
 */
public final class MemoryStore {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("ssc_compass_memory.json");
    private static final Type LIST_TYPE = new TypeToken<List<String>>() {}.getType();
    private static final int MAX_ENTRIES = 100;
    private static final List<String> ENTRIES = new ArrayList<>();

    private MemoryStore() {}

    public static synchronized void load() {
        try {
            if (Files.exists(PATH)) {
                List<String> loaded = GSON.fromJson(Files.readString(PATH), LIST_TYPE);
                ENTRIES.clear();
                if (loaded != null) {
                    ENTRIES.addAll(loaded);
                }
            }
        } catch (Exception e) {
            ShapeShifterCompass.LOGGER.error("Failed to load memory", e);
        }
    }

    private static void save() {
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, GSON.toJson(ENTRIES));
        } catch (Exception e) {
            ShapeShifterCompass.LOGGER.error("Failed to save memory", e);
        }
    }

    /** 新增一条更正记忆（去重 + 超上限裁剪最旧）。已存在或为空返回 false。 */
    public static synchronized boolean add(String content) {
        if (content == null) {
            return false;
        }
        String c = content.strip();
        if (c.isEmpty() || ENTRIES.contains(c)) {
            return false;
        }
        ENTRIES.add(c);
        while (ENTRIES.size() > MAX_ENTRIES) {
            ENTRIES.remove(0);
        }
        save();
        return true;
    }

    public static synchronized List<String> all() {
        return new ArrayList<>(ENTRIES);
    }

    public static synchronized int size() {
        return ENTRIES.size();
    }

    public static synchronized void clear() {
        ENTRIES.clear();
        save();
    }

    /** 拼成每轮注入的 system 提示块；无记忆时返回 null。 */
    public static synchronized String promptBlock() {
        if (ENTRIES.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[玩家更正记忆] 以下是玩家此前经过联网核对后仍坚持、并要求你永久记住的更正");
        sb.append("（优先级高于本地知识库）：\n");
        for (int i = 0; i < ENTRIES.size(); i++) {
            sb.append(i + 1).append(". ").append(ENTRIES.get(i)).append("\n");
        }
        sb.append("回答相关问题时，优先采用这些玩家更正，而不是与之冲突的知识库旧内容。");
        return sb.toString();
    }
}
