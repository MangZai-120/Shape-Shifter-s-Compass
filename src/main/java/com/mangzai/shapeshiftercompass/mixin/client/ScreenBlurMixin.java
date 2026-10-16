package com.mangzai.shapeshiftercompass.mixin.client;

import com.mangzai.shapeshiftercompass.ui.CompassScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Also covers a parent container's background pass while Compass is the active screen. */
@Mixin(Screen.class)
public abstract class ScreenBlurMixin {
    @Inject(method = "applyBlur", at = @At("HEAD"), cancellable = true)
    private void sscCompass$skipBackgroundBlur(float delta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (CompassScreen.keepsWorldVisible((Screen) (Object) this, client == null ? null : client.currentScreen)) {
            ci.cancel();
        }
    }

    /** HandledScreen draws this gradient even when its normal background pass is overridden. */
    @Inject(method = "renderInGameBackground", at = @At("HEAD"), cancellable = true)
    private void sscCompass$skipBackgroundDimming(DrawContext context, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (CompassScreen.keepsWorldVisible((Screen) (Object) this, client == null ? null : client.currentScreen)) {
            ci.cancel();
        }
    }
}
