package net.toatd.toathings.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.toatd.toathings.Toathings;
import net.toatd.toathings.block.ModBlocks;

import java.util.List;
import java.util.random.RandomGenerator;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> PAMPAS_GRASS_KEY = registerKey("pampas_grass");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILDFLOWERS_KEY = registerKey("wildflowers");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_KEY = registerKey("fallen_birch");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, FALLEN_BIRCH_KEY, Feature.BLOCK_COLUMN, new BlockColumnFeatureConfig(List.of(BlockColumnFeatureConfig.
                        createLayer(BiasedToBottomIntProvider.create(3, 5), BlockStateProvider.of(ModBlocks.BIRCH_LOG_HOLLOW))),
                        Direction.NORTH, BlockPredicate.IS_AIR,false));

        //PLANT
        register(context, PAMPAS_GRASS_KEY, Feature.FLOWER, new RandomPatchFeatureConfig(20,6,2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.PAMPAS_GRASS)))));
        DataPool.Builder<BlockState> builder = DataPool.builder();
        for (int i = 1; i <= 4; ++i) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                builder.add((BlockState)((BlockState) ModBlocks.PINK_WILDFLOWERS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, i)).with(FlowerbedBlock.FACING, direction), 1);
                builder.add((BlockState)((BlockState) ModBlocks.PURPLE_WILDFLOWERS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, i)).with(FlowerbedBlock.FACING, direction), 1);
                builder.add((BlockState)((BlockState) ModBlocks.BLUE_WILDFLOWERS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, i)).with(FlowerbedBlock.FACING, direction), 1);
            }
        }
        ConfiguredFeatures.register(context, WILDFLOWERS_KEY, Feature.FLOWER,
                new RandomPatchFeatureConfig(70, 6, 2,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(builder)))));
    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Toathings.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
