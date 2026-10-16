package com.mangzai.shapeshiftercompass.compat;

import com.mangzai.shapeshiftercompass.ui.CompassHud;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.handlers.IGlobalGuiHandler;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.Rect2i;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.List;

/** Optional layout integration only; JEI loads this entrypoint when installed. */
@JeiPlugin
public final class CompassJeiPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return Identifier.of("ssc_compass", "hud_exclusions");
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addGlobalGuiHandler(new IGlobalGuiHandler() {
            @Override
            public Collection<Rect2i> getGuiExtraAreas() {
                var client = MinecraftClient.getInstance();
                if (client.player == null || client.currentScreen == null) return List.of();
                return CompassHud.exclusionAreas(client.currentScreen, client.options.hudHidden);
            }
        });
    }
}
