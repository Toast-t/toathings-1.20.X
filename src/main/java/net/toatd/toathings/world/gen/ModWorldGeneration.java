package net.toatd.toathings.world.gen;

public class ModWorldGeneration {
    public static void generateModWorldGeneration() {
        ModFlowerGeneration.generateFlowers();
        ModTreeGeneration.generateTrees();
        ModFallenTreeGeneration.generateFallenTrees();
    }
}
