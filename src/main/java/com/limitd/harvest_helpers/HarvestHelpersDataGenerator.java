package com.limitd.harvest_helpers;

// tutorial: https://www.youtube.com/watch?v=ELHvhvuGF3U&t=985s
/*

    Used a tutorial to intialize datagen. Datagen automatically forms the json files required for all blocks/items and their specific properties,
    and specifically their textures/models. The tutorial was used to figure out how to structure the files and provided as boilerplate for datagen.

*/
import com.limitd.harvest_helpers.datagen.*;
import com.limitd.harvest_helpers.world.ModConfiguredFeatures;
import com.limitd.harvest_helpers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class HarvestHelpersDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {

		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}