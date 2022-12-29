package net.uku3lig.fractionalgui.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.uku3lig.fractionalgui.FractionalGui.FACTOR;

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
        return calculateScaleFactor(options.getGuiScale().getValue(), forcesUnicodeFont());
    }

    public double calculateScaleFactor(int guiScale, boolean forceUnicodeFont) {
        double i = 1;

        while(
                i != guiScale
                        && i / FACTOR < window.getFramebufferWidth()
                        && i / FACTOR < window.getFramebufferHeight()
                        && window.getFramebufferWidth() / (i / FACTOR + 1) >= 320
                        && window.getFramebufferHeight() / (i / FACTOR + 1) >= 240
        ) {
            ++i;
        }

        if (forceUnicodeFont && i % 2 != 0) {
            ++i;
        }

        return i / FACTOR;
    }
}
