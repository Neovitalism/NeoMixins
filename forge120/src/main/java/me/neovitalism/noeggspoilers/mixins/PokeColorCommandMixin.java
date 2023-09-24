package me.neovitalism.noeggspoilers.mixins;

import com.pixelmonmod.pixelmon.api.command.PixelmonCommandUtils;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.command.impl.PokeColorCommand;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = PokeColorCommand.class, remap = false)
public class PokeColorCommandMixin {
    @Inject(method = "execute", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",
            ordinal = 1),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILSOFT)
    public void noEggSpoilers$execute(CommandSourceStack sender, String[] args, CallbackInfo ci, ServerPlayer target, PlayerPartyStorage storage, int slot, Pokemon pokemon, String nickname) {
        String species = (pokemon.isEgg()) ? "Egg" : pokemon.getSpecies().getName();
        sender.sendSuccess(() -> {
            return PixelmonCommandUtils.format(ChatFormatting.GREEN,
                    "pixelmon.command.pokecolor.success",
                    new Object[]{species, pokemon.getDisplayName()});
        }, false);
        ci.cancel();
    }
}
