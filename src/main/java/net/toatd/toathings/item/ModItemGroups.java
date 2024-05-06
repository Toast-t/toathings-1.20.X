package net.toatd.toathings.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.toatd.toathings.Toathings;
import net.toatd.toathings.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup SERAPHIC_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Toathings.MOD_ID,"toathings"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.toathings"))
                    .icon(() -> new ItemStack(ModBlocks.OAK_LOG_HOLLOW)).entries((displayContext, entries) -> {

                        entries.add(ModItems.EXAMPLE_ITEM);

                        entries.add(ModItems.PORCELAIN);
                        entries.add(ModBlocks.PORCELAIN_TILES);

                        entries.add(ModBlocks.OAK_LOG_HOLLOW);
                        entries.add(ModBlocks.BIRCH_LOG_HOLLOW);

                        entries.add(ModBlocks.RESINOUS_POLYPORE);
                        entries.add(ModBlocks.PAMPAS_GRASS);

                        entries.add(ModBlocks.TOGGLE_LAMP);
                        entries.add(ModBlocks.TOGGLE_LAMP_RED);

                    }).build());
    public static void registerItemGroups(){
        Toathings.LOGGER.info("Seraphic item groups registering.");
    }
}
