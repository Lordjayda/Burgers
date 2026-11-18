package net.lordjayda.erstemod.datagen.villager;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.lordjayda.erstemod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class ModVillagerTradeTags extends FabricTagsProvider<VillagerTrade> {
    public ModVillagerTradeTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
    }
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(ModTags.Trades.FAST_FOOD_WORKER_1)
                .add(TagEntry.element(ModVillagerTrades.FAST_FOOD_WOKER_1_lettuce_seed.identifier()))
                .add(TagEntry.element(ModVillagerTrades.FAST_FOOD_WOKER_1_tomato_seed.identifier()));
    }
}
