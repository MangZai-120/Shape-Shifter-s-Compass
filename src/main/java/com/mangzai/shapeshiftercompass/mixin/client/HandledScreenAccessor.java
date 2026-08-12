package com.mangzai.shapeshiftercompass.mixin.client;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/** 暴露 HandledScreen 的 x/y/backgroundWidth，供在物品栏合成栏右上角定位「AI 助手」按钮。 */
@Mixin(HandledScreen.class)
public interface HandledScreenAccessor {
    @Accessor("x")
    int getX();

    @Accessor("y")
    int getY();

    @Accessor("backgroundWidth")
    int getBackgroundWidth();
}
