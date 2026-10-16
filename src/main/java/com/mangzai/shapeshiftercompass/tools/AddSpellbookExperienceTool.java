package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.ai.CheatGuard;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/** Requests the server command; never edits a client book mirror or moves equipment. */
public final class AddSpellbookExperienceTool implements CompassTool {
    @Override public String name() { return "add_spellbook_experience"; }
    @Override public boolean async() { return true; }
    @Override public String description() {
        return "给自己的月尘魔法书增加正整数经验。通常省略target或使用auto，按本次实时读数优先佩戴书、其次手持书。equipped只选佩戴书，held只选主副手书。"
                + "需要罗盘作弊开关和 OP2，由服务端指令修改；不是玩家经验，不会直接升级或补法力。仅玩家明确要求增加经验时调用一次。";
    }
    @Override public JsonObject parameters() {
        JsonObject amount = new JsonObject(); amount.addProperty("type", "integer");
        amount.addProperty("minimum", 1); amount.addProperty("maximum", Integer.MAX_VALUE);
        amount.addProperty("description", "增加的实际经验点，例如1000表示1000.0经验。");
        JsonObject target = new JsonObject(); target.addProperty("type", "string");
        JsonArray choices = new JsonArray(); choices.add("auto"); choices.add("equipped"); choices.add("held"); target.add("enum", choices);
        JsonObject props = new JsonObject(); props.add("amount", amount); props.add("target", target);
        JsonArray required = new JsonArray(); required.add("amount");
        JsonObject schema = new JsonObject(); schema.addProperty("type", "object");
        schema.add("properties", props); schema.add("required", required); return schema;
    }
    public static String command(JsonObject args) {
        int amount;
        try { amount = new BigDecimal(args.get("amount").getAsString()).intValueExact(); }
        catch (RuntimeException invalid) { throw new IllegalArgumentException("amount 必须是1到2147483647之间的正整数。"); }
        if (amount < 1) throw new IllegalArgumentException("amount 必须是正整数。");
        String target = args.has("target") ? args.get("target").getAsString() : "auto";
        if (target.equals("auto")) throw new IllegalArgumentException("auto目标必须先根据本次实际佩戴/手持状态解析。");
        if (!target.equals("equipped") && !target.equals("held")) throw new IllegalArgumentException("target 只能是 auto、equipped 或 held。");
        return "ssc_addon moon_dust_spellbook exp addon " + amount + (target.equals("equipped") ? " equipped" : "");
    }
    @Override public String execute(JsonObject args) {
        MinecraftClient mc = MinecraftClient.getInstance();
        CompletableFuture<String> preflight = new CompletableFuture<>();
        mc.execute(() -> {
            try {
                if (!CheatGuard.isCheatAllowed()) { preflight.complete("拒绝：请先开启罗盘作弊功能，并拥有服务器 OP2 权限。"); return; }
                if (!FabricLoader.getInstance().isModLoaded("ssc_addon")) { preflight.complete("错误：未安装 SSCA 附属。"); return; }
                if (mc.player == null || mc.player.networkHandler == null) { preflight.complete("错误：玩家未在世界中。"); return; }
                Object raw = AddonAccess.call("client.SpellcastClient", "getEquippedBook", new Class<?>[0]);
                boolean equipped = raw instanceof net.minecraft.item.ItemStack book && !book.isEmpty();
                Class<?> type = AddonAccess.type("item.MoonDustSpellbookItem");
                boolean mainBook = type.isInstance(mc.player.getMainHandStack().getItem());
                boolean offBook = type.isInstance(mc.player.getOffHandStack().getItem());
                boolean held = mainBook || offBook;
                String hand = args.has("hand") ? args.get("hand").getAsString() : "";
                if (hand.equals("main") && !mainBook) { preflight.complete("未执行：你明确指定的主手没有月尘魔法书。"); return; }
                if (hand.equals("off") && (!offBook || mainBook)) { preflight.complete("未执行：副手没有月尘魔法书，或主手也有另一本书。原手持指令优先主手，请先清空主手以确保只修改指定副手书。"); return; }
                String requested = args.has("target") ? args.get("target").getAsString() : "auto";
                String resolved = SpellbookTargeting.resolve(requested, held, equipped);
                JsonObject effective = args.deepCopy(); effective.addProperty("target", resolved);
                String command = command(effective);
                var network = mc.player.networkHandler;
                var parsed = network.getCommandDispatcher().parse(command, network.getCommandSource());
                if (parsed.getReader().canRead() || parsed.getContext().getCommand() == null) {
                    preflight.complete(command.endsWith(" equipped")
                            ? "未执行：服务器尚不支持给佩戴魔法书加经验的 equipped 指令。请更新对应 SSC 版本的 SSCA 服务端；旧版只支持主副手的书。"
                            : "未执行：服务器未提供月尘魔法书经验指令，请检查 SSCA 版本和 OP2 权限。");
                    return;
                }
                preflight.complete(command);
            } catch (IllegalArgumentException invalid) { preflight.complete("未执行：" + invalid.getMessage()); }
            catch (Exception failure) { preflight.complete("未执行：无法检查当前书或服务器指令：" + failure.getMessage()); }
        });
        final String command;
        try {
            command = preflight.get(5, TimeUnit.SECONDS);
            if (!command.startsWith("ssc_addon moon_dust_spellbook exp addon ")) return command;
        } catch (Exception failure) { return "未执行：检查服务器指令超时或失败。"; }
        JsonObject request = new JsonObject(); request.addProperty("command", command);
        String location = command.endsWith(" equipped") ? "饰品槽内佩戴的月尘魔法书" : "主手优先、副手其次的手持月尘魔法书";
        com.mangzai.shapeshiftercompass.ShapeShifterCompass.LOGGER.info("[Compass] spellbook EXP target={} command={}", location, command);
        return "本次目标：" + location + "。\n" + new RunCommandTool().execute(request);
    }
}
