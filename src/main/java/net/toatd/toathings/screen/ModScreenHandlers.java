package net.toatd.toathings.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.toatd.toathings.Toathings;

public class ModScreenHandlers {
    public static final ScreenHandlerType<JuicerScreenHandler> JUICER_SCREEN_HANDLER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Toathings.MOD_ID,"juicer_screen_handler"),
                    new ExtendedScreenHandlerType<>(JuicerScreenHandler::new));

    public static void registerScreenHandler() {
        Toathings.LOGGER.info("Registering Toathings Screen Handlers");
    }
}
