package me.neovitalism.neobattlefixes.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.DamageTypeEnum;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.enums.heldItems.EnumHeldItems;
import com.pixelmonmod.pixelmon.items.HeldItem;
import com.pixelmonmod.pixelmon.items.heldItems.WeaknessPolicyItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = WeaknessPolicyItem.class, remap = false)
public abstract class WeaknessPolicyItemMixin extends HeldItem {
    public WeaknessPolicyItemMixin(EnumHeldItems heldItemType, Properties properties) {
        super(heldItemType, properties);
    }

    /**
     * @author Neovitalism
     * @reason Fix Rocky Helmet activating Weakness Policy.
     */
    @Overwrite
    public void postProcessDamagingAttackTarget(PixelmonWrapper attacker, PixelmonWrapper target, Attack attack, float damage) {

    }

    @Override
    public void tookDamage(PixelmonWrapper attacker, PixelmonWrapper defender, float damage, DamageTypeEnum damageType) {
        if (damageType != DamageTypeEnum.ATTACK) return;
        if ((damage > 0.0F || defender.getSpecies().is(PixelmonSpecies.SHEDINJA)) && attacker.attack.getTypeEffectiveness(attacker, defender) >= 2.0) {
            defender.bc.sendToAll("pixelmon.abilities.activated", defender.getNickname(), defender.getHeldItem().getLocalizedName());
            defender.getBattleStats().modifyStat(2, BattleStatsType.ATTACK, BattleStatsType.SPECIAL_ATTACK);
            defender.consumeItem();
        }
    }
}
