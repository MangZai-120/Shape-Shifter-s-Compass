package com.mangzai.shapeshiftercompass.tools;

import com.mangzai.shapeshiftercompass.knowledge.KnowledgeIndex;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/** No game world is started. API contracts are additionally checked against both real release jars. */
public final class CompassRegressionTest {
    private static int checks;
    public interface ComponentKey { Object get(Object entity); }
    private static final class GeneratedKey implements ComponentKey {
        @Override public Object get(Object entity) { return new OldComponent(); }
    }
    public static final class LegacyRegistry { public static final ComponentKey PLAYER_FORM = new GeneratedKey(); }
    public static final class OldForm { public String FormID; OldForm(String id) { FormID = id; } }
    public static final class NewForm {
        private final String id;
        NewForm(String id) { this.id = id; }
        public String getFormID() { return id; }
    }
    public static final class OldComponent {
        public Object getCurrentForm() { return legacy; }
    }
    public static final class NewUtils {
        public static Object getPlayerForm(Object player) { return modern; }
    }
    public static final class EmptyUtils {}
    public static final class BrokenUtils {
        public static Object getPlayerForm(Object player) { throw new IllegalStateException("not ready"); }
    }
    private static Object legacy, modern;

    private static Class<?> resolveLegacy(String name) throws ClassNotFoundException {
        if (name.endsWith(".RegPlayerFormComponent")) return LegacyRegistry.class;
        if (name.endsWith(".PlayerFormComponent")) return OldComponent.class;
        if (name.endsWith(".ComponentKey")) return ComponentKey.class;
        throw new ClassNotFoundException(name);
    }

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError(message);
    }

    private static void reflection() throws Exception {
        Object player = new Object();
        for (String id : List.of("shape-shifter-curse:bat_3", "my_addon:familiar_fox_sp",
                "my_addon:sheep_spell_form", "shape-shifter-curse:original_shifter")) {
            legacy = new OldForm(id);
            Object old = SscReflection.currentForm(player, Object.class, CompassRegressionTest::resolveLegacy);
            check(id.equals(SscReflection.formId(old)), "legacy component and public FormID: " + id);
            modern = new NewForm(id);
            Object current = SscReflection.currentForm(player, Object.class, name -> {
                if (name.endsWith(".FormUtils")) return NewUtils.class;
                throw new AssertionError("modern read must use modern API");
            });
            check(id.equals(SscReflection.formId(current)), "modern accessor and fresh value: " + id);
        }
        modern = null;
        check(SscReflection.currentForm(player, Object.class, name -> NewUtils.class) == null,
                "unready modern component must not fall back to stale legacy form");
        legacy = null;
        check(SscReflection.currentForm(player, Object.class, CompassRegressionTest::resolveLegacy) == null,
                "unready old component is not a human form");
        check(SscReflection.formId(null) == null, "null form remains unknown");
        legacy = new OldForm("my_addon:ocelot_nova");
        check(SscReflection.formId(SscReflection.currentForm(player, Object.class, name -> {
            if (name.endsWith(".FormUtils")) return EmptyUtils.class;
            return resolveLegacy(name);
        })).equals("my_addon:ocelot_nova"), "missing modern method selects legacy API");
        try {
            SscReflection.currentForm(player, Object.class, name -> BrokenUtils.class);
            throw new AssertionError("invocation failure must be reported");
        } catch (InvocationTargetException expected) {
            check(expected.getCause() instanceof IllegalStateException, "actual failure is not hidden by fallback");
        }
        check(SscReflection.component(new GeneratedKey(), player, name -> ComponentKey.class) instanceof OldComponent,
                "nonpublic generated CCA implementation is invoked through its public interface");
        check(SscReflection.component(new GeneratedKey(), player, name -> {
            if (name.equals("org.ladysnake.cca.api.v3.component.ComponentKey")) return ComponentKey.class;
            throw new ClassNotFoundException(name);
        }) instanceof OldComponent, "CCA 6 org.ladysnake public API is supported");
        check(SscReflection.component(new GeneratedKey(), player, name -> {
            if (name.equals("dev.onyxstudios.cca.api.v3.component.ComponentKey")) return ComponentKey.class;
            throw new ClassNotFoundException(name);
        }) instanceof OldComponent, "older CCA namespace remains an explicit fallback");
    }

    private static void indexContracts() {
        KnowledgeIndex index = new KnowledgeIndex();
        index.add("old.md", "<!-- compass: mod=shape-shifter-curse version=1.9.2 -->\n## 自定义形态\nset_custom_form LEGACY");
        index.add("new.md", "<!-- compass: mod=shape-shifter-curse version=1.10.0 -->\n## 自定义形态\nset_dynamic_form MODERN");
        index.add("addon.md", "<!-- compass: mod=ssc_addon version=8.0.0* -->\n## 外环增强\n外环需要真实增强。");
        index.add("common.md", "# 帮助\n\n## 生命值\n通用属性说明。");
        var old = Map.of("shape-shifter-curse", "1.9.2");
        var current = Map.of("shape-shifter-curse", "1.10.0");
        String result = index.search("自定义形态", 3, old);
        check(result.contains("LEGACY") && !result.contains("MODERN"), "no modern information in old environment");
        result = index.search("自定义形态", 3, current);
        check(result.contains("MODERN") && !result.contains("LEGACY"), "no legacy information in modern environment");
        check(!index.search("外环增强", 3, old).contains("真实增强"), "absent addon is filtered");
        check(index.search("外环增强", 3, Map.of("ssc_addon", "8.0.0-magic-dev.4")).contains("真实增强"), "explicit version wildcard");
        check(!index.search("自定义形态", 3, Map.of("shape-shifter-curse", "9.0.0")).contains("LEGACY"), "unknown version cannot borrow facts");
        check(index.search(" ", 3, old).contains("关键词"), "blank query is rejected");
        check(index.search(null, 3, old).contains("关键词"), "null query is rejected");
        check(index.search("月球全息键盘", 3, old).contains("没有找到"), "unrelated Chinese query has no character-only matches");
        check(index.search("生命值", 1, Map.of()).contains("common.md"), "common knowledge is available without mods");
    }

    private static void corpus() throws Exception {
        Path root = Path.of("build/resources/main/assets/ssc_compass/knowledge");
        check(Files.exists(root.resolve("generated/manifest.json")), "provenance manifest packaged");
        check(!Files.exists(root.resolve("ssc-wiki.md")), "old SSC mirror excluded");
        check(!Files.exists(root.resolve("mcmod-wiki.md")), "old SSCA mirror excluded");
        check(!Files.exists(root.resolve("wiki")), "unversioned external wiki excluded");
        check(Files.exists(root.resolve("licenses/CC-BY-NC-SA-4.0.txt")), "detailed wiki license included");
        KnowledgeIndex index = new KnowledgeIndex();
        try (var paths = Files.walk(root)) {
            for (Path p : paths.filter(f -> f.toString().endsWith(".md")).sorted().toList()) {
                String md = Files.readString(p, StandardCharsets.UTF_8);
                check(!md.contains("????"), "UTF-8 text: " + p.getFileName());
                check(!p.toString().contains("lore"), "no lore imports");
                check(!md.contains("逆向解析某位灵界之主尸骸"), "codex backstory is not gameplay knowledge");
                index.add(root.relativize(p).toString(), md);
            }
        }
        check(index.size() > 2000, "expanded corpus contains more than 2000 scoped entries");
        for (var env : List.of(Map.of("shape-shifter-curse", "1.9.2", "ssc_addon", "8.0.0-magic-dev.3-ssc-1.9.2"),
                Map.of("shape-shifter-curse", "1.10.0", "ssc_addon", "8.0.0-magic-dev.4"))) {
            boolean old = env.get("shape-shifter-curse").equals("1.9.2");
            for (String query : List.of("my_addon:sheep_spell_form", "my_addon:ocelot_nova", "ssc_addon:death_finale",
                    "月尘魔法书经验", "普通卷轴", "白名单 GUI", "共鸣", "进化石", "shape-shifter-curse:bat_3", "灵界之主")) {
                String result = index.search(query, 3, env);
                check(!result.contains("没有找到"), "real query resolves: " + query);
                check(!result.contains(old ? "generated\\ssc110" : "generated\\ssc192"), "SSC source matches installed version");
                check(!result.contains(old ? "generated\\addon110" : "generated\\addon192"), "addon source matches installed version");
            }
            String commands = index.search("变形与自定义形态指令", 1, env);
            check(commands.contains(old ? "set_custom_form" : "set_dynamic_form"), "correct version's form commands rank");
            String exp = index.search("月尘魔法书经验", 1, env);
            check(exp.contains("moon_dust_spellbook exp addon"), "precise book experience command ranks first");
            String rune = index.search("普通卷轴", 3, env);
            check(rune.contains("外环为空"), "foundation-only scroll correction retained");
        }
        System.out.println("Compass regression PASS: " + checks + " checks; " + index.size()
                + " entries; reflection fixtures and packaged version filtering. Live game NOT tested.");
    }

    public static void main(String[] args) throws Exception {
        reflection();
        indexContracts();
        corpus();
    }
}
