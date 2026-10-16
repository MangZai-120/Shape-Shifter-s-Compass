package com.mangzai.shapeshiftercompass.ui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

/** Compass interfaces keep the world visible while the player is using the assistant. */
public abstract class CompassScreen extends Screen {
    protected CompassScreen(Text title) {
        super(title);
    }

    /** Screen.render also calls this after custom panels; keep both background passes transparent. */
    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        // The Compass panels draw their own backgrounds. Never shade the entire world.
    }

    /** Includes a container rendered behind the Compass overlay. */
    public static boolean keepsWorldVisible(Screen rendering, Screen active) {
        return rendering instanceof CompassScreen || active instanceof CompassScreen;
    }
}
