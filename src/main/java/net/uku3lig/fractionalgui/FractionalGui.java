package net.uku3lig.fractionalgui;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.uku3lig.fractionalgui.config.GuiConfig;
import net.uku3lig.ukulib.config.ConfigManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FractionalGui implements ModInitializer {
    @Getter
    private static final ConfigManager<GuiConfig> manager = ConfigManager.create(GuiConfig.class, "fractionalgui");
    public static final DecimalFormat FORMAT = new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.ROOT));

    @Override
    public void onInitialize() {
        // hi :3
    }
}
