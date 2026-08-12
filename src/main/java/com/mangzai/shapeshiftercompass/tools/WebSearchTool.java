package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 联网搜索工具：组合查询 Minecraft Wiki 与 Wikipedia（均为 MediaWiki search API），返回条目标题与摘要。
 * 在异步线程执行 HTTP（async()=true），不阻塞渲染主线程。MediaWiki 要求 User-Agent，否则返回 403。
 */
public class WebSearchTool implements CompassTool {
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();
    private static final String UA = "ShapeShiftersCompass/1.0 (Minecraft Fabric mod)";
    private static final String BROWSER_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
            + "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
    private static final String MC_WIKI = "https://zh.minecraft.wiki/api.php";
    private static final String WIKIPEDIA = "https://zh.wikipedia.org/w/api.php";
    private static final String BING = "https://cn.bing.com/search";

    @Override
    public String name() {
        return "web_search";
    }

    @Override
    public boolean async() {
        return true;
    }

    @Override
    public String description() {
        return "联网搜索。默认 scope=web 为通用网络搜索（Bing，自动优先返回 wiki 类结果），适合查模组、"
                + "整合包等任何信息；也可 scope=mc 仅查 Minecraft Wiki、scope=wiki 仅查 Wikipedia。返回标题/链接/摘要供你归纳回答。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject query = new JsonObject();
        query.addProperty("type", "string");
        query.addProperty("description", "搜索关键词");

        JsonObject scope = new JsonObject();
        scope.addProperty("type", "string");
        scope.addProperty("description", "搜索范围：web=通用网络搜索(默认,优先wiki)，mc=仅 Minecraft Wiki，wiki=仅 Wikipedia，both=MC Wiki+Wikipedia");

        JsonObject props = new JsonObject();
        props.add("query", query);
        props.add("scope", scope);

        JsonArray required = new JsonArray();
        required.add("query");

        JsonObject schema = new JsonObject();
        schema.addProperty("type", "object");
        schema.add("properties", props);
        schema.add("required", required);
        return schema;
    }

    @Override
    public String execute(JsonObject args) {
        String query = args.has("query") && !args.get("query").isJsonNull()
                ? args.get("query").getAsString() : "";
        if (query.isBlank()) {
            return "错误：query 为空";
        }
        String scope = args.has("scope") && !args.get("scope").isJsonNull()
                ? args.get("scope").getAsString() : "web";

        // web：通用网络搜索（Bing，优先 wiki），适合模组等 MediaWiki 覆盖不到的内容
        if ("web".equals(scope)) {
            return searchBing(query);
        }
        StringBuilder sb = new StringBuilder();
        if ("mc".equals(scope) || "both".equals(scope)) {
            sb.append("== Minecraft Wiki ==\n").append(searchMediaWiki(MC_WIKI, query)).append("\n");
        }
        if ("wiki".equals(scope) || "both".equals(scope)) {
            sb.append("== Wikipedia ==\n").append(searchMediaWiki(WIKIPEDIA, query));
        }
        String out = sb.toString().trim();
        // MediaWiki 无结果时回退到通用网络搜索
        return out.isEmpty() ? searchBing(query) : out;
    }

    private String searchMediaWiki(String apiBase, String query) {
        try {
            String url = apiBase + "?action=query&list=search&format=json&srlimit=3&srsearch="
                    + URLEncoder.encode(query, StandardCharsets.UTF_8);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(12))
                    .header("User-Agent", UA)
                    .GET()
                    .build();
            HttpResponse<String> resp = HTTP.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (resp.statusCode() / 100 != 2) {
                return "（搜索失败 HTTP " + resp.statusCode() + "）";
            }
            JsonObject json = GSON.fromJson(resp.body(), JsonObject.class);
            if (json == null || !json.has("query")) {
                return "（无结果）";
            }
            JsonArray results = json.getAsJsonObject("query").getAsJsonArray("search");
            if (results == null || results.size() == 0) {
                return "（无结果）";
            }
            StringBuilder sb = new StringBuilder();
            for (JsonElement e : results) {
                JsonObject o = e.getAsJsonObject();
                String title = o.get("title").getAsString();
                String snippet = o.has("snippet") ? o.get("snippet").getAsString() : "";
                snippet = snippet.replaceAll("<[^>]+>", "").replaceAll("&[a-zA-Z]+;", " ").trim();
                sb.append("• ").append(title);
                if (!snippet.isEmpty()) {
                    sb.append("：").append(snippet);
                }
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception ex) {
            return "（搜索异常：" + ex.getMessage() + "）";
        }
    }

    /** 通用网络搜索（Bing 中文站），解析结果并让 wiki 类结果优先，适合模组等信息。 */
    private String searchBing(String query) {
        try {
            String url = BING + "?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&setlang=zh-CN";
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(12))
                    .header("User-Agent", BROWSER_UA)
                    .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                    .GET()
                    .build();
            HttpResponse<String> resp = HTTP.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (resp.statusCode() / 100 != 2) {
                return "（网络搜索失败 HTTP " + resp.statusCode() + "）";
            }
            return parseBing(resp.body());
        } catch (Exception ex) {
            return "（网络搜索异常：" + ex.getMessage() + "）";
        }
    }

    /** 解析 Bing 结果页：提取每条结果的标题/链接/摘要，wiki 类结果优先排前。 */
    private String parseBing(String html) {
        List<String[]> results = new ArrayList<>();
        String[] blocks = html.split("<li class=\"b_algo\">");
        Pattern link = Pattern.compile("<h2>.*?<a[^>]+href=\"(https?://[^\"]+)\"[^>]*>(.*?)</a>", Pattern.DOTALL);
        Pattern para = Pattern.compile("<p[^>]*>(.*?)</p>", Pattern.DOTALL);
        for (int i = 1; i < blocks.length && results.size() < 8; i++) {
            Matcher lm = link.matcher(blocks[i]);
            if (!lm.find()) {
                continue;
            }
            String u = lm.group(1);
            String t = stripHtml(lm.group(2));
            if (t.isEmpty()) {
                continue;
            }
            Matcher pm = para.matcher(blocks[i]);
            String s = pm.find() ? stripHtml(pm.group(1)) : "";
            results.add(new String[]{t, u, s});
        }
        if (results.isEmpty()) {
            return "（未从网络搜索到结果，可换个关键词重试）";
        }
        results.sort((a, b) -> Integer.compare(wikiRank(b[1]), wikiRank(a[1])));
        StringBuilder sb = new StringBuilder("== 网络搜索结果（wiki 优先）==\n");
        int n = 0;
        for (String[] r : results) {
            if (n++ >= 6) {
                break;
            }
            sb.append("• ").append(r[0]).append("\n  ").append(r[1]);
            if (!r[2].isEmpty()) {
                sb.append("\n  ").append(trimLen(r[2], 200));
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    /** wiki 类域名给更高优先级排序。 */
    private static int wikiRank(String url) {
        String u = url.toLowerCase();
        return (u.contains("wiki") || u.contains("fandom") || u.contains("mcmod")) ? 1 : 0;
    }

    private static String stripHtml(String s) {
        return s.replaceAll("<[^>]+>", "").replaceAll("&[a-zA-Z]+;", " ")
                .replaceAll("&#\\d+;", " ").trim();
    }

    private static String trimLen(String s, int max) {
        return s.length() <= max ? s : s.substring(0, max) + "…";
    }
}
