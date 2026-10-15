package com.mangzai.shapeshiftercompass.knowledge;

import java.util.*;
import java.util.regex.*;

/** Version-scoped, deterministic phrase/word/CJK bigram retrieval. */
public final class KnowledgeIndex {
    private record Entry(String title, String content, String source, String mod, String version) {}
    private record Hit(Entry entry, int score) {}
    private final List<Entry> entries = new ArrayList<>();
    private static final Pattern SCOPE = Pattern.compile("<!-- compass: mod=([\\w-]+) version=([^\\s]+) -->");
    private static final Pattern TERMS = Pattern.compile("[a-z0-9_]+(?::[a-z0-9_./-]+)?|[\\p{IsHan}]+");

    public void add(String source, String markdown) {
        Matcher scope = SCOPE.matcher(markdown);
        String mod = "", version = "*";
        if (scope.find()) { mod = scope.group(1); version = scope.group(2); }
        String text = SCOPE.matcher(markdown).replaceAll("").replace("\r\n", "\n");
        for (String part : text.split("(?m)^#{1,3}\\s+")) {
            String trimmed = part.strip();
            if (trimmed.isEmpty()) continue;
            int newline = trimmed.indexOf('\n');
            String title = newline < 0 ? trimmed : trimmed.substring(0, newline).strip();
            String content = newline < 0 ? title : trimmed.substring(newline + 1).strip();
            if (!content.isEmpty()) entries.add(new Entry(title, content, source, mod, version));
        }
    }

    public int size() { return entries.size(); }

    private static boolean applies(Entry e, Map<String, String> versions) {
        if (e.mod.isEmpty()) return true;
        String actual = versions.get(e.mod);
        if (actual == null) return false;
        return e.version.equals("*") || Arrays.stream(e.version.split(",")).anyMatch(v ->
                v.endsWith("*") ? actual.startsWith(v.substring(0, v.length() - 1)) : actual.equals(v));
    }

    public String search(String query, int maxResults, Map<String, String> versions) {
        if (query == null || query.isBlank()) return "请提供要检索的关键词。";
        if (entries.isEmpty()) return "知识库为空（未加载到任何条目）。";
        String q = query.strip().toLowerCase(Locale.ROOT);
        Set<String> words = new LinkedHashSet<>(), grams = new LinkedHashSet<>();
        Matcher tokens = TERMS.matcher(q);
        while (tokens.find()) {
            String token = tokens.group();
            if (token.length() < 2) continue;
            words.add(token);
            if (Character.UnicodeScript.of(token.codePointAt(0)) == Character.UnicodeScript.HAN) {
                for (int i = 0; i + 1 < token.length(); i++) grams.add(token.substring(i, i + 2));
            }
        }
        List<Hit> hits = new ArrayList<>();
        for (Entry e : entries) {
            if (!applies(e, versions)) continue;
            String title = e.title.toLowerCase(Locale.ROOT), content = e.content.toLowerCase(Locale.ROOT);
            int score = title.contains(q) ? 200 : content.contains(q) ? 60 : 0;
            boolean wordMatch = false;
            for (String word : words) {
                if (title.contains(word)) { score += 24; wordMatch = true; }
                if (content.contains(word)) { score += 4; wordMatch = true; }
            }
            int gramMatches = 0;
            for (String gram : grams) {
                if (title.contains(gram)) { score += 5; gramMatches++; }
                else if (content.contains(gram)) { score++; gramMatches++; }
            }
            if (wordMatch || (gramMatches >= 2 && gramMatches * 3 >= grams.size())) hits.add(new Hit(e, score));
        }
        hits.sort(Comparator.comparingInt(Hit::score).reversed().thenComparing(h -> h.entry.source)
                .thenComparing(h -> h.entry.title));
        if (hits.isEmpty()) return "当前已安装版本的知识库中没有找到与「" + query
                + "」相关的内容；不能使用其它版本的条目推断。配方请用 query_recipe 查实时数据。";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < Math.min(Math.max(maxResults, 1), Math.min(8, hits.size())); i++) {
            Entry e = hits.get(i).entry;
            out.append("## ").append(e.title).append('\n').append("资料: ").append(e.source);
            if (!e.mod.isEmpty()) out.append(" | ").append(e.mod).append(' ').append(e.version)
                    .append(" | 当前安装 ").append(versions.get(e.mod));
            out.append('\n').append(e.content.length() > 6000 ? e.content.substring(0, 6000)
                    + "\n（条目过长，请缩小关键词继续检索）" : e.content).append("\n\n");
        }
        return out.toString().strip();
    }
}
