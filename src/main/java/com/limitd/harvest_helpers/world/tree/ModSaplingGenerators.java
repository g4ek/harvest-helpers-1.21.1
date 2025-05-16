package com.limitd.harvest_helpers.world.tree;

import com.limitd.harvest_helpers.HarvestHelpers;
import com.limitd.harvest_helpers.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator ORANGETREE = new SaplingGenerator(HarvestHelpers.MOD_ID + ":Orange",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ORANGE_KEY), Optional.empty());
}
