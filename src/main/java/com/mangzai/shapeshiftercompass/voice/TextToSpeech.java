package com.mangzai.shapeshiftercompass.voice;

import java.util.function.Consumer;

/**
 * 语音合成 (TTS) 接口——Phase 3 实现（例如接 OpenAI TTS API）。
 * 把 AI 回复的文字转成音频 PCM，交由 Simple Voice Chat 播放。Phase 1 仅预留接口。
 */
public interface TextToSpeech {
    void synthesize(String text, Consumer<byte[]> onAudioPcm, Consumer<String> onError);
}
