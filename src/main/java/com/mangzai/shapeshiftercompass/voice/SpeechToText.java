package com.mangzai.shapeshiftercompass.voice;

import java.util.function.Consumer;

/**
 * 语音识别 (STT) 接口——Phase 3 实现（例如接 Whisper API）。
 * 把麦克风采集的音频 PCM 转成文字，供 AI 处理。Phase 1 仅预留接口。
 */
public interface SpeechToText {
    void transcribe(byte[] audioPcm, int sampleRate, Consumer<String> onResult, Consumer<String> onError);
}
