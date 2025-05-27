package com.limitd.harvest_helpers.datagen;

// tutorial: https://www.youtube.com/watch?v=ELHvhvuGF3U&t=985s
/*
    Used a tutorial to intialize datagen. Datagen automatically forms the json files required for all blocks/items and their specific properties,
    and specifically their textures/models. The tutorial was used to figure out how to structure the files and provided as boilerplate for datagen.

    Made by Jordan:
    Made Fruit Leaves able to be set on fire
 */

import com.limitd.harvest_helpers.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;


public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add(ModBlocks.ORANGE_LEAVES_BLOCK).add(ModBlocks.LEMON_LEAVES_BLOCK).add(ModBlocks.PEACH_LEAVES_BLOCK);
    }
}
