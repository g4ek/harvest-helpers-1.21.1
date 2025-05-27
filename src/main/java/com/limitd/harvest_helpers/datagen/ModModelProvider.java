package com.limitd.harvest_helpers.datagen;

// tutorial: https://www.youtube.com/watch?v=ELHvhvuGF3U&t=985s
/*
    Used a tutorial to intialize datagen. Datagen automatically forms the json files required for all blocks/items and their specific properties,
    and specifically their textures/models. The tutorial was used to figure out how to structure the files and provided as boilerplate for datagen.

    Made by Jordan:
    Set the types of block states for each texture.
    Leaves are registered as 'Singletons' which means that their textures are the same on ALL 6 sides of the block
    Saplings are set as crossBlockStates, which give them the specific 'X' shaped textures and the specific sapling shape

    For generateItemModels, blocks have a specific method that turns the model to be compatible as an item (.asItem())
    Fruits are just single images, and can be generated normally through the itemModelGenerator.
 */

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
