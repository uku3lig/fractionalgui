package net.uku3lig.fractionalgui;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.uku3lig.fractionalgui.config.GuiConfig;
import net.uku3lig.ukulib.config.ConfigManager;

public class FractionalGui implements ModInitializer {
    @Getter
    private static final ConfigManager<GuiConfig> manager = ConfigManager.create(GuiConfig.class, "fractionalgui");
    @Override
    public void onInitialize() {
        // hi :3
    }
}
