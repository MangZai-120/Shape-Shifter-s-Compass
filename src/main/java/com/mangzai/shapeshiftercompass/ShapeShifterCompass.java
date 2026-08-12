package com.mangzai.shapeshiftercompass;

import com.mangzai.shapeshiftercompass.config.CompassConfig;
import com.mangzai.shapeshiftercompass.conversation.ConversationStore;
import com.mangzai.shapeshiftercompass.knowledge.KnowledgeBase;
import com.mangzai.shapeshiftercompass.mixin.client.HandledScreenAccessor;
import com.mangzai.shapeshiftercompass.tools.CraftingGridTool;
import com.mangzai.shapeshiftercompass.tools.EquipmentTool;
import com.mangzai.shapeshiftercompass.tools.FormStatusTool;
import com.mangzai.shapeshiftercompass.tools.InventoryTool;
import com.mangzai.shapeshiftercompass.tools.KnowledgeTool;
import com.mangzai.shapeshiftercompass.tools.RecipeQueryTool;
import com.mangzai.shapeshiftercompass.tools.ToolRegistry;
import com.mangzai.shapeshiftercompass.tools.TrinketsTool;
import com.mangzai.shapeshiftercompass.tools.WebSearchTool;
import com.mangzai.shapeshiftercompass.ui.ChatScreen;
import com.mangzai.shapeshiftercompass.ui.CompassHud;
import com.mangzai.shapeshiftercompass.ui.CompassOverlayScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShapeShifterCompass implements ClientModInitializer {
    public static final String MOD_ID = "ssc_compass";
    public static final Logger LOGGER = LoggerFactory.getLogger("ssc_compass");

    private static KeyBinding openKey;
    private static boolean openKeyDown = false;

    @Override
    public void onInitializeClient() {
        CompassConfig.load();
        KnowledgeBase.load();
        ConversationStore.load();

        ToolRegistry.register(new InventoryTool());
        ToolRegistry.register(new EquipmentTool());
        ToolRegistry.register(new CraftingGridTool());
        ToolRegistry.register(new RecipeQueryTool());
        ToolRegistry.register(new TrinketsTool());
        ToolRegistry.register(new FormStatusTool());
        ToolRegistry.register(new KnowledgeTool());
        ToolRegistry.register(new com.mangzai.shapeshiftercompass.tools.PlayerStatusTool());
        ToolRegistry.register(new WebSearchTool());
        ToolRegistry.register(new com.mangzai.shapeshiftercompass.tools.SeedTool());
        ToolRegistry.register(new com.mangzai.shapeshiftercompass.tools.LocateTool());
        ToolRegistry.register(new com.mangzai.shapeshiftercompass.tools.RunCommandTool());
        ToolRegistry.register(new com.mangzai.shapeshiftercompass.tools.SetPlayerDataTool());
        ToolRegistry.register(new com.mangzai.shapeshiftercompass.tools.ListModsTool());

        openKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.ssc_compass.open",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "category.ssc_compass"
        ));

        CompassHud.register();

        // /locate 反馈捕获（供 LocateTool 解析结构坐标）
        net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents.GAME.register(
                (message, overlay) -> com.mangzai.shapeshiftercompass.tools.LocateTool.onGameMessage(message));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openKey.wasPressed()) {
                CompassHud.setBoxOpen(true);
                client.setScreen(new CompassOverlayScreen(null));
            }
            // 容器界面打开时 KeyBinding 不触发（且 keyPressed 可能被其它 mod 截断），改用物理键边缘检测兑底
            if (client.currentScreen instanceof HandledScreen && client.getWindow() != null) {
                int code = InputUtil.fromTranslationKey(openKey.getBoundKeyTranslationKey()).getCode();
                boolean down = code != InputUtil.UNKNOWN_KEY.getCode()
                        && InputUtil.isKeyPressed(client.getWindow().getHandle(), code);
                if (down && !openKeyDown
                        && !(client.currentScreen.getFocused() instanceof TextFieldWidget)) {
                    CompassHud.setBoxOpen(true);
                    client.setScreen(new CompassOverlayScreen(client.currentScreen));
                }
                openKeyDown = down;
            } else {
                openKeyDown = false;
            }
        });

        // 物品栏合成栏右上角加「AI」按钮（缩小版，约原来的 1/3）
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof InventoryScreen) {
                HandledScreenAccessor acc = (HandledScreenAccessor) screen;
                int btnX = acc.getX() + acc.getBackgroundWidth() - 12;
                int btnY = acc.getY() + 4;
                // 小窗为顶层绘制会盖住按钮：若小窗正显示且与按钮默认位置重叠，把 AI 按钮避让到小窗外紧邻位置
                if (CompassHud.isBoxOpen() && CompassConfig.get().hudVisible) {
                    int bw = CompassConfig.get().hudBoxWidth;
                    int bh = CompassConfig.get().hudBoxHeight;
                    int[] p = CompassHud.computeBoxPos(scaledWidth, scaledHeight, bw, bh);
                    boolean overlap = btnX + 8 > p[0] && btnX < p[0] + bw
                            && btnY + 8 > p[1] && btnY < p[1] + bh;
                    if (overlap) {
                        int[][] cands = {
                                {p[0] + bw + 2, p[1] + 2},
                                {p[0] - 10, p[1] + 2},
                                {p[0] + bw - 10, p[1] + bh + 2},
                                {p[0] + bw - 10, p[1] - 10}
                        };
                        for (int[] cand : cands) {
                            if (cand[0] >= 0 && cand[0] + 8 <= scaledWidth
                                    && cand[1] >= 0 && cand[1] + 8 <= scaledHeight) {
                                btnX = cand[0];
                                btnY = cand[1];
                                break;
                            }
                        }
                    }
                }
                ButtonWidget btn = ButtonWidget.builder(Text.literal("✦"), b -> client.setScreen(new ChatScreen(screen, false)))
                        .dimensions(btnX, btnY, 8, 8)
                        .tooltip(Tooltip.of(Text.translatable("ssc_compass.button.open")))
                        .build();
                Screens.getButtons(screen).add(btn);
            }
            // 仅在容器交互界面（背包/工作台/箱子等 HandledScreen）之上绘制悬浮球+小窗；
            // 若 CompassOverlayScreen 已打开（它自己会画小框），则跳过避免两层叠加；
            // ESC 暂停菜单、选项、标题等非容器界面一律不显示
            if (screen instanceof HandledScreen && !(client.currentScreen instanceof CompassOverlayScreen)) {
                ScreenEvents.afterRender(screen).register((scr, ctx, mx, my, td) -> {
                    if (client.currentScreen instanceof CompassOverlayScreen) {
                        return;
                    }
                    CompassHud.renderOnScreen(ctx, scr.width, scr.height);
                });
            }
        });

        LOGGER.info("Shape Shifter's Compass initialized (client-only).");
    }
}
