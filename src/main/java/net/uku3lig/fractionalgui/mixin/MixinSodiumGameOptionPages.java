package net.uku3lig.fractionalgui.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import net.minecraft.client.util.Window;
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

    private MixinSodiumGameOptionPages() {}
}
