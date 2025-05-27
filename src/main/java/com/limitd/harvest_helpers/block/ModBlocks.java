package com.limitd.harvest_helpers.block;

/*
    Used tutorial as starting point: https://www.youtube.com/watch?v=9JpBpka5oBs

    ModBlocks initializes the blocks used in the mod. Blocks are able to be given specific properties or use similar
    settings compared to other vanilla blocks.

 */

import com.limitd.harvest_helpers.HarvestHelpers;
import com.limitd.harvest_helpers.block.custom.FertilizedFarmlandBlock;

import com.limitd.harvest_helpers.block.custom.SpecialSaplingBlock;
import com.limitd.harvest_helpers.world.tree.ModSaplingGenerators;
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

    // registers fertilized farmland with the same properties as farmland, but with a new fertilizer count property
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

    /*
    Fruit saplings are established with modified randomTick() to increase growth time, uses its modified configuredFeatures,
    and copies settings from oak_saplings.
     */
    public static final Block ORANGE_SAPLING_BLOCK = registerBlock("orange_sapling_block", 
            new SpecialSaplingBlock(ModSaplingGenerators.ORANGETREE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block LEMON_SAPLING_BLOCK = registerBlock("lemon_sapling_block", 
            new SpecialSaplingBlock(ModSaplingGenerators.LEMONTREE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block PEACH_SAPLING_BLOCK = registerBlock("peach_sapling_block", 
            new SpecialSaplingBlock(ModSaplingGenerators.PEACHTREE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    public static final Block ORANGE_LEAVES_BLOCK = registerBlock("orange_leaves_block",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

    public static final Block LEMON_LEAVES_BLOCK = registerBlock("lemon_leaves_block",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

    public static final Block PEACH_LEAVES_BLOCK = registerBlock("peach_leaves_block",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

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

    // This registers all blocks under the 'Building Blocks' category, which can be found in a creative menu
    public static void registerModBlocks()
    {
        HarvestHelpers.LOGGER.info("Registering Mod Blocks for " + HarvestHelpers.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            // adds fertilized farmland to the building blocks category after farmland block
            entries.addAfter(Blocks.FARMLAND, ModBlocks.FERTILIZED_FARMLAND);
          
            entries.add(ModBlocks.ORANGE_SAPLING_BLOCK);
            entries.add(ModBlocks.LEMON_SAPLING_BLOCK);
            entries.add(ModBlocks.PEACH_SAPLING_BLOCK);

            entries.add(ModBlocks.ORANGE_LEAVES_BLOCK);
            entries.add(ModBlocks.LEMON_LEAVES_BLOCK);
            entries.add(ModBlocks.PEACH_LEAVES_BLOCK);
            
         });
    }
}
