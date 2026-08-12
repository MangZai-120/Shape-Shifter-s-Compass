package com.mangzai.shapeshiftercompass.ai;

/**
 * 共享的 AI 调用状态：供大窗与小框同步显示「思考中」与「停止生成」。
 * AiClient 发送前置 busy=true，完成/取消/出错后置 false。
 */
public final class CompassState {
    private static volatile boolean busy = false;

    private CompassState() {}

    public static boolean isBusy() {
        return busy;
    }

    public static void setBusy(boolean v) {
        busy = v;
    }
}
