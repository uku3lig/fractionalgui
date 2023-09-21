package net.uku3lig.fractionalgui;

import lombok.Getter;
import net.uku3lig.fractionalgui.config.GuiConfig;
import net.uku3lig.ukulib.config.ConfigManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FractionalGui {
    @Getter
    private static final ConfigManager<GuiConfig> manager = ConfigManager.createDefault(GuiConfig.class, "fractionalgui");
    public static final DecimalFormat FORMAT = new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.ROOT));

    private FractionalGui() {}
}
