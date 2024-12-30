package me.neovitalism.loadeddicepatch.mixins;

import com.pixelmonmod.pixelmon.api.battles.attack.AttackRegistry;
import com.pixelmonmod.pixelmon.api.pokemon.ability.abilities.BattleBond;
import com.pixelmonmod.pixelmon.api.pokemon.ability.abilities.SkillLink;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.DamageTypeEnum;
import com.pixelmonmod.pixelmon.battles.attacks.specialAttacks.attackModifiers.AttackModifierBase;
import com.pixelmonmod.pixelmon.battles.attacks.specialAttacks.attackModifiers.MultipleHit;
import com.pixelmonmod.pixelmon.battles.controller.log.AttackResult;
import com.pixelmonmod.pixelmon.battles.controller.log.MoveResults;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.controller.participants.RaidPixelmonParticipant;
import com.pixelmonmod.pixelmon.battles.status.StatusType;
import com.pixelmonmod.pixelmon.enums.heldItems.EnumHeldItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = MultipleHit.class, remap = false)
public abstract class MultipleHitMixin extends AttackModifierBase {
    @Shadow public int maxHits;
    @Shadow public int minHits;

    /**
     * @author Neovitalism
     * @reason Fixes loaded dice. This whole system hurts my eyes and my soul. Please send help.
     * P.S. Sorry for the overwrite. Stuff needed changing for the sake of my own sanity.
     * Altering fields on a registry object is pain. Can someone please come make these values final?
     */
    @Overwrite
    public AttackResult applyEffectDuring(PixelmonWrapper user, PixelmonWrapper target) {
        if (user.inMultipleHit) return AttackResult.proceed;
        user.inMultipleHit = true;
        int timesShouldHit = 0, timesHit, minHits = this.minHits;
        if (user.getBattleAbility() instanceof SkillLink) timesShouldHit = this.maxHits;
        else if (user.getBattleAbility() instanceof BattleBond && user.attack.isAttack(AttackRegistry.WATER_SHURIKEN)
                && user.getForm().isForm("ash")) timesShouldHit = 3;
        else if (this.maxHits != 0) {
            if (user.hasHeldItem() && user.getUsableHeldItem().getHeldItemType() == EnumHeldItems.loadedDice) minHits = Math.min(this.maxHits, 4);
            if (minHits == 2 && this.maxHits == 5) {
                int random = RandomHelper.getRandomNumberBetween(0, 9);
                if (user.bc.simulateMode) timesShouldHit = 1;
                else if (random < 3) timesShouldHit = 2;
                else if (random < 6) timesShouldHit = 3;
                else if (random < 8) timesShouldHit = 4;
                else timesShouldHit = 5;
            } else timesShouldHit = RandomHelper.getRandomNumberBetween(minHits, this.maxHits);
        }
        if (target.isRaidPokemon() && ((RaidPixelmonParticipant) target.getParticipant()).areShieldsUp()) timesShouldHit = 1;
        int accuracy = user.attack.getMove().getAccuracy();
        boolean hasSubstitute = false;
        MoveResults finalResult;
        for (timesHit = 0; timesHit < timesShouldHit; timesHit++) {
            user.attack.setMoveAccuracy(-1);
            hasSubstitute = target.hasStatus(StatusType.Substitute);
            MoveResults[] results = user.useAttackOnly();
            for (MoveResults result : results) {
                if (user.attack.moveResult == null) break;
                finalResult = user.attack.moveResult;
                finalResult.damage += result.damage;
                finalResult.fullDamage += result.fullDamage;
            }
            if (!target.isAlive() || !user.isAlive()) break;
        }
        user.attack.setMoveAccuracy(accuracy);
        if (user.bc.simulateMode && timesShouldHit == 1 && user.attack.moveResult != null) {
            user.attack.moveResult.damage = (int) Math.min((double) user.attack.moveResult.damage * 3.168, target.getHealth());
            finalResult = user.attack.moveResult;
            finalResult.fullDamage = (int) ((double) finalResult.fullDamage * 3.168);
        }
        user.inMultipleHit = false;
        user.attack.sendEffectiveChat(user, target);
        if (timesHit > 1) user.bc.sendToAll("multiplehit.times", user.getNickname(), timesHit);
        Attack.postProcessAttackAllHits(user, target, user.attack, user.attack.moveResult == null ? 0.0F : (float) user.attack.moveResult.damage, DamageTypeEnum.ATTACK, hasSubstitute);
        if (!hasSubstitute) Attack.applyContactLate(user, target);
        return AttackResult.hit;
    }
}
