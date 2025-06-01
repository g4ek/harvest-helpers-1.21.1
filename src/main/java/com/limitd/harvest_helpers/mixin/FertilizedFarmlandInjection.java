/**
 * Mixin for the {@link net.minecraft.block.CropBlock} class to enable and enhance crop growth on Fertilized Farmland.
 * <p>
 * This mixin allows crops to be planted and grow on custom Fertilized Farmland blocks, providing an increased chance
 * of growth when the underlying block is fertilized. It injects logic into the crop planting and random tick methods
 * to support the custom fertilizer mechanics.
 * </p>
 *
 * <p>
 * Reference: <a href="https://wiki.fabricmc.net/tutorial:mixin_introduction">Fabric Wiki: Mixin Introduction</a>
 * </p>
 *
 * @author Samit
 */

package com.limitd.harvest_helpers.mixin;

import com.limitd.harvest_helpers.block.ModBlocks;
import com.limitd.harvest_helpers.block.custom.FertilizedFarmlandBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(CropBlock.class)
public class FertilizedFarmlandInjection {

    /**
          * Injects into the {@code canPlantOnTop} method of {@link CropBlock} to allow crops to be planted
          * on custom Fertilized Farmland blocks in addition to regular Farmland.
          * <p>
          * Sets the return value to {@code true} if the block below is Fertilized Farmland or regular Farmland,
          * enabling crop planting on these blocks.
          * </p>
          *
          * @param floor      the block state below the crop
          * @param world      the block view
          * @param pos        the position of the crop
          * @param returnVal  the callback to set the return value
          */
    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    private void canPlant(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> returnVal) {
        if (floor.isOf(ModBlocks.FERTILIZED_FARMLAND) || floor.isOf(Blocks.FARMLAND))
            returnVal.setReturnValue(true);
    }

    /**
         * Injects additional crop growth logic into the {@code randomTick} method of {@link CropBlock}.
         * <p>
         * When a crop receives a random tick, this method checks if the block below is a {@link FertilizedFarmlandBlock}.
         * If so, and the crop is not mature and the light level is sufficient, it increases the chance for the crop to grow.
         * If the crop grows, the fertilizer count on the farmland is decreased. When the fertilizer count reaches zero,
         * the block is converted back to regular farmland.
         * </p>
         *
         * @param state  the current block state of the crop
         * @param world  the server world
         * @param pos    the position of the crop block
         * @param random the random number generator
         * @param ci     the callback info
         */
    @Inject(method = "randomTick", at = @At("RETURN"))
    private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        BlockPos soilPos = pos.down();
        BlockState soilState = world.getBlockState(soilPos);
        CropBlock cropBlock = (CropBlock) state.getBlock();

        if (soilState.getBlock() instanceof FertilizedFarmlandBlock) {
            if (!cropBlock.isMature(state)) {
                if (world.getBaseLightLevel(pos, 0) >= 9) {
                    if (random.nextFloat() < FertilizedFarmlandBlock.FERTILIZED_CHANCE) {
                        cropBlock.grow(world, random, pos, state);

                        int fertilizerCount = soilState.get(FertilizedFarmlandBlock.FERTILIZER_COUNT);
                        int moisture = soilState.get(FertilizedFarmlandBlock.MOISTURE);

                        if (fertilizerCount <= 1) {
                            world.setBlockState(soilPos, Blocks.FARMLAND.getDefaultState().with(FertilizedFarmlandBlock.MOISTURE, moisture));
                        } else {
                            world.setBlockState(soilPos, soilState.with(FertilizedFarmlandBlock.FERTILIZER_COUNT, fertilizerCount - 1).with(FertilizedFarmlandBlock.MOISTURE, moisture));
                        }

                    }
                }
            }
        }
    }
}
