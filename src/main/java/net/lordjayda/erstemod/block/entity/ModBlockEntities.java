package net.lordjayda.erstemod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.block.entity.custom.CuttingBoardBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static void registerBlockEntities(){
        Erstemod.LOGGER.info("Registriere Block enities für"+ Erstemod.MOD_ID);
    }

    public static final BlockEntityType<CuttingBoardBlockEntity> cuttingboard_be=
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, "cuttingboard_be"),
                    FabricBlockEntityTypeBuilder.create(CuttingBoardBlockEntity::new, ModBlocks.CUTTING_BOARD) .build());
    public static final BlockEntityType<CuttingBoardBlockEntity> assembler_be=
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, "assembler_be"),
                    FabricBlockEntityTypeBuilder.create(CuttingBoardBlockEntity::new, ModBlocks.ASSEMBLER) .build());

}
