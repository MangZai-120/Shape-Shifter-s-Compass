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
                + "涉及符文只解释效果、代价、协同与研究方法，不公开具体法术的符文解法。"
                + "魔法书状态与施法障碍用 get_spellbook_status；玩家问指向的这个东西时用 get_target_info，注明目标是打开罗盘时的快照。"
                + "玩家明确要求给魔法书加经验时，使用 add_spellbook_experience(amount=实际经验点,target=auto)，一次请求只发放一次。"
                + "模糊的身上书用auto实时优先佩戴书、其次手持书；明确佩戴用equipped、明确手持用held，不能混用或用玩家xp代替。"
                + "某次没有佩戴或没拿在手里，不代表服务器不支持指令。不得沿用历史回答里的不支持结论，实际工具反馈优先。"
                + "服务器未支持equipped时如实说明，不得改手持的另一本书；工具超时只代表结果未确认，不得重复加经验。"
                + "制作缺口与路线用 plan_crafting，进化下一步用 get_evolution_advice；这些工具只读，不会自动制作或解锁。";
    }

    public static int size() { return index.size(); }
    public static String search(String query, int maxResults) {
        Map<String, String> versions = installedVersions();
        return "当前环境: " + versions + "\n" + index.search(query, maxResults, versions);
    }
}
