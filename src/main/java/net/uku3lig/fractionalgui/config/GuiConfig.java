package net.uku3lig.fractionalgui.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.uku3lig.ukulib.config.IConfig;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuiConfig implements IConfig<GuiConfig> {
    private int factor;

    @Override
    public GuiConfig defaultConfig() {
        return new GuiConfig(5);
    }
}
