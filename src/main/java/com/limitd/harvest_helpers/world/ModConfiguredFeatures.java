package com.limitd.harvest_helpers.world;

import com.limitd.harvest_helpers.HarvestHelpers;
import com.limitd.harvest_helpers.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

/*
    Initial setup from tutorial: https://youtu.be/Sp0uWsWa2Rg?list=PLKGarocXCE1H_HxOYihQMq0mlpqiUJj4L, which initialized bootstrap, registerKey, and register

    Made by Jordan:

    ModConfiguredFeatures is a class that sets up how certain structures are generated. For trees specifically, they have 3 different methods
    StraightTrunkPlacer is a method that establishes how tall tree trunks are. Using blockStateProvider you can set what block trunks are made of. RandomHeights are set to 0, since we want to only generate 6 block tall trunks
    BlobFoliagePlacer is a method that takes in radius, offset from the trunk of the tree, and the height of the foliage.
    TwoLayersFeaturesSize allows trees to grow with its tapered structure (thicker foliage at bottom, thinner at top)

 */

public class ModConfiguredFeatures {


    public static final RegistryKey<ConfiguredFeature<?, ?>> ORANGE_KEY = registerKey("orange_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LEMON_KEY = registerKey("lemon_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PEACH_KEY = registerKey("peach_tree");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        register(context, ORANGE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG), new StraightTrunkPlacer(6, 0, 0), // baseHeight, 1stRandomH, 2ndRandomH
                BlockStateProvider.of(ModBlocks.ORANGE_LEAVES_BLOCK), new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, LEMON_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG), new StraightTrunkPlacer(6, 0, 0), // baseHeight, 1stRandomH, 2ndRandomH
                BlockStateProvider.of(ModBlocks.LEMON_LEAVES_BLOCK), new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PEACH_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG), new StraightTrunkPlacer(6, 0, 0), // baseHeight, 1stRandomH, 2ndRandomH
                BlockStateProvider.of(ModBlocks.PEACH_LEAVES_BLOCK), new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(HarvestHelpers.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
