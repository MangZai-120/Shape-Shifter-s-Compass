package com.mangzai.shapeshiftercompass.ai;

import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;

/**
 * 命令反馈抑制器：AI 工具（run_command / locate_structure）执行命令时开启一个时间窗口，
 * 窗口内到达的命令反馈消息由 ChatHudMixin 拦截、不显示到玩家聊天框，仅写入后台日志。
 *
 * 设计要点：
 * - 窗口含「起始时间 + 超时」，避免无限抑制；超时自动失效。
 * - 多条命令重叠时以最近一次 startSuppression 为准（单条队列已足够覆盖典型场景，
 *   因为工具是串行执行的）。
 * - 抑制前由 ChatHudMixin 先把消息喂给 LocateTool.onGameMessage，保证 /locate 坐标解析不受影响。
 */
public final class CommandFeedbackSuppressor {
    /** 默认抑制窗口（覆盖绝大多数命令反馈的到达延迟）。 */
    private static final long DEFAULT_TIMEOUT_MS = 3000L;
    /** /locate 反馈可能较慢，给更长的窗口。 */
    private static final long LOCATE_TIMEOUT_MS = 6500L;

    private static volatile long suppressUntil = 0L;
    private static volatile String lastCmdHead = "";
    /** 命令结果追踪：窗口内第一条反馈会 complete 它，供工具判定命令成败。null 表示当前不追踪结果。 */
    private static volatile CompletableFuture<String> pendingResult;

    private CommandFeedbackSuppressor() {}

    /** 工具执行命令前调用：开启抑制窗口。cmdHead 为命令首词（如 give/locate），用于日志。 */
    public static void startSuppression(String cmdHead) {
        lastCmdHead = cmdHead == null ? "" : cmdHead.toLowerCase();
        long timeout = "locate".equals(lastCmdHead) ? LOCATE_TIMEOUT_MS : DEFAULT_TIMEOUT_MS;
        suppressUntil = System.currentTimeMillis() + timeout;
        pendingResult = null; // 普通抑制不追踪结果
    }

    /**
     * 开启抑制窗口并返回一个 future：窗口内到达的第一条命令反馈会 complete 它，供工具判定成败。
     * @param cmdHead 命令首词
     */
    public static CompletableFuture<String> startSuppressionAndAwait(String cmdHead) {
        startSuppression(cmdHead);
        CompletableFuture<String> f = new CompletableFuture<>();
        pendingResult = f;
        return f;
    }

    /**
     * 判断一条命令反馈是否表示「执行失败」（语法/参数/目标错误等），供工具决定是否需要修正重试。
     * 保守匹配明确的错误短语，避免把成功反馈误判为失败。
     */
    public static boolean isFailureFeedback(String feedback) {
        if (feedback == null || feedback.isEmpty()) {
            return false;
        }
        String s = feedback.toLowerCase();
        return s.contains("unknown command")
                || s.contains("incorrect argument")
                || s.contains("expected ")
                || s.contains("invalid")
                || s.contains("unparseable")
                || s.contains("no player was found")
                || s.contains("no entity was found")
                || s.contains("that player does not exist")
                || s.contains("no targets matched")
                || s.contains("cannot")
                || feedback.contains("未知")
                || feedback.contains("无效")
                || feedback.contains("找不到")
                || feedback.contains("没有找到")
                || feedback.contains("不是有效")
                || feedback.contains("无法解析");
    }

    /** 立即关闭抑制窗口（命令链结束）。 */
    public static void endSuppression() {
        suppressUntil = 0L;
    }

    /** 当前是否处于抑制窗口内。 */
    public static boolean isSuppressing() {
        return System.currentTimeMillis() < suppressUntil;
    }

    /**
     * ChatHudMixin 在 addMessage HEAD 调用：若处于窗口内则判定为命令反馈，吞掉并记日志。
     * @return true 表示该消息已被吞（mixin 应取消原 addMessage）。
     */
    public static boolean shouldSuppress(Text message) {
        if (!isSuppressing()) {
            return false;
        }
        // 窗口内的消息一律视为命令反馈予以抑制；空消息也一并吞掉。
        String content;
        try {
            content = message == null ? "" : message.getString();
        } catch (Exception e) {
            content = "";
        }
        ShapeShifterCompass.LOGGER.info("[Compass] 命令反馈已隐藏（head={}）：{}", lastCmdHead, content);
        CompletableFuture<String> pr = pendingResult;
        if (pr != null && !pr.isDone()) {
            pr.complete(content);
        }
        return true;
    }
}
