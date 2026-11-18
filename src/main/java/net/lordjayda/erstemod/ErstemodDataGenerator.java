package net.lordjayda.erstemod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.lordjayda.erstemod.datagen.*;
import net.lordjayda.erstemod.datagen.villager.ModPOITags;
import net.lordjayda.erstemod.datagen.villager.ModTradeSets;
import net.lordjayda.erstemod.datagen.villager.ModVillagerTradeTags;
import net.lordjayda.erstemod.datagen.villager.ModVillagerTrades;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class ErstemodDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModBlocksLootTableProvider::new);
		pack.addProvider(ModAdvancementProvider::new);
		pack.addProvider(ModRegistryDataProvider::new);
		pack.addProvider(ModVillagerTradeTags::new);
		pack.addProvider(ModPOITags::new);
		pack.addProvider(ModItemTagsProvider::new);	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {

		registryBuilder.add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap);
		registryBuilder.add(Registries.TRADE_SET, ModTradeSets::bootstrap);
	}
}