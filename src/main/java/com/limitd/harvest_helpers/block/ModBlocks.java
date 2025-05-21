package com.limitd.harvest_helpers.block;

import com.limitd.harvest_helpers.HarvestHelpers;
import com.limitd.harvest_helpers.world.tree.ModSaplingGenerators;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block ORANGE_SAPLING_BLOCK = registerBlock("orange_sapling_block",
    new SaplingBlock(ModSaplingGenerators.ORANGETREE,AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block LEMON_SAPLING_BLOCK = registerBlock("lemon_sapling_block",
            new SaplingBlock(ModSaplingGenerators.LEMONTREE,AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
    public static final Block PEACH_SAPLING_BLOCK = registerBlock("peach_sapling_block",
            new SaplingBlock(ModSaplingGenerators.PEACHTREE,AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

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

    public static void registerModBlocks()
    {
        HarvestHelpers.LOGGER.info("Registering Mod Blocks for " + HarvestHelpers.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.ORANGE_SAPLING_BLOCK);
            entries.add(ModBlocks.LEMON_SAPLING_BLOCK);
            entries.add(ModBlocks.PEACH_SAPLING_BLOCK);

            entries.add(ModBlocks.ORANGE_LEAVES_BLOCK);
            entries.add(ModBlocks.LEMON_LEAVES_BLOCK);
            entries.add(ModBlocks.PEACH_LEAVES_BLOCK);
                 });
    }
}
