package com.mangzai.shapeshiftercompass.tools;
import com.google.gson.JsonObject;
public final class TargetInfoTool implements CompassTool {
    @Override public String name() { return "get_target_info"; }
    @Override public String description() { return "读取玩家打开罗盘时选取的悬停物品、准星方块或实体，带准确ID和记录时间。不得把旧快照当作当前状态。"; }
    @Override public JsonObject parameters() { return ToolArguments.schema(); }
    @Override public String execute(JsonObject args) { return TargetContext.report(); }
}
