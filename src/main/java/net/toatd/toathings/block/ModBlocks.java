package net.toatd.toathings.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.toatd.toathings.Toathings;
import net.toatd.toathings.block.custom.*;

import java.util.function.ToIntFunction;

public class ModBlocks {

    public static final Block PORCELAIN_TILES = registerBlock("porcelain_tiles",
            new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
    public static final Block THINGIMIBOB = registerBlock("thingimibob",
            new ThingimibobBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block OAK_LOG_HOLLOW = registerBlock("oak_log_hollow",
            new HollowLogBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block BIRCH_LOG_HOLLOW = registerBlock("birch_log_hollow",
            new HollowLogBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block RESINOUS_POLYPORE = registerBlock("resinous_polypore",
            new WallPlantBlock(FabricBlockSettings.copyOf(Blocks.VINE).nonOpaque().collidable(false).luminance(1).breakInstantly()));
    public static final Block PAMPAS_GRASS = registerBlock("pampas_grass",
            new TallFlowerBlock(FabricBlockSettings.copyOf(Blocks.GRASS).nonOpaque().breakInstantly()));
    public static final Block TOGGLE_LAMP = registerBlock("toggle_lamp",
            new ToggleableLampBlock(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN).luminance(ModBlocks.createLightLevelFromLitBlockState(15))));
    public static final Block TOGGLE_LAMP_RED = registerBlock("toggle_lamp_red",
            new ToggleableLampBlock(FabricBlockSettings.copyOf(Blocks.SEA_LANTERN).luminance(ModBlocks.createLightLevelFromLitBlockState(8))));
    public static final Block JUICER = registerBlock("juicer", new JuicerBlock(FabricBlockSettings.copyOf(Blocks.STONE)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(Toathings.MOD_ID,name),block);
    }
    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Toathings.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return state -> state.get(Properties.LIT) ? litLevel : 0;
    }
    public static void registerModBlocks(){
        Toathings.LOGGER.info("Registering Toathings Mod Blocks");
    }
}
