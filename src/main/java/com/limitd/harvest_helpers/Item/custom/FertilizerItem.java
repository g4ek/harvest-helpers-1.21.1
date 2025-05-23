package com.limitd.harvest_helpers.Item.custom;

import com.limitd.harvest_helpers.block.ModBlocks;
import com.limitd.harvest_helpers.block.custom.FertilizedFarmlandBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.Fertilizable;
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

    // method for using fertilizer on crop
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
