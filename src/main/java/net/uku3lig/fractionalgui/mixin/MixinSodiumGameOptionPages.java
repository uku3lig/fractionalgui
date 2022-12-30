package net.uku3lig.fractionalgui.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import net.uku3lig.fractionalgui.FractionalGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public class MixinSodiumGameOptionPages {
    @ModifyConstant(method = "lambda$general$9", constant = @Constant(intValue = 4), require = 0)
    private static int changeScaleFactor(int constant) {
        return MinecraftClient.getInstance().getWindow().calculateScaleFactor(0, MinecraftClient.getInstance().forcesUnicodeFont())
                * FractionalGui.getManager().getConfig().getFactor();
    }

    @Redirect(method = "lambda$general$9", at = @At(value = "INVOKE", target = "Lme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;guiScale()Lme/jellysquid/mods/sodium/client/gui/options/control/ControlValueFormatter;"), require = 0)
    private static ControlValueFormatter changeFormatter() {
        return v -> {
            String formatted = FractionalGui.FORMAT.format(((double) v) / FractionalGui.getManager().getConfig().getFactor());
            return v == 0 ? new TranslatableText("options.guiScale.auto").getString() : formatted + "x";
        };
    }

    private MixinSodiumGameOptionPages() {}
}
