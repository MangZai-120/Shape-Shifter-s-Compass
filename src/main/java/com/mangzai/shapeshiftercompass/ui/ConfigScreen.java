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

/** 窗口内配置界面：选择厂商（自动填 baseUrl/model）、填 baseUrl/apiKey/model，保存到本地。无需 ModMenu。 */
public class ConfigScreen extends Screen {
    private final Screen parent;
    private TextFieldWidget baseUrlField;
    private TextFieldWidget apiKeyField;
    private TextFieldWidget modelField;
    private String savedTip = "";

    private static final int W = 240;

    public ConfigScreen(Screen parent) {
        super(Text.translatable("ssc_compass.config.title"));
        this.parent = parent;
    }

    private int left() {
        return this.width / 2 - W / 2;
    }

    @Override
    protected void init() {
        CompassConfig cfg = CompassConfig.get();
        int x = left();
        int y = 40;

        addDrawableChild(CyclingButtonWidget.<AiProvider>builder(p -> Text.literal(p.displayName))
                .values(AiProvider.values())
                .initially(cfg.provider)
                .build(x, y, W, 20, Text.translatable("ssc_compass.config.provider"), (btn, val) -> {
                    cfg.provider = val;
                    if (val != AiProvider.CUSTOM) {
                        baseUrlField.setText(val.baseUrl);
                        if (val.defaultModels.length > 0) {
                            modelField.setText(val.defaultModels[0]);
                        }
                    }
                }));
        y += 34;

        baseUrlField = new TextFieldWidget(this.textRenderer, x, y, W, 18, Text.literal("baseUrl"));
        baseUrlField.setMaxLength(256);
        baseUrlField.setText(cfg.baseUrl);
        addDrawableChild(baseUrlField);
        y += 34;

        apiKeyField = new TextFieldWidget(this.textRenderer, x, y, W, 18, Text.literal("apiKey"));
        apiKeyField.setMaxLength(256);
        apiKeyField.setText(cfg.apiKey);
        // 遮罩显示：实际值照常保存，渲染时把每个字符替换成 *，避免 apiKey 明文暴露
        apiKeyField.setRenderTextProvider((string, firstCharIndex) ->
                OrderedText.styledForwardsVisitedString("*".repeat(string.length()), Style.EMPTY));
        addDrawableChild(apiKeyField);
        y += 34;

        modelField = new TextFieldWidget(this.textRenderer, x, y, W, 18, Text.literal("model"));
        modelField.setMaxLength(128);
        modelField.setText(cfg.model);
        addDrawableChild(modelField);
        y += 34;

        addDrawableChild(ButtonWidget.builder(Text.translatable("ssc_compass.config.edit_layout"),
                        b -> this.client.setScreen(new CompassEditorScreen(this)))
                .dimensions(x, y, W, 20).build());
        y += 26;

        // 作弊开关：仅 op 玩家可见可开
        if (com.mangzai.shapeshiftercompass.ai.CheatGuard.isOp()) {
            addDrawableChild(CyclingButtonWidget.onOffBuilder(cfg.cheatEnabled)
                    .build(x, y, W, 20, Text.translatable("ssc_compass.config.cheat"),
                            (btn, val) -> cfg.cheatEnabled = val));
            y += 26;
        }

        addDrawableChild(ButtonWidget.builder(Text.translatable("ssc_compass.config.save"), b -> {
            cfg.baseUrl = baseUrlField.getText().trim();
            cfg.apiKey = apiKeyField.getText().trim();
            cfg.model = modelField.getText().trim();
            CompassConfig.save();
            savedTip = Text.translatable("ssc_compass.config.saved").getString();
        }).dimensions(x, y, W / 2 - 2, 20).build());
        addDrawableChild(ButtonWidget.builder(Text.translatable("ssc_compass.button.close"), b -> this.close())
                .dimensions(x + W / 2 + 2, y, W / 2 - 2, 20).build());
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx);
        ctx.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFFFF);
        int x = left();
        ctx.drawTextWithShadow(this.textRenderer, Text.translatable("ssc_compass.config.baseurl"), x, 40 + 24, 0xFFAAAAAA);
        ctx.drawTextWithShadow(this.textRenderer, Text.translatable("ssc_compass.config.apikey"), x, 40 + 58, 0xFFAAAAAA);
        ctx.drawTextWithShadow(this.textRenderer, Text.translatable("ssc_compass.config.model"), x, 40 + 92, 0xFFAAAAAA);
        if (!savedTip.isEmpty()) {
            ctx.drawCenteredTextWithShadow(this.textRenderer, Text.literal(savedTip),
                    this.width / 2, this.height - 28, 0xFF7FFF7F);
        }
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }
}
