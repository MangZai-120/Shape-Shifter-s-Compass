package com.mangzai.shapeshiftercompass;

import com.mangzai.shapeshiftercompass.config.CompassConfig;
import com.mangzai.shapeshiftercompass.conversation.ConversationStore;
import com.mangzai.shapeshiftercompass.knowledge.KnowledgeBase;
import com.mangzai.shapeshiftercompass.tools.CraftingGridTool;
import com.mangzai.shapeshiftercompass.tools.EquipmentTool;
import com.mangzai.shapeshiftercompass.tools.FormStatusTool;
import com.mangzai.shapeshiftercompass.tools.InventoryTool;
import com.mangzai.shapeshiftercompass.tools.KnowledgeTool;
import com.mangzai.shapeshiftercompass.tools.RecipeQueryTool;
import com.mangzai.shapeshiftercompass.tools.ToolRegistry;
import com.mangzai.shapeshiftercompass.tools.TrinketsTool;
import com.mangzai.shapeshiftercompass.tools.WebSearchTool;
import com.mangzai.shapeshiftercompass.ui.CompassHud;
import com.mangzai.shapeshiftercompass.ui.CompassOverlayScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
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
        ToolRegistry.register(new com.mangzai.shapeshiftercompass.tools.FoodInfoTool());

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

        // 容器交互界面（背包/工作台/箱子等 HandledScreen）之上绘制悬浮球+小窗；
        // 若 CompassOverlayScreen 已打开（它自己会画小框），则跳过避免两层叠加；
        // ESC 暂停菜单、选项、标题等非容器界面一律不显示
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
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
