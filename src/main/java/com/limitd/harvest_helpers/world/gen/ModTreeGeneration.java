package com.limitd.harvest_helpers.world.gen;

/*
    SHOULD NOT BE GRADED (ModTreeGeneration, ModWorldGeneration, ModPlacedFeatures)

    Written by Jordan:
    While this was a stretch goal, I was unable to figure out biome generation myself, however I felt like it made sense to add it in the context of the mod,
    so I used a tutorial to implement this class. https://www.youtube.com/watch?v=ykHweIW67mk
    These 3 classes allow trees to be generated in 3 specific biomes, Forests, Sunflower Plains, and Flower Forests

 */

import com.limitd.harvest_helpers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModTreeGeneration {
    public static void generateTrees()
    {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ORANGE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SUNFLOWER_PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LEMON_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PEACH_PLACED_KEY);
    }
}
