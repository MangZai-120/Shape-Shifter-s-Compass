package com.mangzai.shapeshiftercompass.ui;

import com.mangzai.shapeshiftercompass.ai.AiClient;
import com.mangzai.shapeshiftercompass.ai.ChatMessage;
import com.mangzai.shapeshiftercompass.conversation.Conversation;
import com.mangzai.shapeshiftercompass.conversation.ConversationStore;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Phase 1 第⑤步聊天界面：微信式气泡（自己右侧蓝、AI 左侧灰绿）、本地打字机效果、
 * 滚动条、会话占用 tooltip；左侧会话列表可新建/切换/删除，点击自己的消息可编辑重生成。
 */
public class ChatScreen extends Screen {
    private final Screen parent;
    private boolean compact;
    private TextFieldWidget input;
    private boolean waiting = false;
    private int editingIndex = -1;
    /** 内联编辑：在原气泡位置显示一个输入框 */
    private TextFieldWidget editField = null;
    private int editBubbleX = 0;
    private int editBubbleW = 0;
    private int editBubbleY = 0;
    private int editBubbleH = 0;
    /** 动态思考动画计数器 */
    private int thinkTick = 0;
    /** 输入框 ↑/↓ 历史导航 */
    private final InputHistory inputHistory = new InputHistory();
    /** 发送/停止按钮引用（动态切换文字） */
    private ButtonWidget sendBtn;
    private int scroll = 0;
    private int maxScroll = 0;
    private boolean autoScroll = true;
    private int typingMsgIndex = -1;
    private int typingChars = 0;
    private String voiceStatus = "";

    private int listX;
    private int listW;
    private int chatX;
    private int chatW;
    private int panelY;
    private int panelH;

    private final List<int[]> msgHitboxes = new ArrayList<>();

    public ChatScreen(Screen parent) {
        this(parent, false);
    }

    public ChatScreen(Screen parent, boolean compact) {
        super(Text.translatable("ssc_compass.screen.title"));
        this.parent = parent;
        this.compact = compact;
    }

    private Conversation conv() {
        return ConversationStore.current();
    }

    @Override
    protected void init() {
        if (compact) {
            int pw = Math.min(220, this.width - 16);
            int ph = Math.min(170, this.height - 16);
            chatX = this.width - pw - 8;
            chatW = pw;
            panelY = this.height - ph - 8;
            panelH = ph;
            listX = 0;
            listW = 0;
        } else {
            panelY = 8;
            panelH = this.height - 16;
            listX = 8;
            listW = 96;
            chatX = listX + listW + 8;
            chatW = this.width - chatX - 8;
        }

        if (!compact) {
            addDrawableChild(ButtonWidget.builder(Text.literal("+ 新对话"), b -> {
                ConversationStore.newConversation();
                editingIndex = -1;
                scroll = 0;
                typingMsgIndex = -1;
                autoScroll = true;
                this.clearAndInit();
            }).dimensions(listX, panelY + 2, listW, 16).build());

            int y = panelY + 22;
            for (Conversation c : new ArrayList<>(ConversationStore.all())) {
                boolean cur = c == conv();
                String label = (cur ? "▶" : "") + c.title;
                ButtonWidget sw = ButtonWidget.builder(Text.literal(trim(label, 11)), b -> {
                    ConversationStore.switchTo(c);
                    editingIndex = -1;
                    scroll = 0;
                    typingMsgIndex = -1;
                    autoScroll = true;
                    this.clearAndInit();
                }).dimensions(listX, y, listW - 15, 16).build();
                sw.setTooltip(Tooltip.of(Text.literal(
                        c.estimateTokens() + " tok · " + ConversationStore.humanBytes(ConversationStore.bytesOf(c)))));
                addDrawableChild(sw);
                addDrawableChild(ButtonWidget.builder(Text.literal("×"), b -> {
                    ConversationStore.delete(c);
                    editingIndex = -1;
                    this.clearAndInit();
                }).dimensions(listX + listW - 14, y, 14, 16).build());
                y += 18;
                if (y > panelY + panelH - 26) {
                    break;
                }
            }
        }

        int inputY = panelY + panelH - 24;
        input = new TextFieldWidget(this.textRenderer, chatX + 4, inputY, chatW - 60, 18,
                Text.translatable("ssc_compass.input.placeholder"));
        input.setMaxLength(4000);
        input.setPlaceholder(Text.translatable("ssc_compass.input.placeholder"));
        addSelectableChild(input);
        setInitialFocus(input);

        sendBtn = ButtonWidget.builder(Text.translatable("ssc_compass.button.send"), b -> {
                    if (com.mangzai.shapeshiftercompass.ai.CompassState.isBusy()) {
                        com.mangzai.shapeshiftercompass.ai.AiClient.cancel();
                        waiting = false;
                    } else {
                        onSend();
                    }
                })
                .dimensions(chatX + chatW - 52, inputY - 1, 48, 20).build();
        addDrawableChild(sendBtn);

        addDrawableChild(ButtonWidget.builder(Text.literal("⚙"), b -> this.client.setScreen(new ConfigScreen(this)))
                .dimensions(chatX + chatW - 16, panelY + 2, 14, 14)
                .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.translatable("ssc_compass.button.settings")))
                .build());

        addDrawableChild(ButtonWidget.builder(Text.literal(compact ? "⬜" : "⚊"), b -> {
            compact = !compact;
            scroll = 0;
            this.clearAndInit();
        }).dimensions(chatX + chatW - 32, panelY + 2, 14, 14).build());

        if (compact) {
            addDrawableChild(ButtonWidget.builder(Text.literal("♪"), b -> onVoice())
                    .dimensions(chatX + chatW - 48, panelY + 2, 14, 14)
                    .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.translatable("ssc_compass.button.voice")))
                    .build());
        }
    }

    private void onSend() {
        if (waiting || com.mangzai.shapeshiftercompass.ai.CompassState.isBusy()) {
            return;
        }
        String text = input.getText().trim();
        if (text.isEmpty()) {
            return;
        }
        Conversation c = conv();
        c.messages.add(new ChatMessage("user", text));
        c.autoTitle();
        input.setText("");
        inputHistory.reset();
        waiting = true;
        autoScroll = true;
        ConversationStore.save();
        AiClient.sendChat(new ArrayList<>(c.messages),
                reply -> {
                    c.messages.add(new ChatMessage("assistant", reply));
                    startTyping(c.messages.size() - 1);
                    CompassHud.setLastAnswer(reply);
                    waiting = false;
                    ConversationStore.save();
                },
                err -> {
                    if ("cancelled".equals(err)) {
                        waiting = false;
                        return;
                    }
                    String msg = "no_key".equals(err)
                            ? Text.translatable("ssc_compass.msg.no_key").getString()
                            : Text.translatable("ssc_compass.msg.error", err).getString();
                    c.messages.add(new ChatMessage("assistant", msg));
                    startTyping(c.messages.size() - 1);
                    waiting = false;
                    ConversationStore.save();
                });
    }

    private void startTyping(int index) {
        typingMsgIndex = index;
        typingChars = 0;
        autoScroll = true;
    }

    private void onVoice() {
        if (!com.mangzai.shapeshiftercompass.voice.VoiceBridge.svcAvailable()) {
            voiceStatus = Text.translatable("ssc_compass.voice.need_svc").getString();
        } else if (!com.mangzai.shapeshiftercompass.voice.VoiceBridge.sttReady()) {
            voiceStatus = Text.translatable("ssc_compass.voice.not_ready").getString();
        } else {
            voiceStatus = Text.translatable("ssc_compass.voice.listening").getString();
        }
    }

    @Override
    public void tick() {
        if (typingMsgIndex >= 0) {
            Conversation c = conv();
            if (typingMsgIndex < c.messages.size()) {
                ChatMessage m = c.messages.get(typingMsgIndex);
                int len = m.content == null ? 0 : m.content.length();
                typingChars += 3;
                if (typingChars >= len) {
                    typingChars = len;
                    typingMsgIndex = -1;
                }
                autoScroll = true;
            } else {
                typingMsgIndex = -1;
            }
        }
        if (editField != null) {
            editField.tick();
        }
        // 动态思考动画：点数 0→1→2→3 循环，每点间隔 5tick，3 点→0点间隔 10tick
        if (waiting || com.mangzai.shapeshiftercompass.ai.CompassState.isBusy()) {
            thinkTick++;
        } else {
            thinkTick = 0;
        }
        // 发送键在 busy 时变红色「停止」文字
        if (sendBtn != null) {
            boolean busy = com.mangzai.shapeshiftercompass.ai.CompassState.isBusy();
            sendBtn.setMessage(busy
                    ? Text.translatable("ssc_compass.button.stop")
                    : Text.translatable("ssc_compass.button.send"));
        }
    }

    /** 当前思考文字（带动态点）。 */
    private String thinkingText() {
        int phase = thinkTick / 5;
        if (phase > 3) {
            // 3 点→0 点的间隔补到 10tick：phase=3 保持，直到 10tick 后归零
            if (thinkTick >= 3 * 5 + 10) {
                thinkTick = 0;
            }
            phase = 3;
        }
        // 动态点使用普通半角句点 .，点之间用一个空格分隔：. / . . / . . .
        StringBuilder dots = new StringBuilder();
        for (int i = 0; i < phase; i++) {
            if (i > 0) {
                dots.append(' ');
            }
            dots.append('.');
        }
        return Text.translatable("ssc_compass.msg.thinking").getString() + dots;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // 内联编辑框中回车=提交，ESC=取消
        if (editField != null && editField.isFocused()) {
            if (keyCode == 257 || keyCode == 335) {
                confirmEdit();
                return true;
            }
            if (keyCode == 256) {
                cancelEdit();
                return true;
            }
            return editField.keyPressed(keyCode, scanCode, modifiers)
                    || super.keyPressed(keyCode, scanCode, modifiers);
        }
        if ((keyCode == 257 || keyCode == 335) && input.isFocused()) {
            onSend();
            return true;
        }
        if (input.isFocused() && inputHistory.handle(keyCode, input, userHistory())) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    /** 当前会话已发送的用户消息（最近的在前），供输入框 ↑/↓ 历史导航。 */
    private java.util.List<String> userHistory() {
        java.util.List<String> list = new java.util.ArrayList<>();
        for (ChatMessage m : conv().messages) {
            if ("user".equals(m.role) && m.content != null && !m.content.isEmpty()) {
                list.add(m.content);
            }
        }
        java.util.Collections.reverse(list);
        return list;
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (editField != null && editField.isFocused()) {
            return editField.charTyped(chr, modifiers);
        }
        return super.charTyped(chr, modifiers);
    }

    /** 确认内联编辑：用编辑后的文字重新生成。 */
    private void confirmEdit() {
        if (editField == null) {
            return;
        }
        String text = editField.getText().trim();
        Conversation c = conv();
        if (editingIndex >= 0 && editingIndex < c.messages.size() && !text.isEmpty()) {
            while (c.messages.size() > editingIndex) {
                c.messages.remove(c.messages.size() - 1);
            }
            c.messages.add(new ChatMessage("user", text));
            c.autoTitle();
            waiting = true;
            autoScroll = true;
            ConversationStore.save();
            AiClient.sendChat(new ArrayList<>(c.messages),
                    reply -> {
                        c.messages.add(new ChatMessage("assistant", reply));
                        startTyping(c.messages.size() - 1);
                        CompassHud.setLastAnswer(reply);
                        waiting = false;
                        ConversationStore.save();
                    },
                    err -> {
                        if ("cancelled".equals(err)) {
                            waiting = false;
                            return;
                        }
                        String msg = "no_key".equals(err)
                                ? Text.translatable("ssc_compass.msg.no_key").getString()
                                : Text.translatable("ssc_compass.msg.error", err).getString();
                        c.messages.add(new ChatMessage("assistant", msg));
                        startTyping(c.messages.size() - 1);
                        waiting = false;
                        ConversationStore.save();
                    });
        }
        editingIndex = -1;
        editField = null;
        this.setFocused(null);
    }

    /** 取消内联编辑（点错时不重新生成）。 */
    private void cancelEdit() {
        editingIndex = -1;
        editField = null;
        this.setFocused(null);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // 先处理内联编辑框的点击
        if (editField != null && mouseX >= editBubbleX && mouseX <= editBubbleX + editBubbleW
                && mouseY >= editBubbleY && mouseY <= editBubbleY + editBubbleH) {
            return editField.mouseClicked(mouseX, mouseY, button);
        }
        // 点击气泡进入内联编辑
        if (button == 0 && mouseX >= chatX && mouseX <= chatX + chatW) {
            for (int[] hb : msgHitboxes) {
                if (mouseY >= hb[1] && mouseY <= hb[2]) {
                    ChatMessage m = conv().messages.get(hb[0]);
                    if ("user".equals(m.role)) {
                        startInlineEdit(hb[0]);
                        return true;
                    }
                }
            }
        }
        // 编辑中点击其它任何地方 → 取消编辑
        if (editField != null) {
            cancelEdit();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    /** 在指定用户气泡位置开启内联编辑框。 */
    private void startInlineEdit(int msgIdx) {
        Conversation c = conv();
        if (msgIdx < 0 || msgIdx >= c.messages.size()) {
            return;
        }
        ChatMessage m = c.messages.get(msgIdx);
        if (!"user".equals(m.role)) {
            return;
        }
        // 计算气泡位置/尺寸（与渲染逻辑一致）
        int top = panelY + 26;
        float cfs = Math.max(0.5f, Math.min(2.0f,
                com.mangzai.shapeshiftercompass.config.CompassConfig.get().chatFontPct / 100.0f));
        int maxBubbleW = (int) (chatW * 0.72);
        int wrapW = Math.max(20, (int) (maxBubbleW / cfs));
        int lineHs = Math.max(1, Math.round(10 * cfs));
        // 找到该消息的 y 起点
        int y = top - scroll;
        for (int i = 0; i < msgIdx; i++) {
            ChatMessage mi = c.messages.get(i);
            if ("system".equals(mi.role)) continue;
            List<OrderedText> lines = this.textRenderer.wrapLines(Text.literal(mi.content == null ? "" : mi.content), wrapW);
            y += Math.max(1, lines.size()) * lineHs + 6 + 4;
        }
        List<OrderedText> lines = this.textRenderer.wrapLines(Text.literal(m.content == null ? "" : m.content), wrapW);
        int textW = 0;
        for (OrderedText line : lines) {
            textW = Math.max(textW, this.textRenderer.getWidth(line));
        }
        int bw = (int) (textW * cfs) + 10;
        editBubbleX = chatX + chatW - 6 - bw;
        editBubbleW = bw;
        editBubbleY = y;
        editBubbleH = Math.max(1, lines.size()) * lineHs + 6;
        editingIndex = msgIdx;
        editField = new TextFieldWidget(this.textRenderer, editBubbleX + 5, editBubbleY + 3, editBubbleW - 10, editBubbleH - 6,
                Text.literal(""));
        editField.setMaxLength(4000);
        editField.setText(m.content == null ? "" : m.content);
        addSelectableChild(editField);
        setInitialFocus(editField);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        scroll -= (int) (amount * 12);
        if (scroll < 0) {
            scroll = 0;
        }
        autoScroll = false;
        return true;
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx);

        if (!compact) {
            ctx.fill(listX - 2, panelY, listX + listW + 2, panelY + panelH, 0xA0000000);
            ctx.drawBorder(listX - 2, panelY, listW + 4, panelH, 0xFF3A3A3A);
            ctx.drawTextWithShadow(this.textRenderer,
                    Text.literal("总占用 " + ConversationStore.humanBytes(ConversationStore.totalBytes())),
                    listX, panelY + panelH - 12, 0xFF888888);
        }

        ctx.fill(chatX - 2, panelY, chatX + chatW + 2, panelY + panelH, 0xA0000000);
        ctx.drawBorder(chatX - 2, panelY, chatW + 4, panelH, 0xFF3A3A3A);
        Conversation c = conv();
        String head = trim(c.title, 14) + "  (" + c.estimateTokens() + " tok, "
                + ConversationStore.humanBytes(ConversationStore.bytesOf(c)) + ")";
        ctx.drawTextWithShadow(this.textRenderer, Text.literal(head), chatX + 4, panelY + 4, 0xFFFFFFFF);
        if (editingIndex >= 0) {
            ctx.drawTextWithShadow(this.textRenderer, Text.literal("[编辑中 · 发送将重新生成]"),
                    chatX + 4, panelY + 14, 0xFFFFC107);
        }
        if (!voiceStatus.isEmpty()) {
            ctx.drawTextWithShadow(this.textRenderer, Text.literal(voiceStatus),
                    chatX + 4, panelY + panelH - 40, 0xFF9AD0FF);
        }

        int top = panelY + 26;
        int bottom = panelY + panelH - 28;
        int maxBubbleW = (int) (chatW * 0.72);
        float cfs = Math.max(0.5f, Math.min(2.0f,
                com.mangzai.shapeshiftercompass.config.CompassConfig.get().chatFontPct / 100.0f));
        int lineHs = Math.max(1, Math.round(10 * cfs));
        int wrapW = Math.max(20, (int) (maxBubbleW / cfs));

        msgHitboxes.clear();
        ctx.enableScissor(chatX, top, chatX + chatW, bottom);
        int y = top - scroll;
        for (int i = 0; i < c.messages.size(); i++) {
            ChatMessage m = c.messages.get(i);
            if ("system".equals(m.role)) {
                continue;
            }
            boolean self = "user".equals(m.role);
            String content = m.content == null ? "" : m.content;
            if (i == typingMsgIndex && typingChars < content.length()) {
                content = content.substring(0, Math.max(0, typingChars));
            }
            List<OrderedText> lines = this.textRenderer.wrapLines(Text.literal(content), wrapW);
            int textW = 0;
            for (OrderedText line : lines) {
                textW = Math.max(textW, this.textRenderer.getWidth(line));
            }
            int bw = (int) (textW * cfs) + 10;
            int bh = Math.max(1, lines.size()) * lineHs + 6;
            int bx = self ? (chatX + chatW - 6 - bw) : (chatX + 6);
            int yStart = y;
            if (y + bh >= top && y <= bottom) {
                ctx.fill(bx, y, bx + bw, y + bh, self ? 0xE02A5A8A : 0xE02C332C);
                ctx.drawBorder(bx, y, bw, bh, self ? 0xFF4A8ACA : 0xFF4A5A4A);
                if (i != editingIndex) {
                    ctx.getMatrices().push();
                    ctx.getMatrices().scale(cfs, cfs, 1.0f);
                    float ly = y + 4;
                    for (OrderedText line : lines) {
                        ctx.drawTextWithShadow(this.textRenderer, line,
                                (int) ((bx + 5) / cfs), (int) (ly / cfs), 0xFFEFEFEF);
                        ly += lineHs;
                    }
                    ctx.getMatrices().pop();
                }
            }
            if (self) {
                msgHitboxes.add(new int[]{i, yStart, y + bh});
            }
            y += bh + 4;
        }
        if (waiting || com.mangzai.shapeshiftercompass.ai.CompassState.isBusy()) {
            if (typingMsgIndex < 0 && y <= bottom) {
                ctx.drawTextWithShadow(this.textRenderer, Text.literal(thinkingText()),
                        chatX + 6, y, 0xFFAAAAAA);
                y += 12;
            }
        }
        ctx.disableScissor();

        int contentH = (y + scroll) - top;
        int visibleH = bottom - top;
        maxScroll = Math.max(0, contentH - visibleH);
        if (autoScroll) {
            scroll = maxScroll;
        } else if (scroll > maxScroll) {
            scroll = maxScroll;
        }
        if (maxScroll > 0) {
            int trackX = chatX + chatW - 2;
            int barH = Math.max(12, visibleH * visibleH / Math.max(1, contentH));
            int barY = top + (int) ((visibleH - barH) * (scroll / (float) maxScroll));
            ctx.fill(trackX, barY, trackX + 2, barY + barH, 0xFF888888);
        }

        input.render(ctx, mouseX, mouseY, delta);
        // 内联编辑框渲染（高亮背景）
        if (editField != null) {
            ctx.fill(editBubbleX - 1, editBubbleY - 1, editBubbleX + editBubbleW + 1, editBubbleY + editBubbleH + 1, 0x80FFC107);
            ctx.drawBorder(editBubbleX, editBubbleY, editBubbleW, editBubbleH, 0xFFFFC107);
            editField.render(ctx, mouseX, mouseY, delta);
            ctx.drawTextWithShadow(this.textRenderer, Text.literal("↵ 确认 · 点外面取消"),
                    chatX + 4, editBubbleY + editBubbleH + 2, 0xFFFFC107);
        }
        super.render(ctx, mouseX, mouseY, delta);
    }

    private static String trim(String s, int n) {
        if (s == null) {
            return "";
        }
        return s.length() > n ? s.substring(0, n) + "…" : s;
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
