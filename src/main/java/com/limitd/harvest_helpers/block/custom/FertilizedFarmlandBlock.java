/**
 * Represents a fertilized variant of {@link FarmlandBlock} with an additional fertilizer count property.
 * <p>
 * The block can fertilize crop growth up to a maximum number of times, with a configurable chance per tick.
 * </p>
 *
 * <p>
 * <b>Fertilizer Effect:</b> The chance of fertilized farmland affecting crop growth is set to 35%.
 * The maximum number of fertilizations is {@value #MAX_FERTILIZER_COUNT}.
 * </p>
 *
 * <p>
 * For crop interaction logic, see {@code FertilizedFarmlandInjection.java} in the mixin package.
 * </p>
 *
 * <p>
 * <b>References:</b>
 * <ul>
 *   <li>Setup tutorial: <a href="https://www.youtube.com/watch?v=Ukb3nGFFW1U&t=">YouTube</a></li>
 * </ul>
 * </p>
 *
 * @author Samit
 * @see net.minecraft.block.FarmlandBlock
 * @see FertilizedFarmlandInjection.java
 */

package com.limitd.harvest_helpers.block.custom;

import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class FertilizedFarmlandBlock extends FarmlandBlock {
    public static final int MAX_FERTILIZER_COUNT = 15;
    public static final IntProperty FERTILIZER_COUNT = IntProperty.of("fertilizer_count", 0, MAX_FERTILIZER_COUNT);
    public static final float FERTILIZED_CHANCE = 0.35f;

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
