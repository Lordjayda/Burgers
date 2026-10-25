package net.lordjayda.erstemod.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.datagen.villager.ModTradeSets;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;

import java.util.Set;

public class ModVillagers {
    public static final ResourceKey<PoiType> LORDJAYDA_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, "lordjayda_poi"));
    public static final PoiType LORDJAYDA_POI = PoiTypes.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, LORDJAYDA_POI_KEY, Set.of(ModBlocks.CUTTING_BOARD.defaultBlockState()), 1, 5);

    public static final VillagerProfession FAST_FOOD_WORKER = registerVillagerProfession("fast_food_worker", "FAST_FOOD_WORKER", LORDJAYDA_POI_KEY,
            SoundEvents.BAMBOO_WOOD_HIT, Int2ObjectMap.ofEntries(
                    Int2ObjectMap.entry(1, ModTradeSets.FAST_FOOD_WORKER_1)
            ));

    private static VillagerProfession registerVillagerProfession(String id, String title, ResourceKey<PoiType> poi,
                                                                 SoundEvent sound, Int2ObjectMap<ResourceKey<TradeSet>> map) {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, id), new VillagerProfession(
                Component.translatable("villager_profession.erstemod." + id), holder -> holder.is(poi), holder -> holder.is(poi),
                ImmutableSet.of(), ImmutableSet.of(), sound, map));
    }


    public static void register(){
        Erstemod.LOGGER.info("Villiager gefunden für" + Erstemod.MOD_ID);
    }
}
