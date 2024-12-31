package me.neovitalism.loadeddicepatch.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.ability.abilities.SkillLink;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.DamageTypeEnum;
import com.pixelmonmod.pixelmon.battles.attacks.specialAttacks.basic.TripleAxel;
import com.pixelmonmod.pixelmon.battles.controller.log.AttackResult;
import com.pixelmonmod.pixelmon.battles.controller.log.MoveResults;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.status.StatusType;
import com.pixelmonmod.pixelmon.enums.heldItems.EnumHeldItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TripleAxel.class, remap = false)
public abstract class TripleAxelMixin {
    /**
     * @author Neovitalism
     * @reason Fixes accuracy, power, and loaded dice usage. This whole system hurts my eyes and my soul. Please send help.
     */
    @Overwrite
    public AttackResult applyEffectStart(PixelmonWrapper user, PixelmonWrapper target) {
        if (user.inMultipleHit) return AttackResult.proceed;
        user.inMultipleHit = true;
        user.attack.setMoveAccuracy(90); // Why does it need this to not always miss first hit lol
        boolean hasSubstitute = false, missed = false;
        boolean loadedDice = user.hasHeldItem() && user.getUsableHeldItem().getHeldItemType() == EnumHeldItems.loadedDice;
        int timesHit;
        for (timesHit = 0; timesHit < 3; timesHit++) {
            user.attack.setOverridePower((timesHit + 1) * 20);
            if (timesHit > 0 && (user.getBattleAbility() instanceof SkillLink || loadedDice)) user.attack.setMoveAccuracy(-1);
            hasSubstitute = target.hasStatus(StatusType.Substitute);
            MoveResults[] results = user.useAttackOnly();
            for (MoveResults result : results) {
                if (result.result == AttackResult.failed || result.result == AttackResult.missed) {
                    missed = true;
                    break;
                }
                if (user.attack.moveResult == null) break;
                MoveResults finalResult = user.attack.moveResult;
                finalResult.damage += result.damage;
                finalResult.fullDamage += result.fullDamage;
            }
            if (missed || !target.isAlive() || !user.isAlive()) break;
        }
        user.inMultipleHit = false;
        user.attack.playAnimation(user, target);
        user.attack.sendEffectiveChat(user, target);
        if (timesHit > 0) user.bc.sendToAll("multiplehit.times", user.getNickname(), timesHit);
        user.attack.setOverridePower(20);
        user.attack.setMoveAccuracy(90);
        Attack.postProcessAttackAllHits(user, target, user.attack, (float) user.attack.moveResult.damage, DamageTypeEnum.ATTACK, hasSubstitute);
        if (!hasSubstitute) Attack.applyContactLate(user, target);
        return AttackResult.hit;
    }
}
