package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;

/**
 * 获取当前世界种子。单人存档可直接从内置服务器读取（不需 op）；多人服务器客户端通常拿不到，提示玩家自行 /seed。
 */
public class SeedTool implements CompassTool {
    @Override
    public String name() {
        return "get_seed";
    }

    @Override
    public String description() {
        return "获取当前世界的种子。单人存档可直接读取；多人服务器一般无法从客户端获取（需服务端支持 /seed）。";
    }

    @Override
    public JsonObject parameters() {
        JsonObject schema = new JsonObject();
        schema.addProperty("type", "object");
        schema.add("properties", new JsonObject());
        return schema;
    }

    @Override
    public String execute(JsonObject args) {
        MinecraftClient mc = MinecraftClient.getInstance();
        MinecraftServer server = mc.getServer();
        if (server != null) {
            try {
                long seed = server.getOverworld().getSeed();
                return "当前世界种子：" + seed;
            } catch (Exception e) {
                return "读取种子失败：" + e.getMessage();
            }
        }
        return "无法从客户端获取种子（多人服务器需服务端支持）。你可以在游戏内执行 /seed 试试。";
    }
}
