package com.limitd.harvest_helpers.Item;

import com.limitd.harvest_helpers.HarvestHelpers;
import com.limitd.harvest_helpers.Item.custom.FertilizerItem;
import com.limitd.harvest_helpers.ModFoodComponents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class ModItems
{
        // registers the fertilizer item that is used to fertilize farmland for crops
        public static final Item FERTILIZER = registerItem("fertilizer", new FertilizerItem(new Item.Settings()));

        // gives the fruits the settings established by ModFoodComponents
        public static final Item ORANGE = registerItem("orange", new Item(new Item.Settings().food(ModFoodComponents.ORANGE)));
        public static final Item LEMON = registerItem("lemon", new Item(new Item.Settings().food(ModFoodComponents.LEMON)));
        public static final Item PEACH = registerItem("peach", new Item(new Item.Settings().food(ModFoodComponents.PEACH)));

        private static Item registerItem(String name, Item item)
        {
            return Registry.register(Registries.ITEM, Identifier.of(HarvestHelpers.MOD_ID, name), item);
        }

        public static void registerModItems()
        {
            HarvestHelpers.LOGGER.info("Registering Mod Items for " + HarvestHelpers.MOD_ID);

            // sets the category for Fertilizer as 'Tools', placing it after bone_meal in creative menus
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
                entries.addAfter(Items.BONE_MEAL, FERTILIZER);
            });

            // Sets the category for fruits under 'Food and Drink'
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
                entries.add(ORANGE);
                entries.add(LEMON);
                entries.add(PEACH);
            });

        }
}
