package net.toatd.toathings.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toatd.toathings.Toathings;
import net.toatd.toathings.item.custom.JuiceItem;

public class ModItems {

    public static final Item EXAMPLE_ITEM = registerItem("example_item", new Item(new FabricItemSettings()));
    public static final Item PORCELAIN = registerItem("porcelain", new Item(new FabricItemSettings()));
    public static final Item PUMPKIN_SLICE = registerItem("pumpkin_slice", new Item(new FabricItemSettings()
            .food(FoodComponents.MELON_SLICE)));


    public static final Item APPLE_JUICE = registerItem("apple_juice", new JuiceItem(new FabricItemSettings()
            .recipeRemainder(Items.GLASS_BOTTLE).food(ModFoodComponents.APPLE_JUICE).maxCount(16)));
    public static final Item CARROT_JUICE = registerItem("carrot_juice", new JuiceItem(new FabricItemSettings()
            .recipeRemainder(Items.GLASS_BOTTLE).food(ModFoodComponents.CARROT_JUICE).maxCount(16)));
    public static final Item PUMPKIN_JUICE = registerItem("pumpkin_juice", new JuiceItem(new FabricItemSettings()
            .recipeRemainder(Items.GLASS_BOTTLE).food(ModFoodComponents.PUMPKIN_JUICE).maxCount(16)));
    public static final Item MELON_JUICE = registerItem("melon_juice", new JuiceItem(new FabricItemSettings()
            .recipeRemainder(Items.GLASS_BOTTLE).food(ModFoodComponents.MELON_JUICE).maxCount(16)));
    public static final Item KELP_JUICE = registerItem("kelp_juice", new JuiceItem(new FabricItemSettings()
            .recipeRemainder(Items.GLASS_BOTTLE).food(ModFoodComponents.KELP_JUICE).maxCount(16)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Toathings.MOD_ID,name),item);
    }
    public static void registerModItems() {
        Toathings.LOGGER.info("Registering Mod Items for " + Toathings.MOD_ID);
    }
}
