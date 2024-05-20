package net.toatd.toathings;

import net.fabricmc.api.ModInitializer;

import net.toatd.toathings.block.ModBlocks;
import net.toatd.toathings.block.entity.ModBlockEntities;
import net.toatd.toathings.recipe.ModRecipes;
import net.toatd.toathings.item.ModItemGroups;
import net.toatd.toathings.item.ModItems;
import net.toatd.toathings.screen.ModScreenHandlers;
import net.toatd.toathings.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Toathings implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "toathings";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandler();
		ModRecipes.registerModRecipes();
		ModWorldGeneration.generateModWorldGeneration();
	}
}