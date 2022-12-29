package net.uku3lig.fractionalgui.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.uku3lig.fractionalgui.FractionalGui;
import net.uku3lig.ukulib.config.screen.AbstractConfigScreen;

public class GuiConfigScreen extends AbstractConfigScreen<GuiConfig> {
    public GuiConfigScreen(Screen parent) {
        super(parent, Text.of("FractionalGui Config"), FractionalGui.getManager());
    }

    @Override
    protected SimpleOption<?>[] getOptions(GuiConfig config) {
        return new SimpleOption[]{
                new SimpleOption<>("Factor", SimpleOption.emptyTooltip(), GameOptions::getGenericValueText,
                        new SimpleOption.ValidatingIntSliderCallbacks(1, 10), config.getFactor(), config::setFactor)
        };
    }
}
