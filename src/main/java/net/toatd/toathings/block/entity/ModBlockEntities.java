package net.toatd.toathings.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.toatd.toathings.Toathings;
import net.toatd.toathings.block.ModBlocks;

public class ModBlockEntities {

    public static final BlockEntityType<JuicerBlockEntity> JUICER_BLOCK_ENTITY_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Toathings.MOD_ID, "juicer_block_entity"),
                    FabricBlockEntityTypeBuilder.create(JuicerBlockEntity::new,
                            ModBlocks.JUICER).build(null));

    public static void registerBlockEntities() {
        Toathings.LOGGER.info("Registering Toathings Block Entities");
    }
}
