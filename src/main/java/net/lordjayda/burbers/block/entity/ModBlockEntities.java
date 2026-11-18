package net.lordjayda.burbers.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.lordjayda.burbers.Burbers;
import net.lordjayda.burbers.block.ModBlocks;
import net.lordjayda.burbers.block.entity.custom.CuttingBoardBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static void registerBlockEntities(){
        Burbers.LOGGER.info("Registriere Block enities für"+ Burbers.MOD_ID);
    }

    public static final BlockEntityType<CuttingBoardBlockEntity> cuttingboard_be=
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Burbers.MOD_ID, "cuttingboard_be"),
                    FabricBlockEntityTypeBuilder.create(CuttingBoardBlockEntity::new, ModBlocks.CUTTING_BOARD) .build());
    public static final BlockEntityType<CuttingBoardBlockEntity> assembler_be=
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(Burbers.MOD_ID, "assembler_be"),
                    FabricBlockEntityTypeBuilder.create(CuttingBoardBlockEntity::new, ModBlocks.ASSEMBLER) .build());

}
