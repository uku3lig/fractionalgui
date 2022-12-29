package net.uku3lig.fractionalgui.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import net.minecraft.client.MinecraftClient;
import net.uku3lig.fractionalgui.FractionalGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public class MixinSodiumGameOptionPages {
    @ModifyConstant(method = "lambda$general$9", constant = @Constant(intValue = 4))
    private static int changeScaleFactor(int constant) {
        return MinecraftClient.getInstance().getWindow().calculateScaleFactor(0, MinecraftClient.getInstance().forcesUnicodeFont())
                * FractionalGui.getManager().getConfig().getFactor();
    }

    private MixinSodiumGameOptionPages() {}
}
