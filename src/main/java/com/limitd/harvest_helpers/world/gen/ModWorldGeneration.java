package com.limitd.harvest_helpers.world.gen;

/*
    SHOULD NOT BE GRADED (ModTreeGeneration, ModWorldGeneration, ModPlacedFeatures)

    Written by Jordan:
    While this was a stretch goal, I was unable to figure out biome generation myself, however I felt like it made sense to add it in the context of the mod,
    so I used a tutorial to implement this class. https://www.youtube.com/watch?v=ykHweIW67mk
    These 3 classes allow trees to be generated in 3 specific biomes, Forests, Sunflower Plains, and Flower Forests

 */

public class ModWorldGeneration {

    public static void generateModWorldGen()
    {
        ModTreeGeneration.generateTrees();
    }
}
