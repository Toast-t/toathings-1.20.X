package net.toatd.toathings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.toatd.toathings.block.ModBlocks;
import net.toatd.toathings.screen.JuicerScreen;
import net.toatd.toathings.screen.ModScreenHandlers;

public class ToathingsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PAMPAS_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_WILDFLOWERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_WILDFLOWERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_WILDFLOWERS, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.JUICER_SCREEN_HANDLER_SCREEN_HANDLER, JuicerScreen::new);

        ColorProviderRegistry.BLOCK.register((state,world,pos,tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos)
                : FoliageColors.getDefaultColor(), ModBlocks.PURPLE_WILDFLOWERS);
        ColorProviderRegistry.BLOCK.register((state,world,pos,tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos)
                : FoliageColors.getDefaultColor(), ModBlocks.PINK_WILDFLOWERS);
        ColorProviderRegistry.BLOCK.register((state,world,pos,tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos)
                : FoliageColors.getDefaultColor(), ModBlocks.BLUE_WILDFLOWERS);
    }
}
