package com.mangzai.shapeshiftercompass.ui;

import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.List;

/**
 * 聊天输入框的历史导航：按 ↑/↓ 依次调出之前发送过的消息（类似终端/命令行历史）。
 * index=-1 表示未在浏览历史（显示的是当前草稿）；draft 保存开始浏览前的草稿，
 * 以便 ↓ 回到底部时还原玩家原本正在输入的内容。
 */
public class InputHistory {
    private static final int KEY_UP = 265;
    private static final int KEY_DOWN = 264;

    private int index = -1;
    private String draft = "";

    /**
     * 处理一次按键。history 为历史消息列表（最近发送的排在最前）。
     * @return true 表示已消费该按键（↑/↓ 且存在历史）。
     */
    public boolean handle(int keyCode, TextFieldWidget input, List<String> history) {
        if (keyCode != KEY_UP && keyCode != KEY_DOWN) {
            return false;
        }
        if (history.isEmpty()) {
            return false;
        }
        if (keyCode == KEY_UP) {
            if (index == -1) {
                draft = input.getText();
                index = 0;
            } else if (index < history.size() - 1) {
                index++;
            }
            input.setText(history.get(index));
        } else { // KEY_DOWN
            if (index == -1) {
                return true;
            }
            if (index > 0) {
                index--;
                input.setText(history.get(index));
            } else {
                index = -1;
                input.setText(draft);
            }
        }
        input.setCursorToEnd();
        return true;
    }

    /** 发送消息后调用，重置浏览状态回到「输入新内容」。 */
    public void reset() {
        index = -1;
        draft = "";
    }
}
