package com.limitd.harvest_helpers;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

// referenced https://docs.fabricmc.net/develop/items/food to add the fruit (Jordan)
// nutrition() is a method that adjusts hunger points given for food
// saturation() adds how many saturation points the fruit will give

public class ModFoodComponents {
    public static final FoodComponent ORANGE = new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).build();
    public static final FoodComponent LEMON = new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).build();
    public static final FoodComponent PEACH = new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f).build();
}
