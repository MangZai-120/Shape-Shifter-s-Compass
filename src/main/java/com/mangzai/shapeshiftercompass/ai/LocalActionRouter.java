package com.mangzai.shapeshiftercompass.ai;

import com.google.gson.JsonObject;
import java.util.List;
import java.util.regex.Pattern;

/** Only complete, explicit requests are executable here; questions and quoted examples stay with AI. */
public final class LocalActionRouter {
    private LocalActionRouter() {}
    public record Action(String tool, JsonObject args) {}
    private static final Pattern BOOK_EXP = Pattern.compile(
            "^(?:请|麻烦)?(?:帮我)?给(?:我)?(?<where>身上(?:装备|佩戴)?|当前装备|装备|佩戴|手持|主手|副手|手里|手上)?的?(?:月尘魔法书|魔法书|书)(?:添|增)?加(?<amount>[0-9]+)(?:点|\\.0点)?经验[。！!]*$");
    public static Action parse(String text) {
        if (text == null) return null;
        var match = BOOK_EXP.matcher(text.strip().replaceAll("\\s+", ""));
        if (!match.matches()) return null;
        String where = match.group("where");
        String target = where == null || where.equals("身上") ? "auto"
                : List.of("手持", "主手", "副手", "手里", "手上").contains(where) ? "held" : "equipped";
        JsonObject args = new JsonObject(); args.addProperty("amount", match.group("amount")); args.addProperty("target", target);
        if ("主手".equals(where)) args.addProperty("hand", "main");
        if ("副手".equals(where)) args.addProperty("hand", "off");
        return new Action("add_spellbook_experience", args);
    }
    public static Action latest(List<ChatMessage> messages) {
        if (messages.isEmpty()) return null;
        var last = messages.get(messages.size()-1);
        return "user".equals(last.role) ? parse(last.content) : null;
    }
}
