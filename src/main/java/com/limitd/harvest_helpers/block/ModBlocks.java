package com.limitd.harvest_helpers.block;

import com.limitd.harvest_helpers.HarvestHelpers;
import com.limitd.harvest_helpers.block.custom.FertilizedFarmlandBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block FERTILIZED_FARMLAND = registerBlock("fertilized_farmland",
            new FertilizedFarmlandBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .ticksRandomly()
                            .strength(0.6F)
                            .sounds(BlockSoundGroup.GRAVEL)
                            .blockVision(Blocks::always)
                            .suffocates(Blocks::always)
            )
    );


    private static Block registerBlock(String name, Block block)
    {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(HarvestHelpers.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, Block block)
    {
        Registry.register(Registries.ITEM, Identifier.of(HarvestHelpers.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks()
    {
        HarvestHelpers.LOGGER.info("Registering Mod Blocks for " + HarvestHelpers.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
                entries.add((ModBlocks.FERTILIZED_FARMLAND));
        });
    }
}
