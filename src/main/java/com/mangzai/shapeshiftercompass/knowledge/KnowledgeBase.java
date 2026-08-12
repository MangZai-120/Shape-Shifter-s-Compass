package com.mangzai.shapeshiftercompass.knowledge;

import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * 预置知识库：加载 mod 内 assets/ssc_compass/knowledge/ 下所有 .md，
 * 按二级标题 ## 切分为条目，提供简单关键词检索。
 * 后续要扩充内容，只需往该目录加 .md 文件（构建会自动打包）。
 */
public final class KnowledgeBase {
    private record Entry(String title, String content) {}

    private static final List<Entry> ENTRIES = new ArrayList<>();

    private KnowledgeBase() {}

    public static void load() {
        ENTRIES.clear();
        FabricLoader.getInstance().getModContainer("ssc_compass").ifPresent(mod -> {
            Path root = mod.findPath("assets/ssc_compass/knowledge").orElse(null);
            if (root == null) {
                return;
            }
            try (Stream<Path> paths = Files.walk(root)) {
                paths.filter(p -> p.toString().toLowerCase(Locale.ROOT).endsWith(".md"))
                        .forEach(KnowledgeBase::loadFile);
            } catch (IOException e) {
                ShapeShifterCompass.LOGGER.error("Load knowledge base failed", e);
            }
        });
        ShapeShifterCompass.LOGGER.info("KnowledgeBase loaded {} entries.", ENTRIES.size());
    }

    private static void loadFile(Path p) {
        try {
            String text = Files.readString(p, StandardCharsets.UTF_8);
            // 按 markdown 二级标题切分
            String[] parts = text.split("(?m)^##\\s+");
            for (String part : parts) {
                String trimmed = part.strip();
                if (trimmed.isEmpty()) {
                    continue;
                }
                int nl = trimmed.indexOf('\n');
                String title = nl < 0 ? trimmed : trimmed.substring(0, nl).strip();
                String content = nl < 0 ? "" : trimmed.substring(nl + 1).strip();
                ENTRIES.add(new Entry(title, content.isEmpty() ? title : content));
            }
        } catch (Exception e) {
            ShapeShifterCompass.LOGGER.warn("Skip knowledge file {}: {}", p, e.getMessage());
        }
    }

    public static int size() {
        return ENTRIES.size();
    }

    /** 关键词检索：返回最多 maxResults 条最相关条目拼成的文本。 */
    public static String search(String query, int maxResults) {
        if (ENTRIES.isEmpty()) {
            return "知识库为空（未加载到任何条目）。";
        }
        String q = query.toLowerCase(Locale.ROOT);
        List<Entry> matched = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();
        for (Entry e : ENTRIES) {
            int score = 0;
            String tl = e.title.toLowerCase(Locale.ROOT);
            String cl = e.content.toLowerCase(Locale.ROOT);
            if (tl.contains(q)) {
                score += 10;
            }
            if (cl.contains(q)) {
                score += 3;
            }
            // 逐字匹配（应对中文短查询）
            for (int i = 0; i < q.length(); i++) {
                char c = q.charAt(i);
                if (Character.isWhitespace(c)) {
                    continue;
                }
                if (tl.indexOf(c) >= 0) {
                    score += 1;
                }
            }
            if (score > 0) {
                matched.add(e);
                scores.add(score);
            }
        }
        if (matched.isEmpty()) {
            return "知识库中没有找到与「" + query + "」相关的内容。可以尝试 query_recipe 查配方，或参考在线 wiki。";
        }
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < matched.size(); i++) {
            idx.add(i);
        }
        idx.sort((a, b) -> scores.get(b) - scores.get(a));
        StringBuilder sb = new StringBuilder();
        int n = Math.min(maxResults, idx.size());
        for (int i = 0; i < n; i++) {
            Entry e = matched.get(idx.get(i));
            sb.append("## ").append(e.title).append('\n').append(e.content).append("\n\n");
        }
        return sb.toString().strip();
    }
}
