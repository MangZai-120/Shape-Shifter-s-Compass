package com.mangzai.shapeshiftercompass.ui;

import com.mangzai.shapeshiftercompass.config.CompassConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/**
 * 可视化布局编辑器：拖动悬浮球与 AI 小框到屏幕任意位置（全屏可放，不吸附）。
 * 保存写入 config；小框大小/透明/字体/显示开关等设置项也在本界面。
 */
public class CompassEditorScreen extends Screen {
    private final Screen parent;
    private int dragging = 0; // 0 无 / 1 悬浮球 / 2 小框
    private int dragOffX;
    private int dragOffY;
    private static final int BALL = 14;

    public CompassEditorScreen(Screen parent) {
        super(Text.translatable("ssc_compass.editor.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        CompassConfig cfg = CompassConfig.get();
        int sbw = 160;
        int sx = this.width / 2 - sbw / 2;
        int sy = 40;

        addDrawableChild(new CompassSlider(sx, sy, sbw, 20, 40, 150, cfg.hudFontPct,
                "ssc_compass.set.font_small", v -> cfg.hudFontPct = v));
        sy += 23;
        addDrawableChild(new CompassSlider(sx, sy, sbw, 20, 60, 200, cfg.chatFontPct,
                "ssc_compass.set.font_chat", v -> cfg.chatFontPct = v));
        sy += 23;
        addDrawableChild(new CompassSlider(sx, sy, sbw, 20, 0, 255, cfg.hudBgAlpha,
                "ssc_compass.set.alpha", v -> cfg.hudBgAlpha = v));
        sy += 23;
        addDrawableChild(new CompassSlider(sx, sy, sbw, 20, 80, 320, cfg.hudBoxWidth,
                "ssc_compass.set.width", v -> cfg.hudBoxWidth = v));
        sy += 23;
        addDrawableChild(new CompassSlider(sx, sy, sbw, 20, 60, 300, cfg.hudBoxHeight,
                "ssc_compass.set.height", v -> cfg.hudBoxHeight = v));

        addDrawableChild(ButtonWidget.builder(Text.translatable("ssc_compass.editor.reset"), b -> {
            CompassConfig c = CompassConfig.get();
            c.hudBallX = -1;
            c.hudBallY = -1;
        }).dimensions(this.width / 2 - 104, this.height - 28, 100, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.translatable("ssc_compass.editor.save"), b -> {
            CompassConfig.save();
            this.close();
        }).dimensions(this.width / 2 + 4, this.height - 28, 100, 20).build());
    }

    /** 无极滑条：value(0~1) 映射到 [min,max] 整数，拖动实时写入 config。 */
    private static class CompassSlider extends net.minecraft.client.gui.widget.SliderWidget {
        private final int min;
        private final int max;
        private final String labelKey;
        private final java.util.function.IntConsumer onChange;

        CompassSlider(int x, int y, int w, int h, int min, int max, int cur, String labelKey,
                java.util.function.IntConsumer onChange) {
            super(x, y, w, h, Text.empty(), clamp01((cur - min) / (double) (max - min)));
            this.min = min;
            this.max = max;
            this.labelKey = labelKey;
            this.onChange = onChange;
            updateMessage();
        }

        private static double clamp01(double v) {
            return Math.max(0.0, Math.min(1.0, v));
        }

        private int current() {
            return (int) Math.round(min + value * (max - min));
        }

        @Override
        protected void updateMessage() {
            setMessage(Text.translatable(labelKey, current()));
        }

        @Override
        protected void applyValue() {
            onChange.accept(current());
        }
    }

    private int ballX() {
        return CompassHud.ballX(this.width);
    }

    private int ballY() {
        return CompassHud.ballY(this.height);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        if (button == 0 && mx >= ballX() && mx <= ballX() + BALL
                && my >= ballY() && my <= ballY() + BALL) {
            dragging = 1;
            dragOffX = (int) mx - ballX();
            dragOffY = (int) my - ballY();
            return true;
        }
        return super.mouseClicked(mx, my, button);
    }

    @Override
    public boolean mouseDragged(double mx, double my, int button, double dx, double dy) {
        if (dragging == 1) {
            CompassHud.setBallPos(clamp((int) mx - dragOffX, 0, this.width - BALL),
                    clamp((int) my - dragOffY, 0, this.height - BALL));
            return true;
        }
        return super.mouseDragged(mx, my, button, dx, dy);
    }

    @Override
    public boolean mouseReleased(double mx, double my, int button) {
        dragging = 0;
        return super.mouseReleased(mx, my, button);
    }

    private static int clamp(int v, int lo, int hi) {
        return Math.max(lo, Math.min(hi, v));
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx);
        ctx.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 12, 0xFFFFFFFF);
        ctx.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("ssc_compass.editor.hint"),
                this.width / 2, 26, 0xFFAAAAAA);

        // 预览小框：跟随悬浮球位置
        CompassConfig cfg = CompassConfig.get();
        int[] p = CompassHud.computeBoxPos(this.width, this.height, cfg.hudBoxWidth, cfg.hudBoxHeight);
        CompassHud.drawBoxFrame(ctx, this.client, cfg, p[0], p[1], cfg.hudBoxWidth, cfg.hudBoxHeight, false, 0);

        // 悬浮球
        CompassHud.drawBall(ctx, ballX(), ballY());

        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }
}
