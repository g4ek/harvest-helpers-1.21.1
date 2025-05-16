package com.limitd.harvest_helpers.util;

import com.limitd.harvest_helpers.HarvestHelpers;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;


public class ModTags {

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("tranformable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(HarvestHelpers.MOD_ID, name));
        }
    }
}
