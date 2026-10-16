package com.mangzai.shapeshiftercompass.ai;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mangzai.shapeshiftercompass.tools.AddSpellbookExperienceTool;
import com.mangzai.shapeshiftercompass.tools.CompassTool;
import com.mangzai.shapeshiftercompass.tools.ToolRegistry;
import java.util.concurrent.atomic.AtomicInteger;

public final class AiResponseRegressionTest {
    private static int checks;
    private static void check(boolean ok, String why) { checks++; if (!ok) throw new AssertionError(why); }
    private static String block(String prefix, String root, String value) {
        return "<"+prefix+root+">\n<"+prefix+"invoke name=\"add_spellbook_experience\">\n"
                + "<"+prefix+"parameter name=\"amount\" string=\"false\">"+value+"</"+prefix+"parameter>\n"
                + "<"+prefix+"parameter name=\"target\" string=\"true\">equipped</"+prefix+"parameter>\n"
                + "</"+prefix+"invoke>\n</"+prefix+root+">";
    }
    public static void run() {
        requestPolicy();
        commandTree();
        localActions();
        for (String prefix : new String[]{"｜DSML｜", "｜｜DSML｜｜", "|DSML|", "｜DSML｜ ", ""}) {
            for (String root : new String[]{"function_calls", "tool_calls", "calls"}) {
                var calls = TextToolCalls.parse(block(prefix, root, "1000"));
                check(calls.size() == 1, "complete official/gateway envelope parses: " + prefix + root);
                check(calls.get(0).name().equals("add_spellbook_experience")
                        && calls.get(0).args().get("amount").getAsInt() == 1000
                        && calls.get(0).args().get("amount").getAsJsonPrimitive().isNumber(), "typed amount retained");
                check(AddSpellbookExperienceTool.command(calls.get(0).args()).equals("ssc_addon moon_dust_spellbook exp addon 1000 equipped"), "1000 goes to explicit equipped server command");
            }
        }
        String text = block("｜DSML｜", "function_calls", "1000");
        for (String bad : new String[]{text.substring(0, text.length()-4), text.replace("</｜DSML｜invoke>", ""),
                text.replace("</｜DSML｜parameter>", ""), text.replace("1000", "[invalid"), "```xml\n"+text+"\n```",
                "Example:\n"+text, text + " explanatory tail", "<ssc_addon moon_dust_spellbook exp addon 1000", ""}) {
            check(TextToolCalls.parse(bad).isEmpty(), "truncated/malformed/example output never executes");
        }
        check(TextToolCalls.parse(text.replace("<｜DSML｜parameter name=\"target\"", "<｜DSML｜parameter name=\"amount\"")).isEmpty(), "duplicate arguments rejected atomically");
        check(TextToolCalls.containsMarkup(text) && !TextToolCalls.containsMarkup("Added 1000 experience."), "bad markup is not a successful natural language reply");
        var outcomes = new ToolOutcomes();
        JsonObject args = JsonParser.parseString("{\"amount\":1000,\"target\":\"equipped\"}").getAsJsonObject();
        check(outcomes.fallback("Empty response").contains("未执行任何"), "empty model reply accurately says no tools ran");
        outcomes.record("add_spellbook_experience", args, "已为佩戴的月尘魔法书增加1000点经验，当前经验为1000.0。");
        check(outcomes.previousMutation("add_spellbook_experience", JsonParser.parseString("{\"target\":\"equipped\",\"amount\":1000}").getAsJsonObject()) != null, "argument order cannot bypass duplicate grant protection");
        check(outcomes.fallback("Empty response").contains("当前经验为1000.0"), "actual result survives empty summary");
        check(new ToolOutcomes().previousMutation("add_spellbook_experience", args) == null, "new player request can legitimately grant again");
        JsonObject timeout = new JsonObject(); timeout.addProperty("command", "ssc_addon moon_dust_spellbook exp addon 1000 equipped");
        outcomes.record("run_command", timeout, "命令已发送；结果未确认。");
        check(outcomes.previousMutation("run_command", timeout).contains("未确认"), "uncertain command result is never automatically repeated");
        check(outcomes.previousMutation("run_command", timeout) != null && sessionKeyEquivalent(args, timeout), "dedicated book tool and generic command share duplicate protection");
        check(outcomes.fallback("HTTP 500").contains("未确认"), "HTTP failure after command preserves uncertainty");
        outcomes.record("get_spellbook_status", new JsonObject(), "Exp: 1000.0");
        check(outcomes.previousMutation("get_spellbook_status", new JsonObject()) == null, "read-only tools remain refreshable");
        AtomicInteger sent = new AtomicInteger();
        ToolRegistry.register(new CompassTool() {
            public String name() { return "add_spellbook_experience"; }
            public String description() { return "fake server"; }
            public JsonObject parameters() { return new JsonObject(); }
            public String execute(JsonObject a) { sent.incrementAndGet(); return "server confirmed +1000"; }
        });
        var session = new ToolOutcomes();
        for (int round=0; round<3; round++) for (var call : TextToolCalls.parse(text)) {
            if (session.previousMutation(call.name(), call.args()) == null)
                session.record(call.name(), call.args(), ToolRegistry.execute(call.name(), call.args()));
        }
        check(sent.get() == 1 && session.fallback("no model text").contains("server confirmed +1000"), "DSML, execution, repeated recovery and fallback grant exactly once");
        ToolRegistry.register(new AddSpellbookExperienceTool());
        check(AddSpellbookExperienceTool.command(JsonParser.parseString("{\"amount\":1000,\"target\":\"held\"}").getAsJsonObject())
                .equals("ssc_addon moon_dust_spellbook exp addon 1000"), "held keeps existing server syntax");
        check(new AddSpellbookExperienceTool().parameters().getAsJsonObject("properties").getAsJsonObject("target").getAsJsonArray("enum").get(0).getAsString().equals("auto"), "generic requests use live auto target");
        for (String invalid : new String[]{"{}", "{\"amount\":0}", "{\"amount\":-1000}", "{\"amount\":1.5}", "{\"amount\":2147483648}", "{\"amount\":1000,\"target\":\"all\"}"}) {
            try { AddSpellbookExperienceTool.command(JsonParser.parseString(invalid).getAsJsonObject()); throw new AssertionError("invalid grant accepted"); }
            catch (IllegalArgumentException expected) { checks++; }
        }
        for (String failure : new String[]{"请先在主手或副手持有月尘魔法书。", "请先佩戴月尘魔法书；equipped 指令只修改饰品栏的书。", "经验数值过大，当前魔法书最多还能增加0点经验。", "Equip a Moon Dust Spellbook first; the equipped command only modifies the worn book."})
            check(CommandFeedbackSuppressor.isFailureFeedback(failure), "book refusal is a failure");
        check(!CommandFeedbackSuppressor.isFailureFeedback("已为佩戴的月尘魔法书增加1000点经验，当前经验为1000.0。"), "successful book grant is not a failure");
        check(TextToolCalls.arguments(JsonParser.parseString("\"{\\\"amount\\\":1000}\"")).get("amount").getAsInt() == 1000, "standard string arguments decode");
        check(TextToolCalls.arguments(args).get("amount").getAsInt() == 1000, "gateway object arguments decode");
        for (String bad : new String[]{"\"{broken\"", "[]", "42", "\"\""}) {
            try { TextToolCalls.arguments(JsonParser.parseString(bad)); throw new AssertionError("bad arguments accepted"); }
            catch (RuntimeException expected) { checks++; }
        }
        System.out.println("Compass AI response regression PASS: " + checks + " checks; DSML calls, typed amounts, exact target, incomplete reply rejection, result fallback and duplicate-grant prevention. No AI service called.");
    }
    private static boolean sessionKeyEquivalent(JsonObject args, JsonObject command) {
        var outcomes = new ToolOutcomes(); outcomes.record("add_spellbook_experience", args, "confirmed");
        return "confirmed".equals(outcomes.previousMutation("run_command", command));
    }

    private static void requestPolicy() {
        var cfg = new com.mangzai.shapeshiftercompass.config.CompassConfig();
        cfg.baseUrl = "https://api.deepseek.com"; cfg.model = "deepseek-flash";
        cfg.maxTokens = 1024; cfg.thinkingEnabled = true; cfg.reasoningEffort = "max";
        var policy = new AiRequestPolicy();
        JsonObject first = new JsonObject(); first.addProperty("max_tokens", cfg.maxTokens); policy.configure(first, cfg);
        check(first.getAsJsonObject("thinking").get("type").getAsString().equals("enabled") && first.get("reasoning_effort").getAsString().equals("max"), "DeepSeek thinking setting sent explicitly");
        check(policy.recoverTruncatedThinking(cfg, "length", "", false, false), "exact logged empty length response triggers bounded recovery");
        JsonObject retry = new JsonObject(); retry.addProperty("max_tokens", cfg.maxTokens); policy.configure(retry, cfg);
        check(retry.getAsJsonObject("thinking").get("type").getAsString().equals("disabled") && !retry.has("reasoning_effort"), "truncated request retries without thinking or max effort");
        check(retry.get("max_tokens").getAsInt() == 1024 && cfg.maxTokens == 1024 && cfg.thinkingEnabled && cfg.reasoningEffort.equals("max"), "recovery cannot raise token cap or change player configuration");
        check(!policy.recoverTruncatedThinking(cfg, "length", "", false, false), "same request cannot repeat thinking recovery");
        check(!new AiRequestPolicy().recoverTruncatedThinking(cfg, "length", "", false, true), "completed tool result skips another AI request");
        check(!new AiRequestPolicy().recoverTruncatedThinking(cfg, "length", "", true, false), "a real tool call is never discarded for recovery");
        check(!new AiRequestPolicy().recoverTruncatedThinking(cfg, "stop", "", false, false), "ordinary empty response is distinct from token truncation");
        check(!new AiRequestPolicy().recoverTruncatedThinking(cfg, "length", "partial answer", false, false), "partial answer is not overwritten or blindly retried");
        cfg.thinkingEnabled = false;
        JsonObject disabled = new JsonObject(); new AiRequestPolicy().configure(disabled, cfg);
        check(disabled.getAsJsonObject("thinking").get("type").getAsString().equals("disabled") && !disabled.has("reasoning_effort"), "turning thinking off explicitly disables DeepSeek default thinking");
        check(!new AiRequestPolicy().recoverTruncatedThinking(cfg, "length", "", false, false), "already disabled thinking cannot trigger retry");
        cfg.baseUrl = "https://example.test/v1"; cfg.thinkingEnabled = true;
        JsonObject other = new JsonObject(); new AiRequestPolicy().configure(other, cfg);
        check(!other.has("thinking") && other.get("reasoning_effort").getAsString().equals("max"), "other API protocols retain existing fields");
        check(!new AiRequestPolicy().recoverTruncatedThinking(cfg, "length", "", false, false), "unknown gateway never receives unsupported thinking switch");
        check(AiRequestPolicy.emptyReason("length").contains("输出上限"), "truncation tells the player the actual reason");
        cfg.baseUrl = "https://api.deepseek.com";
        var reads = new ToolOutcomes(); reads.record("get_spellbook_status",new JsonObject(),"equipped");
        check(reads.hasResults() && !reads.hasMutations(), "read-only results are distinct from a mutation");
        check(new AiRequestPolicy().recoverTruncatedThinking(cfg,"length","",false,reads.hasMutations()), "logged truncation after book inspection can recover and complete the requested operation");
        reads.record("add_spellbook_experience",JsonParser.parseString("{\"amount\":1000,\"target\":\"equipped\"}").getAsJsonObject(),"confirmed");
        check(!new AiRequestPolicy().recoverTruncatedThinking(cfg,"length","",false,reads.hasMutations()), "grant completion does not trigger another model-driven mutation");
    }

    private static void localActions() {
        String[] generic={"给我身上的书加1000点经验","给我的书增加1000点经验","请帮我给魔法书添加1000点经验！","给我书加1000经验"};
        for (String text : generic) {
            var action=LocalActionRouter.parse(text);
            check(action!=null && action.tool().equals("add_spellbook_experience") && action.args().get("amount").getAsInt()==1000,"actual player wording routes directly: "+text);
            check(action.args().get("target").getAsString().equals("auto"),"generic wording leaves location to runtime inspection");
            for (boolean held:new boolean[]{false,true}) for(boolean equipped:new boolean[]{false,true}) {
                try {
                    String target=com.mangzai.shapeshiftercompass.tools.SpellbookTargeting.resolve("auto",held,equipped);
                    var args=action.args().deepCopy();args.addProperty("target",target);
                    String command=AddSpellbookExperienceTool.command(args);
                    check(command.equals("ssc_addon moon_dust_spellbook exp addon 1000"+(equipped?" equipped":"")),"same request selects correct real held/equipped command");
                    check(held || equipped,"a book must exist at an eligible location");
                } catch (IllegalArgumentException missing) { check(!held && !equipped,"only an actually absent book is rejected"); }
            }
        }
        for(String text:new String[]{"如何给我身上的书加1000点经验","不要给我身上的书加1000点经验","不能给我身上的书加1000点经验","给我身上的书加1000点经验吗？","\"给我身上的书加1000点经验\"","给我身上的书加1000点经验，然后删除存档"})
            check(LocalActionRouter.parse(text)==null,"questions, negations, examples and extra actions never execute locally");
        check(LocalActionRouter.parse("给身上装备的书添加1000点经验").args().get("target").getAsString().equals("equipped"),"explicit equipped wording retains its target");
        check(LocalActionRouter.parse("给我手持的书加1000点经验").args().get("target").getAsString().equals("held"),"explicit held wording retains its target");
        check(LocalActionRouter.parse("给我副手的书加1000点经验").args().get("hand").getAsString().equals("off"),"specific offhand intent retained for preflight rejection when main hand also has a book");
        var history=java.util.List.of(new ChatMessage("assistant","服务器不支持佩戴书，改用手持"),new ChatMessage("user","给我身上的书加1000点经验"));
        check(LocalActionRouter.latest(history).args().get("target").getAsString().equals("auto"),"stale assistant answer cannot override new explicit action");
        check(LocalActionRouter.latest(java.util.List.of(new ChatMessage("user",generic[0]),new ChatMessage("assistant","confirmed")))==null,"old request is not replayed when no new user action exists");
        for(String target:new String[]{"held","equipped"}) {
            try { com.mangzai.shapeshiftercompass.tools.SpellbookTargeting.resolve(target,target.equals("equipped"),target.equals("held"));throw new AssertionError("wrong book selected"); }
            catch(IllegalArgumentException expected) { check(expected.getMessage().contains("不能据此说服务器不支持"),"missing target does not become a false server capability claim"); }
        }
        var feedback=CommandFeedbackSuppressor.awaitSpellbookFeedback();
        check(!CommandFeedbackSuppressor.shouldSuppress(net.minecraft.text.Text.literal("ordinary unrelated chat")) && !feedback.isDone(),"unrelated chat cannot confirm an experience grant");
        check(CommandFeedbackSuppressor.shouldSuppress(net.minecraft.text.Text.literal("已为佩戴的月尘魔法书增加1000点经验，当前经验为1000.0。"))
                && feedback.join().contains("1000.0"),"real book feedback confirms the grant");
        CommandFeedbackSuppressor.endSuppression();
    }

    private static void commandTree() {
        for (boolean updated : new boolean[]{false, true}) {
            var server = new com.mojang.brigadier.CommandDispatcher<net.minecraft.command.CommandSource>();
            var amount = com.mojang.brigadier.builder.RequiredArgumentBuilder.<net.minecraft.command.CommandSource, Integer>argument(
                    "amount", com.mojang.brigadier.arguments.IntegerArgumentType.integer(1)).executes(context -> 1);
            if (updated) amount.then(com.mojang.brigadier.builder.LiteralArgumentBuilder.<net.minecraft.command.CommandSource>literal("equipped").executes(context -> 1));
            server.register(com.mojang.brigadier.builder.LiteralArgumentBuilder.<net.minecraft.command.CommandSource>literal("ssc_addon")
                    .then(com.mojang.brigadier.builder.LiteralArgumentBuilder.<net.minecraft.command.CommandSource>literal("moon_dust_spellbook")
                    .then(com.mojang.brigadier.builder.LiteralArgumentBuilder.<net.minecraft.command.CommandSource>literal("exp")
                    .then(com.mojang.brigadier.builder.LiteralArgumentBuilder.<net.minecraft.command.CommandSource>literal("addon").then(amount)))));
            var buffer = new net.minecraft.network.PacketByteBuf(io.netty.buffer.Unpooled.buffer());
            try {
                net.minecraft.network.packet.s2c.play.CommandTreeS2CPacket.CODEC.encode(buffer,
                        new net.minecraft.network.packet.s2c.play.CommandTreeS2CPacket(server.getRoot()));
                var received = net.minecraft.network.packet.s2c.play.CommandTreeS2CPacket.CODEC.decode(buffer);
                var client = new com.mojang.brigadier.CommandDispatcher<>(received.getCommandTree(null));
                var equipped = client.parse("ssc_addon moon_dust_spellbook exp addon 1000 equipped", null);
                check((!equipped.getReader().canRead() && equipped.getContext().getCommand() != null) == updated,
                        "real S2C command packet distinguishes old and equipped-capable servers");
                var held = client.parse("ssc_addon moon_dust_spellbook exp addon 1000", null);
                check(!held.getReader().canRead() && held.getContext().getCommand() != null,
                        "real S2C command packet preserves held command executable marker");
            } finally { buffer.release(); }
        }
    }
}
