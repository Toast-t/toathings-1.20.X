package net.toatd.toathings.datagen.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.toatd.toathings.Toathings;
import net.toatd.toathings.recipe.JuicingRecipe;
import org.jetbrains.annotations.Nullable;
import com.google.gson.JsonArray;

import java.util.function.Consumer;

public class JuicingRecipeBuilder implements CraftingRecipeJsonBuilder {
    private final Item result;
    private final Ingredient food_ingredient;
    private final Ingredient material_ingredient;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.create();

    public JuicingRecipeBuilder(ItemConvertible food_ingredient, ItemConvertible material_ingredient, ItemConvertible result, int count) {
        this.food_ingredient = Ingredient.ofItems(food_ingredient);
        this.material_ingredient = Ingredient.ofItems(material_ingredient);
        this.result = result.asItem();
        this.count = count;
    }

    @Override
    public CraftingRecipeJsonBuilder criterion(String name, CriterionConditions conditions) {
        this.advancement.criterion(name, conditions);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return result;
    }

    @Override
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        this.advancement.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId));

        exporter.accept(new JsonBuilder(recipeId, this.result, this.count, this.food_ingredient, this.material_ingredient,
                this.advancement, new Identifier(recipeId.getNamespace(), "recipes/"
                + recipeId.getPath())));
    }

    public static class JsonBuilder implements RecipeJsonProvider {
        private final Identifier id;
        private final Item result;
        private final Ingredient food_ingredient;
        private final Ingredient material_ingredient;
        private final int count;
        private final Advancement.Builder advancement;
        private final Identifier advancementId;

        public JsonBuilder(Identifier id, Item result, int count, Ingredient food_ingredient, Ingredient material_ingredient,
                           Advancement.Builder advancement, Identifier advancementId) {
            this.id = id;
            this.result = result;
            this.food_ingredient = food_ingredient;
            this.material_ingredient = material_ingredient;
            this.count = count;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serialize(JsonObject json) {
            JsonArray jsonarray = new JsonArray();
            jsonarray.add(food_ingredient.toJson());
            jsonarray.add(material_ingredient.toJson());

            json.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registries.ITEM.getId(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            json.add("output", jsonobject);
        }

        @Override
        public Identifier getRecipeId() {
            return new Identifier(Toathings.MOD_ID,
                    Registries.ITEM.getId(this.result).getPath() + "_from_juicing");
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return JuicingRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject toAdvancementJson() {
            return this.advancement.toJson();
        }

        @Nullable
        @Override
        public Identifier getAdvancementId() {
            return this.advancementId;
        }
    }
}
