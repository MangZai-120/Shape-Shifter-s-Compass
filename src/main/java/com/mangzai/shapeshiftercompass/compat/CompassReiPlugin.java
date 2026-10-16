package com.mangzai.shapeshiftercompass.compat;

import com.mangzai.shapeshiftercompass.ui.CompassHud;
import com.mangzai.shapeshiftercompass.ui.CompassOverlayScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

import java.util.List;

/** Optional layout integration only; REI loads this entrypoint when installed. */
public final class CompassReiPlugin implements REIClientPlugin {
    @Override
    public void registerExclusionZones(ExclusionZones zones) {
        zones.register(Screen.class, screen -> {
            var client = MinecraftClient.getInstance();
            if (client.player == null) return List.of();
            // A container may still render behind the interactive Compass window.
            Screen visible = client.currentScreen instanceof CompassOverlayScreen ? client.currentScreen : screen;
            return CompassHud.exclusionAreas(visible, client.options.hudHidden).stream()
                    .map(area -> new Rectangle(area.getX(), area.getY(), area.getWidth(), area.getHeight()))
                    .toList();
        });
    }
}
