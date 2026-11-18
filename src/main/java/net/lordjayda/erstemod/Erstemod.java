package net.lordjayda.erstemod;

import net.fabricmc.api.ModInitializer;

import net.lordjayda.erstemod.block.ModBlocks;
import net.lordjayda.erstemod.block.entity.ModBlockEntities;
import net.lordjayda.erstemod.crafting.ModRecipeType;
import net.lordjayda.erstemod.creativemodetab.ModCreativeModeTabs;
import net.lordjayda.erstemod.menu.ModMenuTypes;
import net.lordjayda.erstemod.villager.ModVillagers;
import net.lordjayda.erstemod.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Erstemod implements ModInitializer {
	public static final String MOD_ID = "erstemod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModMenuTypes.registerModMenuTypes();
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModVillagers.register();
		ModRecipeType.registerRecipes();
    }
}
