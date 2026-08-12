package com.mangzai.shapeshiftercompass.conversation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** 多会话管理 + 本地持久化（config/ssc_compass/conversations.json）+ 占用统计。 */
public final class ConversationStore {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir()
            .resolve("ssc_compass").resolve("conversations.json");
    private static final List<Conversation> LIST = new ArrayList<>();
    private static Conversation current;

    private ConversationStore() {}

    public static List<Conversation> all() {
        return LIST;
    }

    public static Conversation current() {
        if (current == null) {
            if (LIST.isEmpty()) {
                newConversation();
            } else {
                current = LIST.get(0);
            }
        }
        return current;
    }

    public static void switchTo(Conversation c) {
        current = c;
    }

    public static Conversation newConversation() {
        Conversation c = new Conversation();
        LIST.add(0, c);
        current = c;
        save();
        return c;
    }

    public static void delete(Conversation c) {
        LIST.remove(c);
        if (current == c) {
            current = null;
        }
        // 始终保证至少有一个会话：删空后自动新建
        if (LIST.isEmpty()) {
            newConversation();
        } else if (current == null) {
            current = LIST.get(0);
        }
        save();
    }

    public static long totalBytes() {
        return GSON.toJson(LIST).getBytes(StandardCharsets.UTF_8).length;
    }

    public static long bytesOf(Conversation c) {
        return GSON.toJson(c).getBytes(StandardCharsets.UTF_8).length;
    }

    public static String humanBytes(long b) {
        if (b < 1024) {
            return b + " B";
        }
        if (b < 1024L * 1024) {
            return String.format("%.1f KB", b / 1024.0);
        }
        if (b < 1024L * 1024 * 1024) {
            return String.format("%.1f MB", b / (1024.0 * 1024));
        }
        return String.format("%.2f GB", b / (1024.0 * 1024 * 1024));
    }

    public static void load() {
        try {
            if (Files.exists(PATH)) {
                String json = Files.readString(PATH, StandardCharsets.UTF_8);
                Type t = new TypeToken<List<Conversation>>() {}.getType();
                List<Conversation> loaded = GSON.fromJson(json, t);
                LIST.clear();
                if (loaded != null) {
                    LIST.addAll(loaded);
                }
            }
        } catch (Exception e) {
            ShapeShifterCompass.LOGGER.error("Load conversations failed", e);
        }
        if (LIST.isEmpty()) {
            newConversation();
        } else {
            current = LIST.get(0);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, GSON.toJson(LIST), StandardCharsets.UTF_8);
        } catch (Exception e) {
            ShapeShifterCompass.LOGGER.error("Save conversations failed", e);
        }
    }
}
