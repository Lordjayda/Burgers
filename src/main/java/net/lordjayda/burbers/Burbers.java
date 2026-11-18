package net.lordjayda.burbers;

import net.fabricmc.api.ModInitializer;

import net.lordjayda.burbers.block.ModBlocks;
import net.lordjayda.burbers.block.entity.ModBlockEntities;
import net.lordjayda.burbers.crafting.ModRecipeType;
import net.lordjayda.burbers.creativemodetab.ModCreativeModeTabs;
import net.lordjayda.burbers.menu.ModMenuTypes;
import net.lordjayda.burbers.villager.ModVillagers;
import net.lordjayda.burbers.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Burbers implements ModInitializer {
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
