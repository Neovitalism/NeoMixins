package me.neovitalism.loadeddicepatch.mixins;

import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.DamageTypeEnum;
import com.pixelmonmod.pixelmon.battles.attacks.specialAttacks.basic.ScaleShot;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ScaleShot.class, remap = false)
public abstract class ScaleShotMixin {
    @Inject(method = "dealtDamage", at = @At(value = "HEAD"), cancellable = true)
    public void ldp$dealtDamage(PixelmonWrapper attacker, PixelmonWrapper defender, Attack attack, DamageTypeEnum damageType, CallbackInfo callback) {
        if (damageType != DamageTypeEnum.ATTACK) callback.cancel();
    }
}
