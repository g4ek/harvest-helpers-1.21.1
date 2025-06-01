/**
 * Fertilizer item for applying fertilizer to farmland blocks.
 * <p>
 * Uses the {@link #useOnFertilizable(ItemStack, World, BlockPos)} helper method to apply fertilizer only to farmland blocks.
 * </p>
 *
 * <p>
 * <b>Implementation:</b> Boilerplate setup based on: <a href="https://www.youtube.com/watch?v=bVho57E_1hU">YouTube</a>.
 * Logical implementation is original.
 * </p>
 *
 * @author Samit
 * @see FertilizedFarmlandBlock
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

    /**
     * Called when the fertilizer item is used on a block.
     * <p>
     * Checks if the targeted block is farmland and attempts to apply fertilizer.
     * Reports success or failure to the server. The structure is based on the
     * bone meal item implementation, but the usage logic is different.
     * </p>
     *
     * @param context the usage context containing world, position, and player information
     * @return the result of the action, indicating success or pass
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

    /**
         * Attempts to apply fertilizer to the block at the given position.
         * <p>
         * If the block is a farmland block, it will be converted to a fertilized farmland block
         * with the maximum fertilizer count. If the block is already fully fertilized, no action is taken.
         * If the block is fertilized but not full, it will be refilled to maximum and one fertilizer item is consumed.
         * </p>
         *
         * @param stack the item stack of fertilizer being used
         * @param world the world in which the action is performed
         * @param pos the position of the block to fertilize
         * @return true if fertilizer was applied, false otherwise
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
