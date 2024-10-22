package net.uku3lig.fractionalgui.mixin;

import net.caffeinemc.mods.sodium.client.gui.SodiumGameOptionPages;
import net.caffeinemc.mods.sodium.client.gui.options.control.ControlValueFormatter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.uku3lig.fractionalgui.FractionalGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public class MixinSodiumGameOptionPages {
    @ModifyArg(method = "lambda$general$9", index = 2, at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/control/SliderControl;<init>(Lnet/caffeinemc/mods/sodium/client/gui/options/Option;IIILnet/caffeinemc/mods/sodium/client/gui/options/control/ControlValueFormatter;)V"))
    private static int changeMaxScale(int min) {
        return FractionalGui.maxScaleFactor(MinecraftClient.getInstance().getWindow(), MinecraftClient.getInstance().forcesUnicodeFont());
    }

    @Redirect(method = "lambda$general$9", at = @At(value = "INVOKE", target = "Lnet/caffeinemc/mods/sodium/client/gui/options/control/ControlValueFormatter;guiScale()Lnet/caffeinemc/mods/sodium/client/gui/options/control/ControlValueFormatter;"), require = 0)
    private static ControlValueFormatter changeFormatter() {
        return v -> {
            String formatted = FractionalGui.FORMAT.format(((double) v) / FractionalGui.getManager().getConfig().getFactor());
            return v == 0 ? Text.translatable("options.guiScale.auto") : Text.of(formatted + "x");
        };
    }

    private MixinSodiumGameOptionPages() {}
}
