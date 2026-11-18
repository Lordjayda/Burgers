package net.lordjayda.burbers.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.lordjayda.burbers.block.ModBlocks;
import net.lordjayda.burbers.block.custom.LettuceCropBlock;
import net.lordjayda.burbers.item.ModItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;

import java.util.concurrent.CompletableFuture;

import static net.lordjayda.burbers.block.ModBlocks.lettuce_headcrop;
import static net.lordjayda.burbers.block.ModBlocks.tomatocrop;

public class ModBlocksLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlocksLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.CUTTING_BOARD);
        dropSelf(ModBlocks.ASSEMBLER);
        LootItemCondition.Builder isLettuceMaxAge = MatchBlock.blockMatches(
                this.blocks, lettuce_headcrop, StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, LettuceCropBlock.MAX_AGE)
        );

        LootItemCondition.Builder isTomatoMaxAge = MatchBlock.blockMatches(
                this.blocks, tomatocrop, StatePropertiesPredicate.Builder.properties().hasProperty(LettuceCropBlock.AGE, LettuceCropBlock.MAX_AGE)
        );
        this.add(lettuce_headcrop, this.createCropDrops(lettuce_headcrop, ModItems.LETTUCEHEAD, ModItems.LETTUCE_SEED, isLettuceMaxAge));

        this.add(tomatocrop, this.createCropDrops(tomatocrop, ModItems.TOMATO, ModItems.TOMATO_SEED, isTomatoMaxAge));

    }
}
