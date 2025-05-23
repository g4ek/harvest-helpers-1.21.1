package com.limitd.harvest_helpers.world;

import com.limitd.harvest_helpers.HarvestHelpers;
import com.limitd.harvest_helpers.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static com.limitd.harvest_helpers.world.ModConfiguredFeatures.registerKey;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> ORANGE_PLACED_KEY = registerKey("orange_placed");
    public static final RegistryKey<PlacedFeature> LEMON_PLACED_KEY = registerKey("lemon_placed");
    public static final RegistryKey<PlacedFeature> PEACH_PLACED_KEY = registerKey("peach_placed");


    public static void bootstrap(Registerable<PlacedFeature> context)
    {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, ORANGE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORANGE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(1, 0.1f, 0), ModBlocks.ORANGE_SAPLING_BLOCK));
        register(context, LEMON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEMON_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(1, 0.1f, 0), ModBlocks.LEMON_SAPLING_BLOCK));
        register(context, PEACH_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PEACH_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(1, 0.1f, 0), ModBlocks.PEACH_SAPLING_BLOCK));


    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(HarvestHelpers.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
