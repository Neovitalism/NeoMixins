package me.neovitalism.broketrainers.mixins;

import com.pixelmonmod.pixelmon.api.economy.BankAccount;
import com.pixelmonmod.pixelmon.entities.npcs.NPCTrainer;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(value = NPCTrainer.class, remap = false)
public class NPCTrainerMixin { // We make the account null to avoid sending without altering the winMoney variable.
    @Redirect(method = "loseBattle", at = @At(value = "INVOKE", target = "Lcom/pixelmonmod/pixelmon/api/economy/BankAccountProxy;getBankAccount(Lnet/minecraft/entity/player/ServerPlayerEntity;)Ljava/util/Optional;"))
    public Optional<BankAccount> getBankAccount(ServerPlayerEntity player) {
        return Optional.empty();
    }
}
