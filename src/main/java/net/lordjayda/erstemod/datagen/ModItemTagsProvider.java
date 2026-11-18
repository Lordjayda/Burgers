package net.lordjayda.erstemod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.lordjayda.erstemod.item.ModItems;
import net.lordjayda.erstemod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
       /* tag(ModTags.Items.ASSEMBLER_INGREDIENTS)
                .add(ModItems.getRK(ModItems.LETTUCE))
                .add(ModItems.getRK(ModItems.PATTY))
                .add(ModItems.getRK(ModItems.TOMATO_SLICE));
        tag(ModTags.Items.TOP_BUN)
                .add(ModItems.getRK(ModItems.TOP_BUN));
        tag(ModTags.Items.BOTTOM_BUN)
                .add(ModItems.getRK(ModItems.BOTTOM_BUN));
        tag(ModTags.Items.SAUCES);
        */
    }

}