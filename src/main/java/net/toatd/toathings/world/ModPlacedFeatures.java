package net.toatd.toathings.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.toatd.toathings.Toathings;

import java.util.List;

public class ModPlacedFeatures {
public static final RegistryKey<PlacedFeature> PAMPAS_GRASS_PLACED_KEY = registerKey("pampas_grass_placed");
public static final RegistryKey<PlacedFeature> WILDFLOWERS_KEY = registerKey("wilflowers_placed");
public static final RegistryKey<PlacedFeature> FALLEN_BIRCH_PLACED_KEY = registerKey("fallen_birch_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, PAMPAS_GRASS_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PAMPAS_GRASS_KEY),
                RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        register(context, WILDFLOWERS_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WILDFLOWERS_KEY),
                RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                CountPlacementModifier.of(ClampedIntProvider.create(UniformIntProvider.create(1, 3), 1, 2)), BiomePlacementModifier.of());
        register(context, FALLEN_BIRCH_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.FALLEN_BIRCH_KEY),
                RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    }


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Toathings.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
