package net.uku3lig.fractionalgui.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.Window;
import net.uku3lig.fractionalgui.FractionalGui;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {
    @Final
    @Shadow private Window window;
    @Shadow
    public abstract boolean forcesUnicodeFont();
    @Final
    @Shadow
    public GameOptions options;

    @ModifyArg(method = "onResolutionChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setScaleFactor(D)V"))
    public double changeScaleFactor(double original) {
        return calculateScaleFactor(options.guiScale, forcesUnicodeFont());
    }

    public double calculateScaleFactor(int guiScale, boolean forceUnicodeFont) {
        final int factor = FractionalGui.getManager().getConfig().getFactor();
        double i = 1;

        while(
                i != guiScale
                        && i / factor < window.getFramebufferWidth()
                        && i / factor < window.getFramebufferHeight()
                        && window.getFramebufferWidth() / (i / factor + 1) >= 320
                        && window.getFramebufferHeight() / (i / factor + 1) >= 240
        ) {
            ++i;
        }

        if (forceUnicodeFont && i % 2 != 0) {
            ++i;
        }

        return i / factor;
    }
}
