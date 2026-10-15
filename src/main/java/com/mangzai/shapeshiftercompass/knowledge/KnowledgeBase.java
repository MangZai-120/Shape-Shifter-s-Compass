package com.mangzai.shapeshiftercompass.knowledge;

import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public final class KnowledgeBase {
    private static volatile KnowledgeIndex index = new KnowledgeIndex();
    private KnowledgeBase() {}

    public static void load() {
        KnowledgeIndex next = new KnowledgeIndex();
        FabricLoader.getInstance().getModContainer("ssc_compass").ifPresent(mod -> {
            Path root = mod.findPath("assets/ssc_compass/knowledge").orElse(null);
            if (root == null) return;
            try (Stream<Path> paths = Files.walk(root)) {
                for (Path p : paths.filter(f -> f.toString().endsWith(".md")).sorted().toList()) {
                    try { next.add(root.relativize(p).toString(), Files.readString(p, StandardCharsets.UTF_8)); }
                    catch (IOException e) { ShapeShifterCompass.LOGGER.warn("Skip knowledge file {}", p, e); }
                }
            } catch (IOException e) { ShapeShifterCompass.LOGGER.error("Load knowledge base failed", e); }
        });
        index = next;
        ShapeShifterCompass.LOGGER.info("KnowledgeBase loaded {} version-scoped entries.", index.size());
    }

    public static Map<String, String> installedVersions() {
        Map<String, String> versions = new LinkedHashMap<>();
        for (String id : List.of("shape-shifter-curse", "ssc_addon", "apoli")) {
            FabricLoader.getInstance().getModContainer(id).ifPresent(mod ->
                    versions.put(id, mod.getMetadata().getVersion().getFriendlyString()));
        }
        return versions;
    }

    /** Per-turn context also corrects old user-customized/saved system prompts. */
    public static String environmentPrompt() {
        return "[SSC/SSCA 当前环境] " + installedVersions()
                + "。形态以 get_form_status 的实时结果为准，读取失败不代表人类或冷却完成。"
                + "query_knowledge 已按安装版本过滤，每条带来源和版本；其它版本网页不能覆盖当前版本证据。"
                + "法力/能量是资源点，只有计时器才以 tick 换算秒。形态 Apoli 资源与月尘魔法书 NBT 法力是不同系统。"
                + "普通食物对形态是否有效以 get_food_info 为准，免疫 saturation 效果不等于所有食物无效。"
                + "涉及符文只解释效果、代价、协同与研究方法，不公开具体法术的符文解法。";
    }

    public static int size() { return index.size(); }
    public static String search(String query, int maxResults) {
        Map<String, String> versions = installedVersions();
        return "当前环境: " + versions + "\n" + index.search(query, maxResults, versions);
    }
}
