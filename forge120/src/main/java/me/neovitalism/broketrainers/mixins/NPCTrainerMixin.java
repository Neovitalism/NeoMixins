package me.neovitalism.broketrainers.mixins;

import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = NPCTrainer.class, remap = false)
public class NPCTrainerMixin { // We make the account null to avoid sending without altering the winMoney variable.
    @Redirect(method = "loseBattle", at = @At(value = "INVOKE", target = "Lcom/pixelmonmod/pixelmon/api/economy/BankAccountProxy;getBankAccountNow(Lnet/minecraft/server/level/ServerPlayer;)Lcom/pixelmonmod/pixelmon/api/economy/BankAccount;"))
    public BankAccount getBankAccount(Player player) {
        return null;
    }
}