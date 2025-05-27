/*
   * Implemented by Samit
   * This is the code for the Fertilizer Item. It uses a helper method, useOnFertilizable, to apply fertilizer to farmland blocks only.
   *
   * Boilerplate code based on: https://www.youtube.com/watch?v=bVho57E_1hU
   * Used this video to set up, but logical implementation is purely my own.
 */

package com.limitd.harvest_helpers.Item.custom;

import com.limitd.harvest_helpers.block.ModBlocks;
import com.limitd.harvest_helpers.block.custom.FertilizedFarmlandBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class FertilizerItem extends Item {
    public FertilizerItem(Settings settings) {
        super(settings);
    }

    /*
        * This method is called when the fertilizer item is used on a block.
        * It checks if the block is farmland and applies fertilizer if possible,
        * reporting success or failure to the server. Referenced implementation of
        * bone meal item in Minecraft for general structure of the method (usages
        * are very different though).
    */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        if (useOnFertilizable(context.getStack(), world, pos)) {
            if (!world.isClient) {
                context.getPlayer().emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS);
            }
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    /*
        * This method checks if the block at the given position where the fertilizer
        * is used is a farmland block. If it is, it applies the fertilizer by changing the
        * block state to FertilizedFarmlandBlock with maximum fertilizer count. If the block
        * is already fully fertilized it does nothing and returns false. If the block is
        * fertilized but not fully, it consumes a fertilizer item and updates the block state
        * to one with the maximum fertilizer count.
    */
    public static boolean useOnFertilizable(ItemStack stack, World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof FarmlandBlock) {
            if (blockState.getBlock() instanceof FertilizedFarmlandBlock && blockState.get(FertilizedFarmlandBlock.FERTILIZER_COUNT) == FertilizedFarmlandBlock.MAX_FERTILIZER_COUNT)
                return false;

            if (world instanceof ServerWorld) {
                world.setBlockState(pos, ModBlocks.FERTILIZED_FARMLAND.getDefaultState()
                        .with(FarmlandBlock.MOISTURE, blockState.get(FarmlandBlock.MOISTURE))
                        .with(FertilizedFarmlandBlock.FERTILIZER_COUNT, FertilizedFarmlandBlock.MAX_FERTILIZER_COUNT));
                stack.decrement(1);
            }
            return true;
        } else {
            return false;
        }
    }


}
