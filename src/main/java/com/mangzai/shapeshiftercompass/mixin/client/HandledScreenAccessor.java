package com.mangzai.shapeshiftercompass.mixin.client;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/** Exposes the hovered slot for explicit target capture, plus container layout bounds. */
@Mixin(HandledScreen.class)
public interface HandledScreenAccessor {
    @Accessor("focusedSlot")
    net.minecraft.screen.slot.Slot compassFocusedSlot();
    @Accessor("x")
    int getX();

    @Accessor("y")
    int getY();

    @Accessor("backgroundWidth")
    int getBackgroundWidth();
}
