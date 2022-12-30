package net.uku3lig.fractionalgui.mixin;

import net.minecraft.client.option.Option;
import net.minecraft.client.util.Window;
import net.uku3lig.fractionalgui.FractionalGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Option.class)
public class MixinOption {
    @Redirect(method = "method_32559", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;calculateScaleFactor(IZ)I"))
    private static int changeScaleFactor(Window instance, int guiScale, boolean forceUnicodeFont) {
        return instance.calculateScaleFactor(guiScale, forceUnicodeFont) * FractionalGui.getManager().getConfig().getFactor();
    }

    @Redirect(method = "method_42522", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;toString(I)Ljava/lang/String;"))
    private static String showFractionalValue(int value) {
        return FractionalGui.FORMAT.format(((double) value) / FractionalGui.getManager().getConfig().getFactor());
    }

    private MixinOption() {}
}