package com.mangzai.shapeshiftercompass.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mangzai.shapeshiftercompass.ShapeShifterCompass;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** 本地配置：厂商 / baseUrl / apiKey / model / 采样参数。存于 config/ssc_compass.json。 */
public class CompassConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("ssc_compass.json");
    private static CompassConfig INSTANCE = new CompassConfig();

    public AiProvider provider = AiProvider.OPENAI;
    public String baseUrl = AiProvider.OPENAI.baseUrl;
    public String apiKey = "";
    public String model = "gpt-4o-mini";
    public double temperature = 0.7;
    public int maxTokens = 1024;
    public int maxHistory = 20;
    /** 思考模式（reasoning_effort）：关闭则不传该字段；开启时按档位传给支持思考的模型（如 GLM-5.2） */
    public boolean thinkingEnabled = false;
    /** 思考深度档位：minimal / low / medium / high / xhigh / max */
    public String reasoningEffort = "medium";

    // HUD 布局
    public int hudBallX = -1;
    public int hudBallY = -1;
    public int hudBoxX = 4;
    public int hudBoxY = 4;
    public int hudBoxWidth = 160;
    public boolean hudVisible = true;
    public int hudBgAlpha = 128;
    public int hudFontPct = 75;
    public int chatFontPct = 100;
    public int hudBoxHeight = 112;
    public boolean cheatEnabled = false;

    public static CompassConfig get() {
        return INSTANCE;
    }

    public static void load() {
        try {
            if (Files.exists(PATH)) {
                String json = Files.readString(PATH);
                CompassConfig loaded = GSON.fromJson(json, CompassConfig.class);
                if (loaded != null) {
                    INSTANCE = loaded;
                }
            } else {
                save();
            }
        } catch (Exception e) {
            ShapeShifterCompass.LOGGER.error("Failed to load config", e);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, GSON.toJson(INSTANCE));
        } catch (IOException e) {
            ShapeShifterCompass.LOGGER.error("Failed to save config", e);
        }
    }

    public boolean hasKey() {
        return apiKey != null && !apiKey.isBlank();
    }

    /** 拼出 /chat/completions 端点，兼容 baseUrl 末尾有无斜杠。 */
    public String endpoint() {
        String b = (baseUrl == null || baseUrl.isBlank()) ? provider.baseUrl : baseUrl;
        if (b.endsWith("/")) {
            b = b.substring(0, b.length() - 1);
        }
        return b + "/chat/completions";
    }
}
