package com.limitd.harvest_helpers.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public class FertilizedCropModifier {
    @Inject(at = @At("HEAD"), method = "getGrowthAmount")
    private void growthAmount(World world, CallbackInfoReturnable<Integer> cir) {
        if (world.getBlockState())
    }
}
