package com.limitd.harvest_helpers.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FarmlandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public class FarmlandFertilizedGrowthModifier {
    @Unique
    private int fertilizerCount;


    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(AbstractBlock.Settings settings, CallbackInfo ci) {
        this.fertilizerCount = 0;
    }

    @Unique
    public int getFertilizerCount() {
        return fertilizerCount;
    }

    @Unique
    public void fertilizedGrowth() {
        fertilizerCount--;
    }

}