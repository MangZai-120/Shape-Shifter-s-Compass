package com.mangzai.shapeshiftercompass.mixin.client;

import com.mangzai.shapeshiftercompass.ai.CommandFeedbackSuppressor;
import com.mangzai.shapeshiftercompass.tools.LocateTool;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 拦截聊天消息添加：当处于命令反馈抑制窗口内时，吞掉消息不显示到玩家聊天框。
 * 吞掉前先把消息交给 LocateTool.onGameMessage，保证 /locate 坐标解析链不受影响。
 * 注入两个公开 addMessage 重载（1.20.1 yarn 映射），覆盖系统命令反馈的所有入口。
 */
@Mixin(ChatHud.class)
public abstract class ChatHudMixin {

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"), cancellable = true)
    private void sscCompass$suppressSimple(Text message, CallbackInfo ci) {
        if (CommandFeedbackSuppressor.isSuppressing()) {
            // 先喂给 LocateTool 保证 /locate 反馈仍可被工具解析
            LocateTool.onGameMessage(message);
            if (CommandFeedbackSuppressor.shouldSuppress(message)) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V",
            at = @At("HEAD"), cancellable = true)
    private void sscCompass$suppressFull(Text message, MessageSignatureData signatureData,
                                         net.minecraft.client.gui.hud.MessageIndicator indicator, CallbackInfo ci) {
        if (CommandFeedbackSuppressor.isSuppressing()) {
            LocateTool.onGameMessage(message);
            if (CommandFeedbackSuppressor.shouldSuppress(message)) {
                ci.cancel();
            }
        }
    }
}
