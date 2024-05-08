package net.toatd.toathings.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toatd.toathings.Toathings;

public class ModRecipes {
    public static void registerModRecipes() {
        Toathings.LOGGER.info("Registering Mod Recipes for " + Toathings.MOD_ID);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Toathings.MOD_ID,
                JuicingRecipe.Serializer.ID),JuicingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Toathings.MOD_ID,
                JuicingRecipe.Type.ID),JuicingRecipe.Type.INSTANCE);
    }
}
