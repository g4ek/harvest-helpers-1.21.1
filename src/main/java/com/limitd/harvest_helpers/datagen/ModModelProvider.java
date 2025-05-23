package com.limitd.harvest_helpers.datagen;

import com.limitd.harvest_helpers.Item.ModItems;
import com.limitd.harvest_helpers.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        blockStateModelGenerator.registerSingleton(ModBlocks.ORANGE_LEAVES_BLOCK, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.ORANGE_SAPLING_BLOCK, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSingleton(ModBlocks.LEMON_LEAVES_BLOCK, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.LEMON_SAPLING_BLOCK, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSingleton(ModBlocks.PEACH_LEAVES_BLOCK, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.PEACH_SAPLING_BLOCK, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.register(ModBlocks.ORANGE_SAPLING_BLOCK.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModItems.ORANGE, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.LEMON_SAPLING_BLOCK.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModItems.LEMON, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.PEACH_SAPLING_BLOCK.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH, Models.GENERATED);
    }
}
