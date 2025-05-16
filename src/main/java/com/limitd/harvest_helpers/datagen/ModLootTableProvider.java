package com.limitd.harvest_helpers.datagen;

import com.limitd.harvest_helpers.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate()
    {
        addDrop(ModBlocks.ORANGE_LEAVES_BLOCK);
        addDrop(ModBlocks.ORANGE_SAPLING_BLOCK);
        addDrop(ModBlocks.ORANGE_LEAVES_BLOCK, leavesDrops(ModBlocks.ORANGE_LEAVES_BLOCK, ModBlocks.ORANGE_SAPLING_BLOCK, 0.0625f));
    }
}
