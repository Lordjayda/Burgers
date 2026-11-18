package net.lordjayda.erstemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.ItemUsedOnLocationTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricTagsProvider<Advancement> {
    private HolderLookup.RegistryLookup<?> items;
    private HolderLookup.RegistryLookup<?> blocks;
    public ModAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, Registries.ADVANCEMENT,  registryLookup);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        items = registries.lookupOrThrow(Registries.ITEM);
        blocks = registries.lookupOrThrow(Registries.BLOCK);
    }

 /*   public static class ErsteModAdvancements extends AdvancementSubProvider {

        protected ErsteModAdvancements(BootstrapContext<Advancement> output) {
            super(output);
        }

        @Override
        public void generate() {


           AdvancementHolder root = Advancement.Builder.advancement().rootDisplay(
                    ModBlocks.CUTTING_BOARD.asItem(),
                    Component.translatable( "advancement.erstemod.root.title"),
                    Component.translatable( "advancement.erstemod.root.description"),
                    Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                    AdvancementType.TASK, false, false, false
            )
                    .addCriterion("has_cutting_board", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModBlocks.CUTTING_BOARD)))
                    .save(output, Erstemod.MOD_ID + ":erstemod/root");

            AdvancementHolder plantSeed = Advancement.Builder.advancement()
                    .parent(root)
                    .rootDisplay(
                            ModItems.LETTUCE_SEED,
                            Component.translatable("advancements.tutorialmod.plant_custom.title"),
                            Component.translatable("advancements.tutorialmod.plant_custom.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("LETTUCE", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.lettuce_headcrop))
                    .addCriterion("lettuce", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.tomatocrop))
                    .save(output, Erstemod.MOD_ID + ":erstemod/plant_custom");

            AdvancementHolder harvestcutsomplant = Advancement.Builder.advancement()
                    .parent(plantSeed)
                    .display(
                            ModItems.LETTUCEHEAD,
                            Component.translatable("advancements.tutorialmod.harvestcutsomplant.title"),
                            Component.translatable("advancements.tutorialmod.harvestcutsomplant.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("LETTUCE", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PATTY))
                    .save(output, Erstemod.MOD_ID + ":erstemod/harvestcutsomplant");



            AdvancementHolder cutmeat = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.RAW_PATTY,
                            Component.translatable("advancements.tutorialmod.cutmeat.title"),
                            Component.translatable("advancements.tutorialmod.cutmeat.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("LETTUCE", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RAW_PATTY))
                    .save(output, Erstemod.MOD_ID + ":erstemod/cutmeat");

            AdvancementHolder cookmeat = Advancement.Builder.advancement()
                    .parent(cutmeat)
                    .display(
                            ModItems.PATTY,
                            Component.translatable("advancements.tutorialmod.cookmeat.title"),
                            Component.translatable("advancements.tutorialmod.cookmeat.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("LETTUCE", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PATTY))
                    .save(output, Erstemod.MOD_ID + ":erstemod/cookmeat");

        }

    }

  */
}

