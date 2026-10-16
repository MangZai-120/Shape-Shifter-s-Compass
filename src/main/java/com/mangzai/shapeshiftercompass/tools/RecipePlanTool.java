package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import java.util.*;
import java.util.function.Predicate;

/** Read-only planning: the backpack is copied, then shared across all simulated steps. */
public final class RecipePlanTool implements CompassTool {
    private record Need(List<ItemStack> choices, int count, Predicate<ItemStack> accepts) {}
    private record Plan(String id, ItemStack output, List<Need> needs, String station, String note, boolean example) {}
    private final List<Plan> recipes = new ArrayList<>();
    private String sourceNote = "";
    @Override public String name() { return "plan_crafting"; }
    @Override public String description() { return "按当前背包计算新增1至64个目标物品的材料缺口与最多4层制作步骤，保留药水/卷轴的物品数据。可读已同步SSCA特殊加工示例。不是自动制作，不扫描箱子；动态示例不能证明可直接合成。item可留空使用选中物品。"; }
    @Override public JsonObject parameters() { return ToolArguments.schema("item", "count"); }
    @Override public String execute(JsonObject args) {
        var mc=MinecraftClient.getInstance();
        if(mc.player==null || mc.world==null) return "玩家当前不在游戏世界中。";
        ItemStack target=ItemQuery.resolve(ToolArguments.string(args,"item"));
        if(target.isEmpty()) return "找不到目标物品。请输入物品ID/当前语言名称，或选中物品后打开罗盘。";
        int count=1;
        try { if(args!=null && args.has("count")) count=args.get("count").getAsBigDecimal().intValueExact(); }
        catch(RuntimeException bad) { return "制作数量必须是1至64的整数。"; }
        if(count<1 || count>64) return "制作数量必须在1至64之间。";
        load();
        List<ItemStack> stock=planningStock(target,mc.player.getInventory().main,mc.player.getInventory().offHand);
        StringBuilder out=new StringBuilder("计划新增 ").append(target.getName().getString()).append(" ×").append(count)
                .append("\n只统计当前背包与副手；不消耗或移动任何物品。已有同款目标不抵减新增数量，也不作为循环回收的原料。\n");
        int[] budget={40};
        expand(target,count,stock,new HashSet<>(),0,budget,out);
        return out.append("\n依据：当前服务器同步配方及当前背包快照。").append(sourceNote)
                .append("\n后续分配包含本路线预计产物的余量，仍须取得列出的原料。路线最多4层/40步，不保证全局最省材料；工具、燃料、加工能量与特殊前置另行满足。含动态物品的示例以实际配方匹配为准。未收录的Apoli合成可继续查知识库。")
                .toString();
    }
    static List<ItemStack> planningStock(ItemStack target,List<ItemStack> backpack,List<ItemStack> offhand) {
        List<ItemStack> result=new ArrayList<>();
        for(List<ItemStack> source:List.of(backpack,offhand)) for(ItemStack stack:source)
            if(!stack.isEmpty() && !ItemStack.canCombine(stack,target)) result.add(stack.copy());
        return result;
    }
    private void load() {
        recipes.clear(); sourceNote="";
        var mc=MinecraftClient.getInstance();
        int scanned=0;
        for(Recipe<?> recipe:mc.world.getRecipeManager().values()) {
            if(++scanned>20000) { sourceNote=" 配方数量超过本次查询上限，部分配方未检索。"; break; }
            try {
                ItemStack output=recipe.getOutput(mc.world.getRegistryManager());
                if(output.isEmpty()) continue;
                List<Need> needs=new ArrayList<>();
                for(Ingredient ingredient:recipe.getIngredients()) if(!ingredient.isEmpty()) needs.add(new Need(
                        Arrays.asList(ingredient.getMatchingStacks()),1,ingredient::test));
                if(needs.isEmpty()) continue;
                recipes.add(new Plan(recipe.getId().toString(),output.copy(),needs,recipe.getType().toString(),"",false));
            } catch(RuntimeException ignored) { /* dynamic output, handled below if supported */ }
        }
        try {
            Class<?> api=AddonAccess.type("compat.recipeviewer.SscViewerRecipes");
            List<?> crafting=(List<?>)api.getMethod("crafting",RecipeManager.class).invoke(null,mc.world.getRecipeManager());
            for(Object example:crafting) {
                List<Need> needs=new ArrayList<>();
                for(Object row:(List<?>)AddonAccess.get(example,"grid")) {
                    List<ItemStack> choices=new ArrayList<>();
                    for(Object stack:(List<?>)row) if(stack instanceof ItemStack s && !s.isEmpty()) choices.add(s.copy());
                    if(!choices.isEmpty()) needs.add(exact(choices));
                }
                String id=AddonAccess.get(example,"id").toString();
                recipes.removeIf(p->p.id.equals(id));
                recipes.add(new Plan(id,((ItemStack)AddonAccess.get(example,"output")).copy(),needs,"工作台",
                        ((net.minecraft.text.Text)AddonAccess.get(example,"description")).getString(),true));
            }
            for(String method:List.of("brewing","processes")) for(Object example:(List<?>)api.getMethod(method).invoke(null)) {
                List<Need> needs=new ArrayList<>(); String station;
                if(method.equals("brewing")) {
                    needs.add(exact(List.of(((ItemStack)AddonAccess.get(example,"input")).copy())));
                    needs.add(exact(List.of(((ItemStack)AddonAccess.get(example,"ingredient")).copy()))); station="酿造台";
                } else {
                    for(Object input:(List<?>)AddonAccess.get(example,"inputs")) needs.add(exact(List.of(((ItemStack)input).copy())));
                    station=((ItemStack)AddonAccess.get(example,"workstation")).getName().getString();
                }
                recipes.add(new Plan(AddonAccess.get(example,"id").toString(),((ItemStack)AddonAccess.get(example,"output")).copy(),needs,station,
                        method.equals("processes")?((net.minecraft.text.Text)AddonAccess.get(example,"description")).getString():"酿造示例",true));
            }
            sourceNote+=" 已包含SSCA特殊合成、酿造与加工示例（它们的服务端条件仍需满足）。";
        } catch(ReflectiveOperationException | LinkageError unavailable) {
            sourceNote+=" 当前未读取到SSCA特殊加工接口，不能把未列出的加工认定为不存在。";
        }
        recipes.sort(Comparator.comparing(Plan::id));
    }
    private static Need exact(List<ItemStack> choices) {
        return new Need(choices,Math.max(1,choices.get(0).getCount()),s->choices.stream().anyMatch(c->ItemStack.canCombine(c,s)));
    }
    private static MaterialAllocator.Allocation allocate(Plan plan,int times,List<ItemStack> stock) {
        int[] available=stock.stream().mapToInt(ItemStack::getCount).toArray(), required=new int[plan.needs.size()];
        boolean[][] accepts=new boolean[stock.size()][required.length];
        for(int j=0;j<required.length;j++) {
            required[j]=(int)Math.min(100000L,(long)plan.needs.get(j).count*times);
            for(int i=0;i<stock.size();i++) accepts[i][j]=plan.needs.get(j).accepts.test(stock.get(i));
        }
        return MaterialAllocator.allocate(available,required,accepts);
    }
    private void expand(ItemStack target,int count,List<ItemStack> stock,Set<String> path,int depth,int[] budget,StringBuilder out) {
        String prefix="  ".repeat(depth), key=Registries.ITEM.getId(target.getItem())+String.valueOf(target.getNbt());
        if(depth>=4 || budget[0]--<=0 || !path.add(key)) { out.append(prefix).append("待获取：").append(target.getName().getString()).append(" ×").append(count).append("（深度/循环边界）\n"); return; }
        Plan best=null; int bestMissing=Integer.MAX_VALUE;
        for(Plan p:recipes) if(p.output.getItem()==target.getItem() && (!target.hasNbt() || ItemStack.canCombine(p.output,target))) {
            int times=(count+p.output.getCount()-1)/p.output.getCount();
            int missing=Arrays.stream(allocate(p,times,stock).missing()).sum();
            if(missing<bestMissing) { best=p; bestMissing=missing; }
        }
        if(best==null) { out.append(prefix).append("待获取：").append(TargetContext.describe(target).replace('\n',' ')).append(" ×").append(count).append("（无已读取的制作配方）\n"); path.remove(key); return; }
        int times=(count+best.output.getCount()-1)/best.output.getCount();
        var allocation=allocate(best,times,stock);
        out.append(prefix).append(target.getName().getString()).append("：").append(times).append(" 次，产出 ").append(best.output.getCount()*times)
                .append("；使用 ").append(best.station).append(best.example?"【加工/动态示例】":"").append('\n');
        if(!best.note.isBlank()) out.append(prefix).append(best.note).append('\n');
        out.append(prefix).append("配方：").append(best.id).append('\n');
        for(int i=0;i<stock.size();i++) stock.get(i).decrement(allocation.used()[i]);
        for(int j=0;j<best.needs.size();j++) {
            Need need=best.needs.get(j); int missing=allocation.missing()[j];
            out.append(prefix).append("  材料：").append(need.choices.stream().limit(4).map(s->s.getName().getString()).distinct().reduce((a,b)->a+" / "+b).orElse("特殊材料"))
                    .append("，需要 ").append((long)need.count*times).append("，本路线分配后缺 ").append(missing).append('\n');
            if(missing>0 && !need.choices.isEmpty()) {
                // Surplus produced by an earlier branch can satisfy a later overlapping demand.
                for(ItemStack s:stock) if(need.accepts.test(s)) { int take=Math.min(missing,s.getCount()); s.decrement(take); missing-=take; }
                if(missing>0) expand(need.choices.get(0).copyWithCount(1),missing,stock,new HashSet<>(path),depth+1,budget,out);
            }
        }
        int surplus=best.output.getCount()*times-count;
        if(surplus>0) stock.add(best.output.copyWithCount(surplus));
        path.remove(key);
    }
}
