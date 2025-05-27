package com.limitd.harvest_helpers.util;

import com.limitd.harvest_helpers.HarvestHelpers;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
// tutorial: https://www.youtube.com/watch?v=ELHvhvuGF3U&t=985s
/*
    Used a tutorial to intialize datagen. Datagen automatically forms the json files required for all blocks/items and their specific properties,
    and specifically their textures/models. The tutorial was used to figure out how to structure the files and provided as boilerplate for datagen.
*/

public class ModTags {

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("tranformable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(HarvestHelpers.MOD_ID, name));
        }
    }
}
