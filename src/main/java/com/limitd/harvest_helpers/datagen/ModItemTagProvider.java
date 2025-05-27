package com.limitd.harvest_helpers.datagen;

// tutorial: https://www.youtube.com/watch?v=ELHvhvuGF3U&t=985s
/*
    Used a tutorial to intialize datagen. Datagen automatically forms the json files required for all blocks/items and their specific properties,
    and specifically their textures/models. The tutorial was used to figure out how to structure the files and provided as boilerplate for datagen.

    Made by Jordan:
    Made fruits transformable items for future use, potentially used in potions or to be smelt, but gives the fruits the properties to change states.
 */


import com.limitd.harvest_helpers.Item.ModItems;
import com.limitd.harvest_helpers.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
        }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.ORANGE)
                .add(ModItems.LEMON)
                .add(ModItems.PEACH);
    }
}
