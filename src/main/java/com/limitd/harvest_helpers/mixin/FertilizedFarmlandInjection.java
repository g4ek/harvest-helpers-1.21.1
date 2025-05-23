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
    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    private void canPlant(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (floor.isOf(ModBlocks.FERTILIZED_FARMLAND) || floor.isOf(Blocks.FARMLAND))
            cir.setReturnValue(true);
    }

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
