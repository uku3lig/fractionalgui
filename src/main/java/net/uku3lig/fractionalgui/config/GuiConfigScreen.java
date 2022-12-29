package net.uku3lig.fractionalgui.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.Option;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.uku3lig.fractionalgui.FractionalGui;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;

public class GuiConfigScreen extends AbstractConfigScreen<GuiConfig> {
    public GuiConfigScreen(Screen parent) {
        super(parent, Text.of("FractionalGui Config"), FractionalGui.getManager());
    }

    @Override
    protected Option[] getOptions(GuiConfig config) {
        return new Option[]{
                new DoubleOption("Factor", 1, 10, 1, opt -> (double) config.getFactor(),
                        (opt, value) -> config.setFactor(value.intValue()),
                        (opt, option) -> new TranslatableText("options.generic_value", "Factor", config.getFactor()))
        };
    }
}
