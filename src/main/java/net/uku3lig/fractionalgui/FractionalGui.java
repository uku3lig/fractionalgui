package net.uku3lig.fractionalgui;

import lombok.Getter;
import net.minecraft.client.util.Window;
import net.uku3lig.fractionalgui.config.GuiConfig;
import net.uku3lig.ukulib.config.ConfigManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FractionalGui {
    @Getter
    private static final ConfigManager<GuiConfig> manager = ConfigManager.createDefault(GuiConfig.class, "fractionalgui");
    public static final DecimalFormat FORMAT = new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.ROOT));

    public static double calculateScaleFactor(Window window, int guiScale, boolean forceUnicodeFont) {
        final double factor = FractionalGui.getManager().getConfig().getFactor();
        double i = 1;

        while (
                i != guiScale
                        && i / factor < window.getFramebufferWidth()
                        && i / factor < window.getFramebufferHeight()
                        && window.getFramebufferWidth() / (i / factor + 1) >= 320
                        && window.getFramebufferHeight() / (i / factor + 1) >= 240
        ) {
            ++i;
        }

        if (forceUnicodeFont && i % 2 != 0) {
            ++i;
        }

        return i / factor;
    }

    public static int maxScaleFactor(Window window, boolean forceUnicodeFont) {
        return (int) (calculateScaleFactor(window, 0, forceUnicodeFont) * FractionalGui.getManager().getConfig().getFactor());
    }
}
