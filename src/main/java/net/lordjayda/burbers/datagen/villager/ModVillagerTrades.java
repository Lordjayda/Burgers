package net.lordjayda.burbers.datagen.villager;


import net.lordjayda.burbers.Burbers;
import net.lordjayda.burbers.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;

public class ModVillagerTrades {

    public static final ResourceKey<VillagerTrade> FAST_FOOD_WOKER_1_tomato_seed = createKey("fast_food_worker/1/emerald_tomato_seed");
    public static final ResourceKey<VillagerTrade> FAST_FOOD_WOKER_1_lettuce_seed = createKey("fast_food_worker/1/emerald_lettuce_seed");


    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        context.register(FAST_FOOD_WOKER_1_lettuce_seed, VillagerTrade.builder(new TradeCost(Items.EMERALD, 4), new ItemStackTemplate(ModItems.LETTUCE_SEED, 12),4, 1, 1).build());
        context.register(FAST_FOOD_WOKER_1_tomato_seed, VillagerTrade.builder(new TradeCost(Items.EMERALD, 4), new ItemStackTemplate(ModItems.TOMATO_SEED, 12),4, 1, 1).build());            ;
    }


    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(Burbers.MOD_ID, name));
    }
}
