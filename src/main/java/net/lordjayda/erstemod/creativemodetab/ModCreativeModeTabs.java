package net.lordjayda.erstemod.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.lordjayda.erstemod.Erstemod;
import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab burger_tab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Erstemod.MOD_ID, "burgers"),
            FabricCreativeModeTab.builder().icon(()-> new ItemStack(ModItems.BURGER))
                            .title(Component.translatable("creativemodetab.erstemod.burgers"))
                            .displayItems((parameters, output) ->{
                                output.accept(ModItems.BURGER);
                                output.accept(ModItems.TOMATO);
                                output.accept(ModItems.TOMATO_SLICE);
                                output.accept(ModItems.TOMATO_SEED);
                                output.accept(ModItems.LETTUCEHEAD);
                                output.accept(ModItems.LETTUCE);
                                output.accept(ModItems.LETTUCE_SEED);
                                output.accept(ModItems.BUN);
                                output.accept(ModItems.TOP_BUN);
                                output.accept(ModItems.BOTTOM_BUN);
                                output.accept(ModItems.PATTY);
                                output.accept(ModItems.RAW_PATTY);
                                output.accept(ModBlocks.CUTTING_BOARD);
                                output.accept(ModBlocks.ASSEMBLER);
                                output.accept(ModItems.FRIES);
                            }).build());
//hier neuen output für etwas im creative tab
    public static void registerModCreativeModeTabs(){
        Erstemod.LOGGER.info("Tabs registriert für" + Erstemod.MOD_ID);
    }
}