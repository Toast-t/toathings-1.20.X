package net.toatd.toathings.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.toatd.toathings.world.ModPlacedFeatures;

public class ModFallenTreeGeneration {
    public static void generateFallenTrees() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST),
                GenerationStep.Feature.SURFACE_STRUCTURES, ModPlacedFeatures.FALLEN_BIRCH_PLACED_KEY);
    }
}

