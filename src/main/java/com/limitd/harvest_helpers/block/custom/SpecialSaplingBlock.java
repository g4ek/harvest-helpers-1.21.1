package com.limitd.harvest_helpers.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

/*
    Made by Jordan:

    SpecialSaplingBlock class is used for the new fruit trees to be implemented that require new methods to differentiate them from Minecraft's Vanilla Trees

    SpecialSaplingBlock is the same constructor used as SaplingBlock, which takes in a specific generator that generates a unique structure for the tree, and settings

    randomTick is a method that is overridden from SaplingBlock. randomTick is used to determine when a tree should grow.
    Minecraft trees attempt to grow at random intervals, so on average, if a tree has adequate sunlight, it should grow in 30 mins (1.5 in game days)
    at a bounds of random.nextInt(7). With the overridden method using random.nextInt(15), the tree should grow in about 63 mins (3.15 in game days) on average.

 */

public class SpecialSaplingBlock extends SaplingBlock {

    public SpecialSaplingBlock(SaplingGenerator generator, AbstractBlock.Settings settings)
    {
        super(generator, settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9 && random.nextInt(15) == 0) {
            this.generate(world, pos, state, random);
        }
    }
}
