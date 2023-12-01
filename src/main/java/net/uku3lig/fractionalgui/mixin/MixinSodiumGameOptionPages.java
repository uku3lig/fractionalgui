package net.uku3lig.fractionalgui.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.uku3lig.fractionalgui.FractionalGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public class MixinSodiumGameOptionPages {
    @ModifyArg(method = "lambda$general$9", index = 2, at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/control/SliderControl;<init>(Lme/jellysquid/mods/sodium/client/gui/options/Option;IIILme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;)V"))
    private static int changeMaxScale(int min) {
        return FractionalGui.maxScaleFactor(MinecraftClient.getInstance().getWindow(), MinecraftClient.getInstance().forcesUnicodeFont());
    }

    @Redirect(method = "lambda$general$9", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;guiScale()Lme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;"), require = 0)
    private static ControlValueFormatter changeFormatter() {
        return v -> {
            String formatted = FractionalGui.FORMAT.format(((double) v) / FractionalGui.getManager().getConfig().getFactor());
            return v == 0 ? Text.translatable("options.guiScale.auto") : Text.of(formatted + "x");
        };
    }

    private MixinSodiumGameOptionPages() {}
}
