package net.lordjayda.erstemod.block;

import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.custom.Assembler;
import net.lordjayda.erstemod.block.custom.LettuceCropBlock;
import net.lordjayda.erstemod.block.custom.CuttingBoard;
import net.lordjayda.erstemod.block.custom.TomatoCropBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {



    public static final Block CUTTING_BOARD =registerBlock("cutting_board",
            properties -> new CuttingBoard(properties.strength(0.2f)
                    .sound(SoundType.BAMBOO_WOOD)));

    public static final Block ASSEMBLER =registerBlock("assembler",
            properties -> new Assembler(properties.strength(0.2f)
                    .sound(SoundType.BAMBOO_WOOD)));

    public static final Block lettuce_headcrop =registerBlock("lettuce_headcrop",
            properties -> new LettuceCropBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.POPPED)));
    public static final Block tomatocrop =registerBlock("tomatocrop",
            properties -> new TomatoCropBlock(properties.noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.POPPED)));
// das kopieren für neuen block

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function){
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name))));
        registerBlockItems(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name),toRegister);
    }

    //block mit blockitem registrierer

    private static Block registerBlockwithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function){
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name))));
        registerBlockItems(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name),toRegister);
    }
    //block ohne blockitem

    private static void registerBlockItems(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, name)))));
    }

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }


    public static void registerModBlocks (){
        Erstemod.LOGGER.info("Registering Mod Blöcke für" + Erstemod.MOD_ID);
    }
}
