//package com.limitd.harvest_helpers.potion;
//
//import com.limitd.harvest_helpers.HarvestHelpers;
//import net.fabricmc.fabric.api.networking.v1.S2CPlayChannelEvents;
//import net.minecraft.entity.effect.StatusEffect;
//import net.minecraft.entity.effect.StatusEffectInstance;
//import net.minecraft.entity.effect.StatusEffects;
//import net.minecraft.potion.Potion;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.registry.entry.RegistryEntry;
//import net.minecraft.util.Identifier;
//
//public class ModPotions {
//    public static final RegistryEntry<Potion> ORANGE_JUICE = registerPotion("orange_juice",
//            new Potion(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 120)));
//    public static final RegistryEntry<Potion> LEMON_JUICE = registerPotion("orange_juice",
//            new Potion(new StatusEffectInstance(StatusEffects.SPEED, 120)));
//    public static final RegistryEntry<Potion> LEMONADE = registerPotion("orange_juice",
//            new Potion(new StatusEffectInstance(StatusEffects.HASTE, 120)));
//    public static final RegistryEntry<Potion> PEACH_JUICE = registerPotion("orange_juice",
//            new Potion(new StatusEffectInstance(StatusEffects.STRENGTH, 120)));
//
//    private static RegistryEntry<Potion> registerPotion(String name, Potion potion)
//    {
//        return Registry.registerReference(Registries.POTION, Identifier.of(HarvestHelpers.MOD_ID, name), potion);
//    }
//
//    public static void registerPotions()
//    {
//        HarvestHelpers.LOGGER.info("Registering Mod Potions for " + HarvestHelpers.MOD_ID);
//    }
//}
