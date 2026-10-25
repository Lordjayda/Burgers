package net.lordjayda.erstemod.datagen.villager;

import net.lordjayda.erstemod.tags.ModTags;
import net.lordjayda.erstemod.Erstemod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.Optional;

public class ModTradeSets {
    public static final ResourceKey<TradeSet> FAST_FOOD_WORKER_1 = create("fast_food_worker/level_1");

    public static void bootstrap(BootstrapContext<TradeSet> context) {
        register(context, FAST_FOOD_WORKER_1, ModTags.Trades.FAST_FOOD_WORKER_1);
    }

    private static ResourceKey<TradeSet> create(final String id) {
        return ResourceKey.create(Registries.TRADE_SET, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, id));
    }

    public static Holder.Reference<TradeSet> register(final BootstrapContext<TradeSet> context,
                                                      final ResourceKey<TradeSet> resourceKey, final TagKey<VillagerTrade> tradeTag) {
        return register(context, resourceKey, tradeTag, ContextIntProviders.exactly(2));
    }

    public static Holder.Reference<TradeSet> register(final BootstrapContext<TradeSet> context, final ResourceKey<TradeSet> resourceKey,
                                                      final TagKey<VillagerTrade> tradeTag, final Holder<ContextIntProvider> amount) {
        return context.register(resourceKey, new TradeSet(context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tradeTag), amount,
                false, Optional.of(resourceKey.identifier().withPrefix("trade_set/"))));
    }
}