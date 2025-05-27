/*
    * Implemented by Samit
    * This is a mixin for the CropBlock class that allows crops to grow on Fertilized Farmland
    * with an increased chance of growth when the block is fertilized. A mixin is essentially
    * a way to modify the behavior of existing classes in Minecraft without directly changing the
    * classes themselves by injecting code into specific methods at runtime.
    *
    * Used fabric wiki for reference and learning how to use mixins: https://wiki.fabricmc.net/tutorial:mixin_introduction
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

// declares this class as a mixin of CropBlock.class
@Mixin(CropBlock.class)
public class FertilizedFarmlandInjection {

    /*
        * This method is injected into the canPlantOnTop method of CropBlock.
        * It modifies the return value of the method to allow crops to be planted,
        * so the cancellable parameter is set to true to indicate that the method's
        * return value can be changed. I changed the return value to true using the
        * CallbackInfoReturnable parameter.
        * It checks if the block below is Fertilized Farmland or regular Farmland,
        * and if so, allows crops to be planted on it.
        * This is necessary for crops to grow on Fertilized Farmland.
     */
    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    private void canPlant(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> returnVal) {
        if (floor.isOf(ModBlocks.FERTILIZED_FARMLAND) || floor.isOf(Blocks.FARMLAND))
            returnVal.setReturnValue(true);
    }

    /*
        * This method is injected at the end of the randomTick method of CropBlock,
        * which is called every time the block receives a random tick update.
        * It modifies the behavior of crop growth when the block is fertilized.
        * It checks if the block below is Fertilized Farmland, and if so, allows crops
        * to grow with an increased chance when the light level is sufficient.
        * If the crop grows, it decreases the fertilizer count on the Fertilized Farmland block.
        * If the fertilizer count reaches 0, it changes the block back to regular Farmland.
        *
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
