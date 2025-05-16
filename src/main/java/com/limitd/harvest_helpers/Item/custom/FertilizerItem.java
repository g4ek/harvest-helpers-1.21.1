package com.limitd.harvest_helpers.Item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class FertilizerItem extends Item {
    public FertilizerItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockPos pos2 = pos.offset(context.getSide());
        if (useOnFertilizable(context.getStack(), world, pos)) {
            if (!world.isClient) {
                context.getPlayer().emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
            }
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    // method for using fertilizer on crop
    public static boolean useOnFertilizable(ItemStack stack, World world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof Fertilizable fertilizable && fertilizable.isFertilizable(world, pos, blockState)) {
            if (world instanceof ServerWorld) {
                if (fertilizable.canGrow(world, world.random, pos, blockState)) {
                    fertilizable.grow((ServerWorld)world, world.random, pos, blockState);
                }

                stack.decrement(1);
            }

            return true;
        } else {
            return false;
        }
    }


}
