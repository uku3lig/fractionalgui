package net.uku3lig.fractionalgui.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import net.minecraft.client.util.Window;
import net.minecraft.text.Text;
import net.uku3lig.fractionalgui.FractionalGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public class MixinSodiumGameOptionPages {
    @Redirect(method = "lambda$general$9", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;calculateScaleFactor(IZ)I"), require = 0)
    private static int changeScaleFactor(Window instance, int guiScale, boolean forceUnicodeFont) {
        return instance.calculateScaleFactor(guiScale, forceUnicodeFont) * FractionalGui.getManager().getConfig().getFactor();
    }

    @Redirect(method = "lambda$general$9", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;guiScale()Lme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;"), require = 0)
    private static ControlValueFormatter changeFormatter() {
        return v -> {
            String formatted = FractionalGui.FORMAT.format(((double) v) / FractionalGui.getManager().getConfig().getFactor());
            return v == 0 ? Text.translatable("options.guiScale.auto").getString() : formatted + "x";
        };
    }

    private MixinSodiumGameOptionPages() {}
}
