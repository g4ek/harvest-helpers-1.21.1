package com.limitd.harvest_helpers;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent ORANGE = new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f)
            .build();
    public static final FoodComponent LEMON = new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f)
            .build();
    public static final FoodComponent PEACH = new FoodComponent.Builder().nutrition(3).saturationModifier(0.5f)
            .build();
}
