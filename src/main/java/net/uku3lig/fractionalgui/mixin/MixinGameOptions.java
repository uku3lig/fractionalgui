package net.uku3lig.fractionalgui.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.Window;
import net.uku3lig.fractionalgui.FractionalGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameOptions.class)
public class MixinGameOptions {
    @Redirect(method = "method_42479", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;calculateScaleFactor(IZ)I"))
    private static int hi(Window instance, int guiScale, boolean forceUnicodeFont) {
        return instance.calculateScaleFactor(guiScale, forceUnicodeFont) * FractionalGui.FACTOR;
    }

    private MixinGameOptions() {}
}