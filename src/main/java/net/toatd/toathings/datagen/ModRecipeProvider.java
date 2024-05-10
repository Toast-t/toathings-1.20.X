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
import net.toatd.toathings.datagen.recipe.JuicingRecipeBuilder;
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
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD,ModItems.PUMPKIN_SLICE,4)
                .input(Items.PUMPKIN)
                .criterion(hasItem(Items.PUMPKIN),conditionsFromItem(Items.PUMPKIN))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PUMPKIN_SLICE)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD,Items.MELON_SLICE,4)
                .input(Items.MELON)
                .criterion(hasItem(Items.MELON),conditionsFromItem(Items.MELON))
                .offerTo(exporter, new Identifier(getRecipeName(Items.MELON_SLICE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE,ModBlocks.TOGGLE_LAMP,1)
                .pattern("PGP")
                .pattern("GRG")
                .pattern("PGP")
                .input('G',Items.GLOWSTONE_DUST)
                .input('R', Items.REDSTONE)
                .input('P', ItemTags.PLANKS)
                .criterion(hasItem(Items.GLOWSTONE_DUST),conditionsFromItem(Items.GLOWSTONE_DUST))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TOGGLE_LAMP)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,ModBlocks.JUICER,1)
                .pattern("PVP")
                .pattern("PSP")
                .pattern("PRP")
                .input('R',Items.REDSTONE)
                .input('V', Items.PISTON)
                .input('P', ModItems.PORCELAIN)
                .input('S', Items.IRON_BARS)
                .criterion(hasItem(ModItems.PORCELAIN),conditionsFromItem(ModItems.PORCELAIN))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.JUICER)));

        new JuicingRecipeBuilder(Items.APPLE, Items.HONEYCOMB, ModItems.APPLE_JUICE,1)
                .offerTo(exporter);

        new JuicingRecipeBuilder(Items.CARROT, Items.SUGAR, ModItems.CARROT_JUICE,1)
                .offerTo(exporter);

        new JuicingRecipeBuilder(ModItems.PUMPKIN_SLICE, Items.WHEAT, ModItems.PUMPKIN_JUICE,1)
                .offerTo(exporter);

        new JuicingRecipeBuilder(Items.MELON_SLICE, Items.NETHER_WART, ModItems.MELON_JUICE,1)
                .offerTo(exporter);

        new JuicingRecipeBuilder(Items.KELP, Items.SCUTE, ModItems.KELP_JUICE,1)
                .offerTo(exporter);

        new JuicingRecipeBuilder(Items.GOLDEN_APPLE, Items.EXPERIENCE_BOTTLE, ModItems.GOLDEN_JUICE,1)
                .offerTo(exporter);
    }
}
