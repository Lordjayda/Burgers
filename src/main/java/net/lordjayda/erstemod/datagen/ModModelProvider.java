package net.lordjayda.erstemod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.block.custom.LettuceCropBlock;
import net.lordjayda.erstemod.block.custom.TomatoCropBlock;
import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        /*
        blockModelGenerators.createTrivialCube(ModBlocks.ASSEMBLER);
        blockModelGenerators.createTrivialCube(ModBlocks.CUTTING_BOARD);
        blockModelGenerators.createCropBlock(ModBlocks.lettuce_headcrop, LettuceCropBlock.AGE,0, 1);
        blockModelGenerators.createCropBlock(ModBlocks.tomatocrop, TomatoCropBlock.AGE, 0,1 );
*/

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        /*
        itemModelGenerators.generateFlatItem(ModItems.BURGER, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LETTUCE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LETTUCEHEAD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TOMATO, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.PATTY, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.RAW_PATTY, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BUN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TOP_BUN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.BOTTOM_BUN, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TOMATO_SLICE, ModelTemplates.FLAT_ITEM);
        */

    }
}
