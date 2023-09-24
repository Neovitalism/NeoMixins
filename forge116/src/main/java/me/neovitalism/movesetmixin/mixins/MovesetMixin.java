package me.neovitalism.movesetmixin.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.stats.Moveset;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.ImmutableAttack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(value = Moveset.class, remap = false)
public abstract class MovesetMixin {
    @Shadow
    public Attack[] attacks;
    @Shadow
    @Final
    private List<ImmutableAttack> reminderMoves;

    /**
     * @author Neovitalism
     * @reason Stops egg hatching from crashing the server.
     */
    @Overwrite
    public void addCurrentMovesToReminder() {
        for (Attack attack : this.attacks) {
            if (attack != null) { // More specifically with this null check, here.
                this.reminderMoves.add(attack.getActualMove());
            }
        }
    }
}
