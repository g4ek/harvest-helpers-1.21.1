package com.limitd.harvest_helpers.block.custom;

import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class FertilizedFarmlandBlock extends FarmlandBlock {
    public static final int MAX_FERTILIZER_COUNT = 7;
    public static final IntProperty FERTILIZER_COUNT = IntProperty.of("fertilizer_count", 0, MAX_FERTILIZER_COUNT);
    public static final float FERTILIZED_CHANCE = 0.50f;

    // initialize the fertilizer count property
    public FertilizedFarmlandBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(MOISTURE, 0).with(FERTILIZER_COUNT, MAX_FERTILIZER_COUNT));
    }

    // adds the fertilizer count property to the block state
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FERTILIZER_COUNT);
    }

}
