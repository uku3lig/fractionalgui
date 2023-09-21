package net.uku3lig.fractionalgui.config;

import net.minecraft.client.gui.screen.Screen;
import net.uku3lig.fractionalgui.FractionalGui;
import net.uku3lig.ukulib.config.option.SliderOption;
import net.uku3lig.ukulib.config.option.WidgetCreator;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;

public class GuiConfigScreen extends AbstractConfigScreen<GuiConfig> {
    public GuiConfigScreen(Screen parent) {
        super("FractionalGui Config", parent, FractionalGui.getManager());
    }

    @Override
    protected WidgetCreator[] getWidgets(GuiConfig config) {
        return new WidgetCreator[] {
                new SliderOption("Factor", config.getFactor(), d -> config.setFactor((int) d),
                        SliderOption.INTEGER_VALUE_TO_TEXT, 1, 10, 1)
        };
    }
}
