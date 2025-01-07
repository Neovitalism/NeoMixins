package me.neovitalism.neobattlefixes.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.ability.Ability;
import com.pixelmonmod.pixelmon.api.pokemon.ability.abilities.MagicGuard;
import com.pixelmonmod.pixelmon.api.pokemon.ability.abilities.SheerForce;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.DamageTypeEnum;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.items.heldItems.LifeOrbItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = LifeOrbItem.class, remap = false)
public abstract class LifeOrbItemMixin {
    /**
     * @author Neovitalism
     * @reason Remove setting of a shared variable.
     */
    @Overwrite
    public void dealtDamage(PixelmonWrapper attacker, PixelmonWrapper defender, Attack attack, DamageTypeEnum damageType) {}

    /**
     * @author Neovitalism
     * @reason Fix Life Orb causing issues with a shared variable and taking recoil from moves that did no damage.
     */
    @Overwrite
    public void applyEffectAfterAllTargets(PixelmonWrapper attacker) {
        if (attacker.attack.damageResult <= 0) return;
        Ability attackerAbility = attacker.getBattleAbility();
        if (attackerAbility instanceof MagicGuard) return;
        if (attackerAbility instanceof SheerForce && attacker.attack.getMove().hasSecondaryEffect()) return;
        int recoil = attacker.getPercentMaxHealth(10.0F, true);
        attacker.bc.sendToAll("pixelmon.helditem.lifeowner", attacker.getNickname());
        attacker.doBattleDamage(attacker, (float) recoil, DamageTypeEnum.ITEM);
    }
}