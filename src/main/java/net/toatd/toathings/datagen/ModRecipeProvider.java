package net.toatd.toathings.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.toatd.toathings.block.ModBlocks;
import net.toatd.toathings.item.ModItems;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offer2x2CompactingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,ModBlocks.PORCELAIN_TILES, ModItems.PORCELAIN);

        offerBlasting(exporter, List.of(Items.CLAY_BALL), RecipeCategory.MISC, ModItems.PORCELAIN, 0.0f,200,"porcelain");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE,ModBlocks.TOGGLE_LAMP_RED,1)
                .input(Items.RED_DYE)
                .input(ModBlocks.TOGGLE_LAMP)
                .criterion(hasItem(Items.GLOWSTONE_DUST),conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TOGGLE_LAMP_RED)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE,ModBlocks.TOGGLE_LAMP,1)
                .pattern("PGP")
                .pattern("GRG")
                .pattern("PGP")
                .input('G',Items.GLOWSTONE_DUST)
                .input('R', Items.REDSTONE)
                .input('P', ItemTags.PLANKS)
                .criterion(hasItem(Items.GLOWSTONE_DUST),conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TOGGLE_LAMP)));
    }
}
