package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import com.mangzai.shapeshiftercompass.knowledge.KnowledgeBase;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import java.util.*;

public final class EvolutionAdviceTool implements CompassTool {
    @Override public String name() { return "get_evolution_advice"; }
    @Override public String description() { return "结合当前形态、客户端同步进化路线、等级/点数、解锁节点及背包进化道具给出下一步条件。只读，不自动解锁，不泄露剧情或符文解法。"; }
    @Override public JsonObject parameters() { return ToolArguments.schema(); }
    @Override public String execute(JsonObject args) {
        var mc=MinecraftClient.getInstance();
        if(mc.player==null) return "玩家当前不在游戏世界中。";
        String form=SscBridge.getCurrentFormId(mc.player);
        if(form==null) return "当前形态尚未读取成功，暂时不能判断进化路线。";
        StringBuilder out=new StringBuilder("当前形态：").append(form).append('\n');
        if(!FabricLoader.getInstance().isModLoaded("ssc_addon")) return out.append(KnowledgeBase.search(form,2)).toString();
        try {
            Object key=AddonAccess.type("evolution.RegEvolutionComponent").getField("EVOLUTION").get(null);
            Object component=SscReflection.component(key,mc.player,Class::forName);
            String routeId=String.valueOf(AddonAccess.get(component,"getRoute"));
            int points=((Number)AddonAccess.get(component,"getPoints")).intValue();
            out.append("进化路线：").append(routeId.isEmpty()?"未选择SSCA路线":routeId).append("；进化等级：")
                    .append(AddonAccess.get(component,"getEvoLevel")).append("；可用点数：").append(points).append('\n');
            Object registry=AddonAccess.type("evolution.EvolutionRegistry").getField("INSTANCE").get(null);
            Map<?,?> routes=(Map<?,?>)AddonAccess.get(registry,"all");
            if(routes.isEmpty()) out.append("服务器路线镜像尚未读取到；不能把空表当作没有进化路线。\n");
            Object route=routeId.isEmpty()?registry.getClass().getMethod("getRouteByStartForm",Identifier.class).invoke(registry,new Identifier(form))
                    :registry.getClass().getMethod("getRoute",String.class).invoke(registry,routeId);
            if(route!=null) {
                out.append("路线：").append(Text.translatable(String.valueOf(AddonAccess.field(route,"displayNameKey"))).getString()).append('\n');
                if(!Boolean.TRUE.equals(AddonAccess.field(route,"enabled"))) out.append("此路线被服务器关闭。\n");
                out.append("路线起点：").append(AddonAccess.field(route,"startForm")).append('\n');
                if(!form.equals(String.valueOf(AddonAccess.field(route,"startForm")))) out.append("当前形态不是此路线的起点，可能正在变形或同步；不能据此认定可以继续进化。\n");
                var check=component.getClass().getMethod("isUnlocked",String.class);
                List<String> lockedBranches=new ArrayList<>();
                int shown=0;
                for(Object node:(List<?>)AddonAccess.field(route,"nodes")) {
                    String id=String.valueOf(AddonAccess.field(node,"id"));
                    boolean unlocked=Boolean.TRUE.equals(check.invoke(component,id));
                    if(!unlocked && !String.valueOf(AddonAccess.field(node,"branch")).isEmpty()) lockedBranches.add(id);
                    if(unlocked || shown++>=24) continue;
                    List<String> missing=new ArrayList<>();
                    for(Object parent:(List<?>)AddonAccess.field(node,"prereqs")) if(!Boolean.TRUE.equals(check.invoke(component,parent.toString()))) missing.add(parent.toString());
                    int cost=((Number)AddonAccess.field(node,"cost")).intValue();
                    boolean auto=Boolean.TRUE.equals(AddonAccess.field(node,"autoUnlock"));
                    out.append("未解锁：").append(Text.translatable(String.valueOf(AddonAccess.field(node,"nameKey"))).getString())
                            .append("；").append(auto?"由进度自动解锁":"需要 "+cost+" 点，当前 "+points+" 点")
                            .append(missing.isEmpty()?"；前置节点已满足":"；缺前置 "+String.join("、",missing)).append('\n');
                }
                out.append(lockedBranches.isEmpty()?"本路线分支节点均已解锁（仍需满足道具与变形条件）。\n":"继续进化尚缺分支节点："+String.join("、",lockedBranches)+"\n");
            }
        } catch(ReflectiveOperationException | LinkageError unavailable) {
            out.append("进化进度接口未就绪；以下只提供对应版本的路线资料，不能假定节点已解锁。\n");
        }
        out.append("背包进化道具：\n");
        for(String id:List.of("ssc_addon:sp_upgrade_thing","ssc_addon:evolution_stone")) {
            int count=0;
            for(var stack:mc.player.getInventory().main) if(net.minecraft.registry.Registries.ITEM.getId(stack.getItem()).toString().equals(id)) count+=stack.getCount();
            for(var stack:mc.player.getInventory().offHand) if(net.minecraft.registry.Registries.ITEM.getId(stack.getItem()).toString().equals(id)) count+=stack.getCount();
            out.append(id).append(" ×").append(count).append('\n');
        }
        out.append("月髓环还要求诅咒之月的实际夜晚；这里不凭本地月相推定服务器已满足条件。\n");
        return out.append(KnowledgeBase.search(form+" 进化",3)).toString();
    }
}
