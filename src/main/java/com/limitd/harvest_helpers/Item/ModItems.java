package com.limitd.harvest_helpers.Item;

import com.limitd.harvest_helpers.HarvestHelpers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class ModItems
{
        public static final Item ORANGE_SAPLING = registerItem("orange_sapling", new Item(new Item.Settings()));

        private static Item registerItem(String name, Item item)
        {
                return Registry.register(Registries.ITEM, Identifier.of(HarvestHelpers.MOD_ID, name), item);
        }
        public static void registerModItems()
        {
                HarvestHelpers.LOGGER.info("registering Mod items for " + HarvestHelpers.MOD_ID);

                ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
                        entries.add(ORANGE_SAPLING);
                });
        }
}
