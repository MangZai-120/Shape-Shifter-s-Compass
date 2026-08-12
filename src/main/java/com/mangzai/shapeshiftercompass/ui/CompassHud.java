package com.mangzai.shapeshiftercompass.ui;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

import java.util.List;

/**
 * HUD overlay 小框：左上角常驻显示 AI 最新回答，不拦截玩家操作（HudRenderCallback）。
 * 第一步：固定左上角 + 缩放小字体 + 半透明背景。
 * 后续：位置/大小/透明度/字体/显示开关设置可调 + 可视化拖拽（见 BarPositionEditorScreen 思路）。
 */
public final class CompassHud {
    public static final int BALL = 14;
    public static final int BOX_H = 112;
    private static final boolean SSC_LOADED =
            net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("shape-shifter-curse")
            || net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("ssc_addon");
    private static String lastAnswer = "";
    private static boolean boxOpen = false;

    private CompassHud() {}

    public static void register() {
        HudRenderCallback.EVENT.register((ctx, tickDelta) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null || mc.options.hudHidden) {
                return;
            }
            renderOverlay(ctx, ctx.getScaledWindowWidth(), ctx.getScaledWindowHeight(), true);
        });
    }

    /** 供 Screen（背包/合成台等）afterRender 调用：在界面之上绘制悬浮球+小框。 */
    public static void renderOnScreen(DrawContext ctx, int sw, int sh) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.options.hudHidden) {
            return;
        }
        renderOverlay(ctx, sw, sh, true);
    }

    public static void setLastAnswer(String s) {
        lastAnswer = s == null ? "" : s;
    }

    public static void setBoxOpen(boolean v) {
        boxOpen = v;
    }

    public static boolean isBoxOpen() {
        return boxOpen;
    }

    public static void setVisible(boolean v) {
        com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudVisible = v;
    }

    public static boolean isVisible() {
        return com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudVisible;
    }

    public static int ballX(int screenW) {
        int x = com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBallX;
        return x < 0 ? screenW - 20 : Math.max(0, Math.min(screenW - BALL, x));
    }

    public static int ballY(int screenH) {
        int y = com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBallY;
        return y < 0 ? screenH / 2 : Math.max(0, Math.min(screenH - BALL, y));
    }

    public static void setBallPos(int x, int y) {
        com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBallX = x;
        com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBallY = y;
    }

    public static void setBoxPos(int x, int y) {
        com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBoxX = x;
        com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBoxY = y;
    }

    public static int boxX() {
        return com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBoxX;
    }

    public static int boxY() {
        return com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBoxY;
    }

    public static int boxW() {
        return com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBoxWidth;
    }

    public static int boxH() {
        return com.mangzai.shapeshiftercompass.config.CompassConfig.get().hudBoxHeight;
    }

    /** 公共绘制：withBall 控制是否画悬浮球；小窗仅在开启且 boxOpen 时显示（overlay 打开时一律不画，避免两层）。 */
    public static void renderOverlay(DrawContext ctx, int sw, int sh, boolean withBall) {
        if (withBall) {
            drawBall(ctx, ballX(sw), ballY(sh));
        }
        // CompassOverlayScreen 自己会画小框，此处跳过避免两层叠加
        if (MinecraftClient.getInstance().currentScreen instanceof CompassOverlayScreen) {
            return;
        }
        com.mangzai.shapeshiftercompass.config.CompassConfig cfg =
                com.mangzai.shapeshiftercompass.config.CompassConfig.get();
        if (cfg.hudVisible && boxOpen) {
            int[] p = computeBoxPos(sw, sh, cfg.hudBoxWidth, cfg.hudBoxHeight);
            boolean busy = com.mangzai.shapeshiftercompass.ai.CompassState.isBusy();
            // AI 生成中（用户已退出小框）：内容区清空，只显示「思考中 · · ·」
            drawBoxFrame(ctx, MinecraftClient.getInstance(), cfg, p[0], p[1], cfg.hudBoxWidth, cfg.hudBoxHeight, false, 0, busy, 0);
            if (busy) {
                int tick = thinkingTick();
                ctx.drawText(MinecraftClient.getInstance().textRenderer,
                        Text.literal(thinkingText(tick)),
                        p[0] + 3, p[1] + cfg.hudBoxHeight - 13, 0xFFAAAAAA, false);
            }
        }
    }

    /** HUD 思考动画 tick：基于系统时间推导（HUDRenderCallback 无 tick 注入）。 */
    private static int thinkingTick() {
        return (int) ((System.currentTimeMillis() / 100L) % 26);
    }

    /** HUD 思考文字（带动态点 ·，点间空格）。 */
    private static String thinkingText(int tick) {
        int phase = tick / 5;
        if (phase > 3) {
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

    /** 还原方块样式悬浮球（可全屏放置）。 */
    public static void drawBall(DrawContext ctx, int x, int y) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ctx.fill(x, y, x + BALL, y + BALL, 0xC03A7ABA);
        ctx.drawBorder(x, y, BALL, BALL, 0xFFFFFFFF);
        ctx.drawText(mc.textRenderer, "✦", x + 3, y + 3, 0xFFFFFFFF, false);
    }

    /** 小窗位置：贴着悬浮球朝屏幕中心展开（球落在小框靠边的角）。 */
    public static int[] computeBoxPos(int sw, int sh, int boxW, int boxH) {
        int bx = ballX(sw);
        int by = ballY(sh);
        boolean left = (bx + BALL / 2) < sw / 2;
        boolean top = (by + BALL / 2) < sh / 2;
        int x = left ? bx : (bx + BALL - boxW);
        int y = top ? (by + BALL) : (by - boxH);
        x = Math.max(2, Math.min(Math.max(2, sw - boxW - 2), x));
        y = Math.max(2, Math.min(Math.max(2, sh - boxH - 2), y));
        return new int[]{x, y};
    }

    /** 绘制小窗主体（标题栏含放大/关闭图标 + 回答内容）；interactive=true 底部留输入框空间。 */
    public static void drawBoxFrame(DrawContext ctx, MinecraftClient mc,
            com.mangzai.shapeshiftercompass.config.CompassConfig cfg,
            int px, int py, int boxW, int boxH, boolean interactive, int scrollLines) {
        drawBoxFrame(ctx, mc, cfg, px, py, boxW, boxH, interactive, scrollLines, false, 0);
    }

    /** 绘制小窗主体；hideContent=true 时内容区留空；hoverGlyph=1/2 时对应图标画深色 hover 背景。 */
    public static void drawBoxFrame(DrawContext ctx, MinecraftClient mc,
            com.mangzai.shapeshiftercompass.config.CompassConfig cfg,
            int px, int py, int boxW, int boxH, boolean interactive, int scrollLines,
            boolean hideContent, int hoverGlyph) {
        TextRenderer tr = mc.textRenderer;
        float fscale = Math.max(0.4f, cfg.hudFontPct / 100.0f);
        int alpha = Math.max(0, Math.min(255, cfg.hudBgAlpha)) << 24;
        int titleH = 13;
        int inputH = interactive ? 22 : 0;

        ctx.fill(px, py, px + boxW, py + boxH, alpha);
        ctx.drawBorder(px, py, boxW, boxH, 0xFF4A8ACA);
        ctx.fill(px + 1, py + 1, px + boxW - 1, py + titleH, 0x40FFFFFF);
        // hover 高亮：放大/关闭图标区域画深色背景（类 Windows 选中提示）
        if (hoverGlyph == 1) {
            ctx.fill(px + boxW - 29, py + 1, px + boxW - 15, py + titleH, 0x80303030);
        } else if (hoverGlyph == 2) {
            ctx.fill(px + boxW - 15, py + 1, px + boxW - 1, py + titleH, 0x80602020);
        }
        ctx.drawText(tr, Text.literal("♪ Compass"), px + 3, py + 3, 0xFFFFFFFF, false);
        drawGlyph(ctx, tr, "▢", px + boxW - 21, py + titleH / 2, 1.5f, 0xFFCCE4FF);
        drawGlyph(ctx, tr, "✕", px + boxW - 8, py + titleH / 2, 1.3f, 0xFFFF9090);

        String text;
        if (hideContent) {
            text = "";
        } else if (!SSC_LOADED) {
            text = Text.translatable("ssc_compass.overlay.no_ssc").getString();
        } else {
            String answer = currentAnswer();
            text = answer.isEmpty() ? Text.translatable("ssc_compass.overlay.empty").getString() : answer;
        }
        int wrapW = (int) ((boxW - 6) / fscale);
        List<OrderedText> lines = tr.wrapLines(Text.literal(text), wrapW);
        int areaH = boxH - titleH - inputH - 4;
        int maxLines = Math.max(1, (int) (areaH / (9 * fscale)));
        int maxStart = Math.max(0, lines.size() - maxLines);
        int start = Math.max(0, Math.min(scrollLines, maxStart));
        int shown = Math.min(maxLines, lines.size() - start);

        ctx.getMatrices().push();
        ctx.getMatrices().scale(fscale, fscale, 1.0f);
        int lx = (int) ((px + 3) / fscale);
        int ly = (int) ((py + titleH + 2) / fscale);
        for (int i = 0; i < shown; i++) {
            ctx.drawText(tr, lines.get(start + i), lx, ly, 0xFFEFEFEF, false);
            ly += 9;
        }
        ctx.getMatrices().pop();

        // 内容超出时右侧画滚动条
        if (lines.size() > maxLines && maxStart > 0) {
            int trackTop = py + titleH + 1;
            int trackH = boxH - titleH - inputH - 2;
            int barH = Math.max(6, trackH * maxLines / lines.size());
            int barY = trackTop + (trackH - barH) * start / maxStart;
            ctx.fill(px + boxW - 3, barY, px + boxW - 1, barY + barH, 0xFF9AA0A6);
        }
    }

    /** 当前对话的最新一条 AI 回答（无则回退到 lastAnswer）。 */
    public static String currentAnswer() {
        try {
            com.mangzai.shapeshiftercompass.conversation.Conversation c =
                    com.mangzai.shapeshiftercompass.conversation.ConversationStore.current();
            if (c != null) {
                for (int i = c.messages.size() - 1; i >= 0; i--) {
                    com.mangzai.shapeshiftercompass.ai.ChatMessage m = c.messages.get(i);
                    if ("assistant".equals(m.role) && m.content != null && !m.content.isEmpty()) {
                        return m.content;
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return lastAnswer;
    }

    /** 当前对话最新回答的换行行数（供交互态计算滚动上限）。 */
    public static int answerLineCount(MinecraftClient mc, int boxW, float fscale) {
        String answer = currentAnswer();
        if (answer.isEmpty()) {
            return 0;
        }
        int wrapW = (int) ((boxW - 6) / fscale);
        return mc.textRenderer.wrapLines(Text.literal(answer), wrapW).size();
    }

    /** 在 (cx,cy) 为中心按 scale 放大绘制单个符号（用于放大/关闭图标）。 */
    private static void drawGlyph(DrawContext ctx, TextRenderer tr, String s, int cx, int cy, float scale, int color) {
        float w = tr.getWidth(s) * scale;
        float h = 7.5f * scale;
        ctx.getMatrices().push();
        ctx.getMatrices().translate(cx - w / 2f, cy - h / 2f, 0);
        ctx.getMatrices().scale(scale, scale, 1.0f);
        ctx.drawText(tr, s, 0, 0, color, false);
        ctx.getMatrices().pop();
    }
}
