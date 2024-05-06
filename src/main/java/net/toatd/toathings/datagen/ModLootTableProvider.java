package net.toatd.toathings.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.toatd.toathings.block.ModBlocks;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    public static LootTable.Builder shearDrop(Block block) {
        return LootTable.builder().pool(LootPool.builder().conditionally(WITH_SHEARS).with((LootPoolEntry.Builder<?>)((Object)ItemEntry.builder(block).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f))))));
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PORCELAIN_TILES);
        addDrop(ModBlocks.OAK_LOG_HOLLOW);
        addDrop(ModBlocks.BIRCH_LOG_HOLLOW);
        addDrop(ModBlocks.PAMPAS_GRASS, doorDrops(ModBlocks.PAMPAS_GRASS));
        addDrop(ModBlocks.TOGGLE_LAMP);
        addDrop(ModBlocks.TOGGLE_LAMP_RED);
        addDrop(ModBlocks.RESINOUS_POLYPORE, shearDrop(ModBlocks.RESINOUS_POLYPORE));
    }
}
