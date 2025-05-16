package com.limitd.harvest_helpers;

import com.limitd.harvest_helpers.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class HarvestHelpersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient()
    {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_SAPLING_BLOCK, RenderLayer.getCutout());

    }
}
