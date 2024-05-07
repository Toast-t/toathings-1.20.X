package net.toatd.toathings.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;
import net.toatd.toathings.block.ModBlocks;
import net.toatd.toathings.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void registerPampasGrass(BlockStateModelGenerator ModelGen) {
        ModelGen.registerItemModel(ModBlocks.PAMPAS_GRASS, "_cross");
        Identifier identifier = ModelIds.getBlockSubModelId(ModBlocks.PAMPAS_GRASS, "_up");
        Identifier identifier2 = ModelGen.createSubModel(ModBlocks.PAMPAS_GRASS, "_bottom",
                BlockStateModelGenerator.TintType.NOT_TINTED.getCrossModel(), TextureMap::cross);
        ModelGen.registerDoubleBlock(ModBlocks.PAMPAS_GRASS, identifier, identifier2);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PORCELAIN_TILES);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TOGGLE_LAMP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TOGGLE_LAMP_RED);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.THINGIMIBOB);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.OAK_LOG_HOLLOW);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BIRCH_LOG_HOLLOW);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.RESINOUS_POLYPORE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.JUICER);

        registerPampasGrass(blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.EXAMPLE_ITEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.PORCELAIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_JUICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARROT_JUICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_JUICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MELON_JUICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KELP_JUICE, Models.GENERATED);
    }
}
