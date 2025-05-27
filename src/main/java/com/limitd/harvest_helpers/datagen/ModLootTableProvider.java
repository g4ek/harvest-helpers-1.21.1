package com.limitd.harvest_helpers.datagen;

import com.limitd.harvest_helpers.Item.ModItems;
import com.limitd.harvest_helpers.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

// tutorial: https://www.youtube.com/watch?v=ELHvhvuGF3U&t=985s
/*
    Used a tutorial to intialize datagen. Datagen automatically forms the json files required for all blocks/items and their specific properties,
    and specifically their textures/models. The tutorial was used to figure out how to structure the files and provided as boilerplate for datagen.

    Made by Jordan:

    ModLootTableProvider is a class used to help generate json files that determine what items/blocks are dropped when blocks are broken.

    Since HarvestHelpers naturally grows fruit trees throughout the world, if leaves are broken, the leaves should drop a random amount of its respective fruit.
    I made specific methods that have a similar structure to an available method (oakLeavesDrops), which instead of dropping apples will drop their fruit

 */

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    /*
    If the player punches the leaves with their fist (not using shears or special tools), it will roll 5 times for a chance to drop an orange.
    This usually results in 0-3 fruits being dropped by a block. If the tool has fortune, it will not drop fruit at all. These properties are applied to all 3 methods.
     */
    public LootTable.Builder orangeLeavesDrops(Block leaves, Block sapling, float... saplingChance) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT); // checks if this specific enchantment exists, if it doesn't, it returns null
        return this.leavesDrops(leaves, sapling, saplingChance).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(5.0F)).conditionally(this.createWithoutShearsOrSilkTouchCondition()).with(((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(ModItems.ORANGE))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), new float[]{0.15F, 0.00F, 0.0F, 0F, 0.0F}))));
    }

    public LootTable.Builder lemonLeavesDrops(Block leaves, Block sapling, float... saplingChance) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, sapling, saplingChance).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(5.0F)).conditionally(this.createWithoutShearsOrSilkTouchCondition()).with(((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(ModItems.LEMON))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), new float[]{0.15F, 0.00F, 0.0F, 0F, 0.0F}))));
    }
    public LootTable.Builder peachLeavesDrops(Block leaves, Block sapling, float... saplingChance) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, sapling, saplingChance).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(5.0F)).conditionally(this.createWithoutShearsOrSilkTouchCondition()).with(((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(ModItems.PEACH))).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), new float[]{0.15F, 0.00F, 0.0F, 0F, 0.0F}))));
    }

    @Override
    public void generate() // overrides FabricBlockLootTableProvider's method to change the drops for the blocks in the loot table to be their fruitLeavesDrops() method
    {
        addDrop(ModBlocks.ORANGE_SAPLING_BLOCK);
        addDrop(ModBlocks.ORANGE_LEAVES_BLOCK, orangeLeavesDrops(ModBlocks.ORANGE_LEAVES_BLOCK, ModBlocks.ORANGE_SAPLING_BLOCK, 0.f));
        addDrop(ModBlocks.LEMON_LEAVES_BLOCK, lemonLeavesDrops(ModBlocks.LEMON_LEAVES_BLOCK, ModBlocks.LEMON_SAPLING_BLOCK, 0.1f));
        addDrop(ModBlocks.PEACH_LEAVES_BLOCK, peachLeavesDrops(ModBlocks.PEACH_LEAVES_BLOCK, ModBlocks.PEACH_SAPLING_BLOCK, 0.1f));


    }

}
