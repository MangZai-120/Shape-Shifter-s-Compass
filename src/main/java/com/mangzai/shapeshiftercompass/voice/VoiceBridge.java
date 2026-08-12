package com.mangzai.shapeshiftercompass.voice;

import net.fabricmc.loader.api.FabricLoader;

/**
 * 语音桥接占位（Phase 3 接入 Simple Voice Chat + STT/TTS）。
 * Phase 1 仅预留接口与注册点，保持模块化、不接任何实现，避免对 SVC 的硬依赖。
 *
 * <p>Phase 3 计划：SVC 采集麦克风 → {@link SpeechToText} → AI（复用 AiClient）
 * → {@link TextToSpeech} → SVC 播放。此处提供 STT/TTS 的注册与就绪查询。</p>
 */
public final class VoiceBridge {
    private static SpeechToText stt;
    private static TextToSpeech tts;

    private VoiceBridge() {}

    public static void setSpeechToText(SpeechToText impl) {
        stt = impl;
    }

    public static void setTextToSpeech(TextToSpeech impl) {
        tts = impl;
    }

    public static SpeechToText speechToText() {
        return stt;
    }

    public static TextToSpeech textToSpeech() {
        return tts;
    }

    public static boolean sttReady() {
        return stt != null;
    }

    public static boolean ttsReady() {
        return tts != null;
    }

    /** Simple Voice Chat 是否在场（Phase 3 语音输入/输出的前提）。 */
    public static boolean svcAvailable() {
        return FabricLoader.getInstance().isModLoaded("voicechat");
    }
}
