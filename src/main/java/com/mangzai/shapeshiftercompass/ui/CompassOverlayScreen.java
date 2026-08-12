package com.mangzai.shapeshiftercompass.ui;

import com.mangzai.shapeshiftercompass.ai.AiClient;
import com.mangzai.shapeshiftercompass.ai.ChatMessage;
import com.mangzai.shapeshiftercompass.config.CompassConfig;
import com.mangzai.shapeshiftercompass.conversation.Conversation;
import com.mangzai.shapeshiftercompass.conversation.ConversationStore;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;

/**
 * 交互模式（快捷键 K 唤出）：半透明、鼠标可见，不整屏遮挡。
 * 点悬浮球 → 展开大窗（完整 ChatScreen）；直接打字 → 小窗问答（回答显示在 HUD 小框）。
 */
public class CompassOverlayScreen extends Screen {
    private final Screen parent;
    private TextFieldWidget input;
    private boolean waiting = false;
    private int bx;
    private int by;
    private int bw;
    private int bh;
    private int scroll = 0;
    private int thinkTick = 0;
    private int hoverGlyph = 0; // 0 无 / 1 放大 / 2 关闭
    private ButtonWidget sendBtn;
    /** 输入框 ↑/↓ 历史导航 */
    private final InputHistory inputHistory = new InputHistory();

    public CompassOverlayScreen(Screen parent) {
        super(Text.translatable("ssc_compass.screen.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        CompassHud.setBoxOpen(true);
        CompassConfig cfg = CompassConfig.get();
        bw = cfg.hudBoxWidth;
        bh = cfg.hudBoxHeight;
        int[] p = CompassHud.computeBoxPos(this.width, this.height, bw, bh);
        bx = p[0];
        by = p[1];

        input = new TextFieldWidget(this.textRenderer, bx + 3, by + bh - 20, bw - 40, 16,
                Text.translatable("ssc_compass.input.placeholder"));
        input.setMaxLength(4000);
        input.setPlaceholder(Text.translatable("ssc_compass.input.placeholder"));
        addSelectableChild(input);
        setInitialFocus(input);
        sendBtn = ButtonWidget.builder(Text.literal("→"), b -> {
            if (com.mangzai.shapeshiftercompass.ai.CompassState.isBusy()) {
                com.mangzai.shapeshiftercompass.ai.AiClient.cancel();
                waiting = false;
            } else {
                onSend();
            }
        }).dimensions(bx + bw - 34, by + bh - 21, 32, 18).build();
        addDrawableChild(sendBtn);
    }

    private void onSend() {
        if (waiting) {
            return;
        }
        String t = input.getText().trim();
        if (t.isEmpty()) {
            return;
        }
        Conversation c = ConversationStore.current();
        c.messages.add(new ChatMessage("user", t));
        c.autoTitle();
        input.setText("");
        inputHistory.reset();
        waiting = true;
        ConversationStore.save();
        AiClient.sendChat(new ArrayList<>(c.messages),
                reply -> {
                    c.messages.add(new ChatMessage("assistant", reply));
                    CompassHud.setLastAnswer(reply);
                    waiting = false;
                    ConversationStore.save();
                },
                err -> {
                    if ("cancelled".equals(err)) {
                        waiting = false;
                        return;
                    }
                    String m = "no_key".equals(err)
                            ? Text.translatable("ssc_compass.msg.no_key").getString()
                            : Text.translatable("ssc_compass.msg.error", err).getString();
                    c.messages.add(new ChatMessage("assistant", m));
                    CompassHud.setLastAnswer(m);
                    waiting = false;
                    ConversationStore.save();
                });
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        if (button == 0) {
            int titleH = 13;
            // 关闭 ✕（标题栏右上角）
            if (mx >= bx + bw - 15 && mx <= bx + bw - 1 && my >= by + 1 && my <= by + titleH) {
                CompassHud.setBoxOpen(false);
                this.close();
                return true;
            }
            // 放大 ▢ → 大窗
            if (mx >= bx + bw - 29 && mx <= bx + bw - 15 && my >= by + 1 && my <= by + titleH) {
                this.client.setScreen(new ChatScreen(null, false));
                return true;
            }
            // 点小窗外空白 → 回到游戏控制，但保留小窗（HUD 继续显示）
            if (!(mx >= bx && mx <= bx + bw && my >= by && my <= by + bh)) {
                this.close();
                return true;
            }
        }
        return super.mouseClicked(mx, my, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
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
        for (ChatMessage m : ConversationStore.current().messages) {
            if ("user".equals(m.role) && m.content != null && !m.content.isEmpty()) {
                list.add(m.content);
            }
        }
        java.util.Collections.reverse(list);
        return list;
    }

    @Override
    public void tick() {
        if (waiting || com.mangzai.shapeshiftercompass.ai.CompassState.isBusy()) {
            thinkTick++;
        } else {
            thinkTick = 0;
        }
        if (sendBtn != null) {
            sendBtn.setMessage(Text.literal(
                    com.mangzai.shapeshiftercompass.ai.CompassState.isBusy() ? "■" : "→"));
        }
    }

    private String thinkingText() {
        int phase = thinkTick / 5;
        if (phase > 3) {
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
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        // 若从容器界面唤出，先把原界面画作背景，实现叠加而非替换
        if (parent != null) {
            try {
                parent.render(ctx, -1, -1, delta);
            } catch (Exception ignored) {
            }
            ctx.fill(0, 0, this.width, this.height, 0x40000000);
        }
        CompassConfig cfg = CompassConfig.get();
        // 计算 hover 状态：放大/关闭图标区域
        int titleH = 13;
        hoverGlyph = 0;
        if (mouseY >= by + 1 && mouseY <= by + titleH) {
            if (mouseX >= bx + bw - 29 && mouseX <= bx + bw - 15) {
                hoverGlyph = 1;
            } else if (mouseX >= bx + bw - 15 && mouseX <= bx + bw - 1) {
                hoverGlyph = 2;
            }
        }
        // 发送后等待 AI 回复期间，小框内容区清空（直到新回答返回）
        CompassHud.drawBoxFrame(ctx, this.client, cfg, bx, by, bw, bh, true, scroll, waiting, hoverGlyph);
        if (waiting || com.mangzai.shapeshiftercompass.ai.CompassState.isBusy()) {
            ctx.drawText(this.textRenderer, Text.literal(thinkingText()),
                    bx + 3, by + bh - 33, 0xFFAAAAAA, false);
        }
        input.render(ctx, mouseX, mouseY, delta);
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        CompassConfig cfg = CompassConfig.get();
        float fscale = Math.max(0.4f, cfg.hudFontPct / 100.0f);
        int lineCount = CompassHud.answerLineCount(this.client, bw, fscale);
        int maxLines = Math.max(1, (int) ((bh - 13 - 22 - 4) / (9 * fscale)));
        int maxStart = Math.max(0, lineCount - maxLines);
        scroll = Math.max(0, Math.min(maxStart, scroll - (int) amount));
        return true;
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
