package com.mangzai.shapeshiftercompass.tools;

/** Explicit locations never fall back to another book. Generic requests prefer the equipped book. */
public final class SpellbookTargeting {
    private SpellbookTargeting() {}
    public static String resolve(String requested, boolean held, boolean equipped) {
        if (requested.equals("auto")) {
            if (equipped) return "equipped";
            if (held) return "held";
            throw new IllegalArgumentException("当前主副手和饰品槽都没有可操作的月尘魔法书。书仅在背包或未选中的快捷栏里时，请先拿到手上或佩戴。");
        }
        if (requested.equals("equipped") && equipped) return "equipped";
        if (requested.equals("held") && held) return "held";
        if (requested.equals("equipped")) throw new IllegalArgumentException("未执行：当前没有佩戴书。" + (held ? "手上有书，可改用target=held；不能据此说服务器不支持佩戴书。" : "请先佩戴月尘魔法书。"));
        if (requested.equals("held")) throw new IllegalArgumentException("未执行：当前主副手没有书。" + (equipped ? "饰品槽内有佩戴书，可改用target=equipped或auto；不能据此说服务器不支持佩戴书。" : "请先选中快捷栏中的魔法书或放到副手。"));
        throw new IllegalArgumentException("target只能是auto、equipped或held。");
    }
}
