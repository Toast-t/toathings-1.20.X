package net.toatd.toathings.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toatd.toathings.Toathings;

public class ModItems {

    public static final Item EXAMPLE_ITEM = registerItem("example_item", new Item(new FabricItemSettings()));
    public static final Item PORCELAIN = registerItem("porcelain", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Toathings.MOD_ID,name),item);
    }
    public static void registerModItems() {
        Toathings.LOGGER.info("Registering Mod Items for " + Toathings.MOD_ID);
    }
}
