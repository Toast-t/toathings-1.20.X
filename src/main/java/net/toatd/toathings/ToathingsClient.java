package net.toatd.toathings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.toatd.toathings.block.ModBlocks;
import net.toatd.toathings.screen.JuicerScreen;
import net.toatd.toathings.screen.ModScreenHandlers;

public class ToathingsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PAMPAS_GRASS, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.JUICER_SCREEN_HANDLER_SCREEN_HANDLER, JuicerScreen::new);
    }
}
