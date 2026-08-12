package com.mangzai.shapeshiftercompass.ai;

import com.mangzai.shapeshiftercompass.config.CompassConfig;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;

/**
 * 作弊功能权限守卫：作弊开关 + 真实 op 权限双重校验。
 * 权限等级由服务器下发（客户端无法篡改），因此 AI 或玩家用 prompt 声称「我有权限」无法绕过此校验。
 */
public final class CheatGuard {
    private CheatGuard() {}

    /** 玩家是否拥有 op 权限（等级 ≥ 2，服务器下发的真实值）。 */
    public static boolean isOp() {
        MinecraftClient mc = MinecraftClient.getInstance();
        return mc.player != null && mc.player.hasPermissionLevel(2);
    }

    /** 作弊功能是否允许：必须同时「设置里开启作弊」且「拥有真实 op 权限」。 */
    public static boolean isCheatAllowed() {
        return CompassConfig.get().cheatEnabled && isOp();
    }

    /** 当前 Minecraft 版本号（如 1.20.1）。 */
    public static String mcVersion() {
        try {
            return SharedConstants.getGameVersion().getName();
        } catch (Exception e) {
            return "unknown";
        }
    }
}
