package com.limitd.harvest_helpers.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

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
