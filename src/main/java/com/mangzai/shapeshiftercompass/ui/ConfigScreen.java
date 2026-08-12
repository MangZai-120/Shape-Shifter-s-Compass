package com.mangzai.shapeshiftercompass.ui;

import com.mangzai.shapeshiftercompass.config.AiProvider;
import com.mangzai.shapeshiftercompass.config.CompassConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

/** 窗口内配置界面：选择厂商（自动填 baseUrl/model）、填 baseUrl/apiKey/model，保存到本地。无需 ModMenu�?*/
public class ConfigScreen extends Screen {
    private final Screen parent;
    private TextFieldWidget baseUrlField;
    private TextFieldWidget apiKeyField;
    private TextFieldWidget modelField;
    private String savedTip = "";

    /** 基准宽度�?00% GUI scale 下的控件宽度）；实际宽度�?UI 缩放自适应，避免高 GUI scale 下底部按钮溢�?*/
    private static final int BASE_W = 192;
    private float uiScale = 1.0f;

    public ConfigScreen(Screen parent) {
        super(Text.translatable("ssc_compass.config.title"));
        this.parent = parent;
    }

    /** 实际控件宽度（按 uiScale 缩放，至�?120 保证可用）�?*/
    private int W() {
        return Math.max(120, Math.round(BASE_W * uiScale));
    }

    private int left() {
        return this.width / 2 - W() / 2;
    }

    @Override
    protected void init() {
        CompassConfig cfg = CompassConfig.get();
        // 根据屏幕高度自适应缩放：估算内容总高，若超出可用高度则按比例缩小（整体至少缩 15%）�?
        // 控件数：厂商/baseUrl/apiKey/model/思考模�?思考深�?布局编辑/(作弊)/保存关闭
        boolean isOp = com.mangzai.shapeshiftercompass.ai.CheatGuard.isOp();
        int rows = 8 + (isOp ? 1 : 0); // 保存关闭�?1 �?
        int estHeight = 40 + rows * 30 + 20; // 起始40 + 每行�?0 + 底部余量
        int availHeight = this.height - 20; // 留顶部标�?
        // 默认整体�?15%（用户要求），若仍超出则进一步缩
        float base = 0.85f;
        if (estHeight * base > availHeight) {
            uiScale = Math.max(0.55f, availHeight / (float) estHeight);
        } else {
            uiScale = base;
        }
        int W = W();
        int btnH = Math.max(14, Math.round(20 * uiScale));
        int fieldH = Math.max(12, Math.round(18 * uiScale));
        int stepBig = Math.round(34 * uiScale);
        int stepSmall = Math.round(26 * uiScale);
        int x = left();
        int y = Math.round(40 * uiScale);

        addDrawableChild(CyclingButtonWidget.<AiProvider>builder(p -> Text.literal(p.displayName))
                .values(AiProvider.values())
                .initially(cfg.provider)
                .build(x, y, W, btnH, Text.translatable("ssc_compass.config.provider"), (btn, val) -> {
                    cfg.provider = val;
                    if (val != AiProvider.CUSTOM) {
                        baseUrlField.setText(val.baseUrl);
                        if (val.defaultModels.length > 0) {
                            modelField.setText(val.defaultModels[0]);
                        }
                    }
                }));
        y += stepBig;

        baseUrlField = new TextFieldWidget(this.textRenderer, x, y, W, fieldH, Text.literal("baseUrl"));
        baseUrlField.setMaxLength(256);
        baseUrlField.setText(cfg.baseUrl);
        addDrawableChild(baseUrlField);
        y += stepBig;

        apiKeyField = new TextFieldWidget(this.textRenderer, x, y, W, fieldH, Text.literal("apiKey"));
        apiKeyField.setMaxLength(256);
        apiKeyField.setText(cfg.apiKey);
        // 遮罩显示：实际值照常保存，渲染时把每个字符替换�?*，避�?apiKey 明文暴露
        apiKeyField.setRenderTextProvider((string, firstCharIndex) ->
                OrderedText.styledForwardsVisitedString("*".repeat(string.length()), Style.EMPTY));
        addDrawableChild(apiKeyField);
        y += stepBig;

        modelField = new TextFieldWidget(this.textRenderer, x, y, W, fieldH, Text.literal("model"));
        modelField.setMaxLength(128);
        modelField.setText(cfg.model);
        addDrawableChild(modelField);
        y += stepBig;

        // 思考模式开关（reasoning_effort）：开启后按下方档位传给支持思考的模型
        addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.thinkingEnabled)
                .build(x, y, W, btnH, Text.translatable("ssc_compass.config.thinking"),
                        (btn, val) -> cfg.thinkingEnabled = val));
        y += stepSmall;
        // 思考深度档位：minimal / low / medium / high / xhigh / max（默�?medium�?
        addDrawableChild(CyclingButtonWidget.<String>builder(effort -> Text.literal(effort))
                .values("minimal", "low", "medium", "high", "xhigh", "max")
                .initially(cfg.reasoningEffort)
                .build(x, y, W, btnH, Text.translatable("ssc_compass.config.thinking_level"),
                        (btn, val) -> cfg.reasoningEffort = val));
        y += stepSmall;

        addDrawableChild(ButtonWidget.builder(Text.translatable("ssc_compass.config.edit_layout"),
                        b -> this.client.setScreen(new CompassEditorScreen(this)))
                .dimensions(x, y, W, btnH).build());
        y += stepSmall;

        // 作弊开关：�?op 玩家可见可开
        if (isOp) {
            addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.cheatEnabled)
                    .build(x, y, W, btnH, Text.translatable("ssc_compass.config.cheat"),
                            (btn, val) -> cfg.cheatEnabled = val));
            y += stepSmall;
        }

        addDrawableChild(ButtonWidget.builder(Text.translatable("ssc_compass.config.save"), b -> {
            cfg.baseUrl = baseUrlField.getText().trim();
            cfg.apiKey = apiKeyField.getText().trim();
            cfg.model = modelField.getText().trim();
            CompassConfig.save();
            savedTip = Text.translatable("ssc_compass.config.saved").getString();
        }).dimensions(x, y, W / 2 - 2, btnH).build());
        addDrawableChild(ButtonWidget.builder(Text.translatable("ssc_compass.button.close"), b -> this.close())
                .dimensions(x + W / 2 + 2, y, W / 2 - 2, btnH).build());
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx);
        int titleY = Math.max(8, Math.round(20 * uiScale));
        ctx.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, titleY, 0xFFFFFFFF);
        int x = left();
        // 输入框上方的小标签，跟随 uiScale 缩放：每个标签画在对应输入框上方�?10px 处�?
        // 输入�?y 起始 = 40*scale，第一行是厂商按钮（步�?stepBig），baseUrl/apiKey/model 依次占第 2/3/4 行�?
        int fieldRow0 = Math.round(40 * uiScale) + Math.round(34 * uiScale); // baseUrl 框的 y
        int fieldStep = Math.round(34 * uiScale);
        int labelOffset = Math.round(12 * uiScale); // 标签在框上方的偏�?
        ctx.drawTextWithShadow(this.textRenderer, Text.translatable("ssc_compass.config.baseurl"),
                x, fieldRow0 - labelOffset, 0xFFAAAAAA);
        ctx.drawTextWithShadow(this.textRenderer, Text.translatable("ssc_compass.config.apikey"),
                x, fieldRow0 + fieldStep - labelOffset, 0xFFAAAAAA);
        ctx.drawTextWithShadow(this.textRenderer, Text.translatable("ssc_compass.config.model"),
                x, fieldRow0 + fieldStep * 2 - labelOffset, 0xFFAAAAAA);
        if (!savedTip.isEmpty()) {
            ctx.drawCenteredTextWithShadow(this.textRenderer, Text.literal(savedTip),
                    this.width / 2, this.height - Math.max(16, Math.round(28 * uiScale)), 0xFF7FFF7F);
        }
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }
}
