package com.mangzai.shapeshiftercompass.ai;

/** Stored locally with an answer; never implicitly appended to later API requests. */
public record Evidence(String title,String basis,String detail,long capturedAt) {
    public static Evidence of(String tool,String result) {
        String basis=switch(tool) {
            case "query_knowledge" -> "对应安装版本的知识库；数值可能是默认值";
            case "web_search" -> "联网查询结果；需结合原文来源、日期和模组版本判断";
            case "plan_crafting","get_evolution_advice" -> "本次客户端快照、本地计算及所注明的参考资料；特殊条件仍需满足";
            default -> "本次工具返回的读数或操作结果；不能代替服务器后续校验";
        };
        String title=switch(tool) {
            case "get_spellbook_status" -> "魔法书与施法条件";
            case "add_spellbook_experience" -> "魔法书经验操作";
            case "get_target_info" -> "选取目标";
            case "plan_crafting" -> "材料与制作路线";
            case "get_evolution_advice" -> "进化条件";
            case "query_knowledge" -> "知识库资料";
            case "query_recipe" -> "配方资料";
            case "get_form_status" -> "形态与资源";
            case "get_player_status" -> "人物状态";
            case "web_search" -> "网络来源";
            default -> tool;
        };
        return new Evidence(title,basis,result.length()>8000?result.substring(0,8000)+"\n（依据截取至8000字符）":result,System.currentTimeMillis());
    }
}
