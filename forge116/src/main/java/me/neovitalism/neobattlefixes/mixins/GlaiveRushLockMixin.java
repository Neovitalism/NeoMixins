package me.neovitalism.neobattlefixes.mixins;

import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.status.GlaiveRushLock;
import com.pixelmonmod.pixelmon.battles.status.StatusBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GlaiveRushLock.class, remap = false)
public abstract class GlaiveRushLockMixin extends StatusBase {
    /**
     * @author Neovitalism
     * @reason Status uses the wrong method.
     */
    @Overwrite
    public int[] modifyPowerAndAccuracyUser(int power, int accuracy, PixelmonWrapper user, PixelmonWrapper target, Attack a) {
        return new int[]{power, accuracy};
    }

    @Override
    public int[] modifyPowerAndAccuracyTarget(int power, int accuracy, PixelmonWrapper user, PixelmonWrapper target, Attack a) {
        return new int[]{power * 2, -2};
    }
}