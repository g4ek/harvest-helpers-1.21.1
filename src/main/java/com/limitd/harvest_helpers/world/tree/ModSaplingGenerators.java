package com.limitd.harvest_helpers.world.tree;

/*

    Made by Jordan:

    Made various saplingGenerators which are available in Vanilla Minecraft.
    Example: public static final SaplingGenerator AZALEA = new SaplingGenerator("azalea", Optional.empty(), Optional.of(TreeConfiguredFeatures.AZALEA_TREE), Optional.empty()
    Adjusted them to use HarvestHelpers features. SaplingGenerators take in additional megaVariants or variants with bees, but those don't exist in this mod.
 */

import com.limitd.harvest_helpers.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator ORANGETREE = new SaplingGenerator("OrangeTree", Optional.empty(), Optional.of(ModConfiguredFeatures.ORANGE_KEY), Optional.empty());
    public static final SaplingGenerator LEMONTREE = new SaplingGenerator("LemonTree", Optional.empty(), Optional.of(ModConfiguredFeatures.LEMON_KEY), Optional.empty());
    public static final SaplingGenerator PEACHTREE = new SaplingGenerator("PeachTree", Optional.empty(), Optional.of(ModConfiguredFeatures.PEACH_KEY), Optional.empty());
}
