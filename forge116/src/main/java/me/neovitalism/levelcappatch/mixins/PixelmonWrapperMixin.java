package me.neovitalism.levelcappatch.mixins;

import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value=PixelmonWrapper.class, remap = false)
public abstract class PixelmonWrapperMixin {
    @Shadow public abstract void recalculateMaxHP();

    @Inject(method={"setTempLevel"}, at={@At(value="RETURN")})
    public void levelCapPatch$setTempLevel(int level, CallbackInfo ci) {
        this.recalculateMaxHP();
    }
}
