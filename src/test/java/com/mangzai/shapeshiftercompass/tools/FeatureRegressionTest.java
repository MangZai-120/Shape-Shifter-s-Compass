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
        check(!ItemStack.areItemsAndComponentsEqual(poison,healing),"different potion NBT is not interchangeable");
        var stock=RecipePlanTool.planningStock(poison,List.of(poison,healing),List.of());
        check(stock.size()==1 && ItemStack.areItemsAndComponentsEqual(stock.get(0),healing),"new-output planning excludes recycling the existing target, while preserving different potion inputs");
        stock.get(0).decrement(1);
        check(healing.getCount()==1,"simulated material use cannot modify real inventory stacks");
        ItemStack snapshot=poison.copy();
        var privateData = new NbtCompound();
        privateData.put("RuneScheme",new NbtCompound());
        privateData.putString("pages","private narrative");
        snapshot.set(net.minecraft.component.DataComponentTypes.CUSTOM_DATA,
                net.minecraft.component.type.NbtComponent.of(privateData));
        String description=TargetContext.describe(snapshot);
        check(description.contains("minecraft:poison") && !description.contains("RuneScheme") && !description.contains("private narrative"),
                "target description keeps relevant item data and excludes rune solutions/story pages");
        check(!poison.contains(net.minecraft.component.DataComponentTypes.CUSTOM_DATA),"copied target data leaves source untouched");
        check(ItemQuery.resolve("minecraft:potion{bad syntax").isEmpty(),"invalid SNBT is rejected");
        check(!ItemQuery.resolve("minecraft:oak_planks").isEmpty(),"ordinary item ID query");
        check(ItemQuery.resolve("minecraft:air").isEmpty(),"air is not a crafting target");
        ItemStack legacyList=ItemQuery.resolve("minecraft:stone{Tags:[\"first\",\"second\"],Level:2}");
        check(!legacyList.isEmpty() && legacyList.get(net.minecraft.component.DataComponentTypes.CUSTOM_DATA)
                .copyNbt().getList("Tags",8).size()==2,"legacy custom NBT lists are not mistaken for component syntax");
        var lookup = net.minecraft.registry.RegistryWrapper.WrapperLookup.of(
                net.minecraft.registry.Registries.REGISTRIES.stream().map(net.minecraft.registry.Registry::getReadOnlyWrapper));
        ItemStack componentPotion = ItemQuery.resolve(
                "minecraft:potion[minecraft:potion_contents={potion:\"minecraft:poison\"}]", lookup);
        check(ItemStack.areItemsAndComponentsEqual(poison, componentPotion),
                "native component query and legacy Potion query preserve the same potion");
        check(ItemQuery.resolve("minecraft:potion[minecraft:potion_contents={potion:\"minecraft:healing\"}] trailing",lookup).isEmpty(),
                "trailing component query text is rejected");
        check(ItemQuery.resolve("minecraft:apple[minecraft:unknown_component={}]",lookup).isEmpty(),
                "unknown data component is rejected");
        ItemStack carrot = new ItemStack(net.minecraft.item.Items.GOLDEN_CARROT);
        check(FoodInfo.describeIfFood(carrot,null).contains("14.4"),
                "1.21 saturation is already an absolute gain, not a multiplier");
        ItemStack apple = new ItemStack(net.minecraft.item.Items.APPLE);
        apple.set(net.minecraft.component.DataComponentTypes.FOOD,
                new net.minecraft.component.type.FoodComponent(7,13f,true,0.8f,Optional.empty(),List.of()));
        String customFood=FoodInfo.describeIfFood(apple,null);
        check(customFood.contains("+7") && customFood.contains("13.0") && customFood.contains("饱腹"),
                "food inspection reads the actual stack's modified component");
        check(FoodInfo.describeItem(net.minecraft.item.Items.APPLE,null).contains("+4"),
                "custom food on one stack does not change the registered item default");
        apple.remove(net.minecraft.component.DataComponentTypes.FOOD);
        check(FoodInfo.describeIfFood(apple,null).isEmpty(),"removed food component is respected");
        apple.set(net.minecraft.component.DataComponentTypes.FOOD,
                new net.minecraft.component.type.FoodComponent(0,5f,false,1.6f,Optional.empty(),List.of()));
        check(FoodInfo.describeIfFood(apple,null).contains("5.0"),
                "zero nutrition does not discard a component's saturation gain");
        var recipe = new net.minecraft.recipe.ShapedRecipe("",net.minecraft.recipe.book.CraftingRecipeCategory.MISC,
                net.minecraft.recipe.RawShapedRecipe.create(Map.of('P',net.minecraft.recipe.Ingredient.ofItems(net.minecraft.item.Items.OAK_PLANKS)),"PP","PP"),
                new ItemStack(net.minecraft.item.Items.CRAFTING_TABLE));
        var entry = new net.minecraft.recipe.RecipeEntry<>(net.minecraft.util.Identifier.of("ssc_compass","migration_recipe"),recipe);
        var manager = new net.minecraft.recipe.RecipeManager(lookup);
        manager.setRecipes(List.of(entry));
        var read = manager.values().iterator().next();
        check(read.id().equals(entry.id()) && read.value().getResult(lookup).isOf(net.minecraft.item.Items.CRAFTING_TABLE),
                "1.21 recipe entries retain recipe IDs and registry-aware outputs");
        check(read.value().getIngredients().size()==4,"recipe entry ingredients remain readable");
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
            com.mangzai.shapeshiftercompass.ShapeShifterCompass.registerTools();
            com.mangzai.shapeshiftercompass.knowledge.KnowledgeBase.load();
            for (String tool:List.of("get_inventory","get_equipment","get_crafting_grid","query_recipe","get_trinkets",
                    "get_form_status","query_knowledge","get_player_status","web_search","get_seed","locate_structure",
                    "run_command","set_player_data","list_mods","get_food_info","save_memory","get_spellbook_status",
                    "add_spellbook_experience","get_target_info","plan_crafting","get_evolution_advice"))
                check(ToolRegistry.get(tool)!=null,"shared client setup registers tool: "+tool);
            check(com.mangzai.shapeshiftercompass.knowledge.KnowledgeBase.size()>=2000,
                    "shared client setup loads packaged knowledge without optional mods");
            check(com.mangzai.shapeshiftercompass.knowledge.KnowledgeBase.environmentPrompt().contains("1.20.1")
                    && com.mangzai.shapeshiftercompass.knowledge.KnowledgeBase.environmentPrompt().contains("1.21.1"),
                    "runtime context states the Minecraft version boundary of historical knowledge");
            com.mangzai.shapeshiftercompass.ai.AiResponseRegressionTest.run();
            Class<?> handled=Class.forName("net.minecraft.client.gui.screen.ingame.HandledScreen",false,Probe.class.getClassLoader());
            check(com.mangzai.shapeshiftercompass.mixin.client.HandledScreenAccessor.class.isAssignableFrom(handled),"hovered slot accessor applies to actual Minecraft class");
            Class.forName("net.minecraft.client.gui.hud.ChatHud",false,Probe.class.getClassLoader());
            for (String screen:List.of("ChatScreen","CompassOverlayScreen","ConfigScreen","CompassEditorScreen","EvidenceScreen"))
                Class.forName("com.mangzai.shapeshiftercompass.ui."+screen,false,Probe.class.getClassLoader());
            check(true,"chat feedback mixin and all Compass screens load against Minecraft 1.21.1");
            var mod=net.fabricmc.loader.api.FabricLoader.getInstance().getModContainer("ssc_compass").orElseThrow();
            var metadata=com.google.gson.JsonParser.parseString(java.nio.file.Files.readString(mod.findPath("fabric.mod.json").orElseThrow())).getAsJsonObject();
            check(!metadata.getAsJsonObject("entrypoints").has("jei_mod_plugin"),"removed local viewer buttons do not leave a JEI entrypoint");
            System.out.println("Compass feature regression PASS: "+checks+" checks; exhaustive allocation oracle, evidence migration, real item/NBT bootstrap and accessor transformation. No game window/world or AI API started.");
        }
    }
}
