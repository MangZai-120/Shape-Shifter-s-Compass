package com.mangzai.shapeshiftercompass.tools;

import com.google.gson.Gson;
import com.mangzai.shapeshiftercompass.ai.*;
import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import java.util.*;

public final class FeatureRegressionTest {
    private static int checks;
    private static void check(boolean ok,String message) { checks++; if(!ok) throw new AssertionError(message); }
    private static int brute(int index,List<Integer> units,int[] remaining,boolean[][] accepts) {
        if(index==units.size()) return 0;
        int best=brute(index+1,units,remaining,accepts),type=units.get(index);
        for(int j=0;j<remaining.length;j++) if(remaining[j]>0 && accepts[type][j]) {
            remaining[j]--; best=Math.max(best,1+brute(index+1,units,remaining,accepts)); remaining[j]++;
        }
        return best;
    }
    private static void allocations() {
        var overlap=MaterialAllocator.allocate(new int[]{1,1},new int[]{1,1},new boolean[][]{{true,true},{true,false}});
        check(Arrays.stream(overlap.missing()).sum()==0,"narrow ingredient is not starved by a broad tag");
        check(Arrays.stream(MaterialAllocator.allocate(new int[]{4},new int[]{3,3},new boolean[][]{{true,true}}).missing()).sum()==2,
                "one stack cannot satisfy two full demands");
        check(MaterialAllocator.allocate(new int[0],new int[]{2},new boolean[0][1]).missing()[0]==2,"empty inventory");
        Random random=new Random(0xC0A55);
        for(int round=0;round<250;round++) {
            int n=1+random.nextInt(3),m=1+random.nextInt(3);
            int[] available=new int[n],required=new int[m]; boolean[][] accepts=new boolean[n][m]; var units=new ArrayList<Integer>();
            for(int i=0;i<n;i++) { available[i]=random.nextInt(3); for(int k=0;k<available[i];k++) units.add(i); }
            for(int j=0;j<m;j++) required[j]=random.nextInt(4);
            for(int i=0;i<n;i++) for(int j=0;j<m;j++) accepts[i][j]=random.nextBoolean();
            int[] original=available.clone(),needs=required.clone();
            var result=MaterialAllocator.allocate(available,required,accepts);
            int matched=Arrays.stream(required).sum()-Arrays.stream(result.missing()).sum();
            check(matched==brute(0,units,required.clone(),accepts),"optimal allocation against exhaustive oracle "+round);
            check(Arrays.stream(result.used()).sum()==matched,"conservation of materials "+round);
            check(Arrays.equals(original,available) && Arrays.equals(needs,required),"planner input is not mutated "+round);
        }
    }
    private static void evidence() throws Exception {
        Gson gson=new Gson();
        ChatMessage old=gson.fromJson("{\"role\":\"assistant\",\"content\":\"old answer\"}",ChatMessage.class);
        check(old.evidence==null,"old chat history remains readable");
        ChatMessage message=new ChatMessage("assistant","answer");
        message.evidence=List.of(Evidence.of("query_knowledge","source | version\ntext"));
        ChatMessage copy=gson.fromJson(gson.toJson(message),ChatMessage.class);
        check(copy.evidence.size()==1 && copy.evidence.get(0).detail().contains("version"),"evidence persists with its answer");
        check(Evidence.of("plan_crafting","x".repeat(10000)).detail().length()<8100,"evidence storage bounded");
        check(Evidence.of("web_search","result").basis().contains("联网"),"web evidence not mislabeled as live game state");
    }
    private static void stacks() {
        SharedConstants.createGameVersion(); Bootstrap.initialize();
        ItemStack poison=ItemQuery.resolve("minecraft:potion{Potion:\"minecraft:poison\"}");
        ItemStack healing=ItemQuery.resolve("minecraft:potion{Potion:\"minecraft:healing\"}");
        check(!poison.isEmpty() && !healing.isEmpty(),"SNBT item query resolves");
        check(!ItemStack.canCombine(poison,healing),"different potion NBT is not interchangeable");
        var stock=RecipePlanTool.planningStock(poison,List.of(poison,healing),List.of());
        check(stock.size()==1 && ItemStack.canCombine(stock.get(0),healing),"new-output planning excludes recycling the existing target, while preserving different potion inputs");
        stock.get(0).decrement(1);
        check(healing.getCount()==1,"simulated material use cannot modify real inventory stacks");
        ItemStack snapshot=poison.copy();
        snapshot.getOrCreateNbt().put("RuneScheme",new NbtCompound());
        snapshot.getOrCreateNbt().putString("pages","private narrative");
        String description=TargetContext.describe(snapshot);
        check(description.contains("minecraft:poison") && !description.contains("RuneScheme") && !description.contains("private narrative"),
                "target description keeps relevant item data and excludes rune solutions/story pages");
        check(!poison.getOrCreateNbt().contains("RuneScheme"),"copied target data leaves source untouched");
        check(ItemQuery.resolve("minecraft:potion{bad syntax").isEmpty(),"invalid SNBT is rejected");
        check(!ItemQuery.resolve("minecraft:oak_planks").isEmpty(),"ordinary item ID query");
        check(ItemQuery.resolve("minecraft:air").isEmpty(),"air is not a crafting target");
    }
    public static void main(String[] args) throws Exception {
        System.setProperty("java.class.path",java.nio.file.Files.readString(java.nio.file.Path.of(System.getProperty("compass.testClasspath"))));
        var knot=new net.fabricmc.loader.impl.launch.knot.Knot(net.fabricmc.api.EnvType.CLIENT);
        ClassLoader loader=knot.init(new String[0]); Thread.currentThread().setContextClassLoader(loader);
        Class.forName(FeatureRegressionTest.class.getName()+"$Probe",true,loader).getMethod("run").invoke(null);
    }
    public static final class Probe {
        public static void run() throws Exception {
            allocations(); evidence(); stacks();
            com.mangzai.shapeshiftercompass.ai.AiResponseRegressionTest.run();
            Class<?> handled=Class.forName("net.minecraft.client.gui.screen.ingame.HandledScreen",false,Probe.class.getClassLoader());
            check(com.mangzai.shapeshiftercompass.mixin.client.HandledScreenAccessor.class.isAssignableFrom(handled),"hovered slot accessor applies to actual Minecraft class");
            var mod=net.fabricmc.loader.api.FabricLoader.getInstance().getModContainer("ssc_compass").orElseThrow();
            var metadata=com.google.gson.JsonParser.parseString(java.nio.file.Files.readString(mod.findPath("fabric.mod.json").orElseThrow())).getAsJsonObject();
            check(!metadata.getAsJsonObject("entrypoints").has("jei_mod_plugin"),"removed local viewer buttons do not leave a JEI entrypoint");
            System.out.println("Compass feature regression PASS: "+checks+" checks; exhaustive allocation oracle, evidence migration, real item/NBT bootstrap and accessor transformation. No game window/world or AI API started.");
        }
    }
}
