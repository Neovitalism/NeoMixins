package me.neovitalism.noeggspoilers.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.command.impl.wiki.WikiCommand;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = WikiCommand.class, remap = false)
public class WikiCommandMixin {
    @Inject(method = "execute", at = @At(value = "INVOKE",
    target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",
    ordinal = 2),
    cancellable = true,
    locals = LocalCapture.CAPTURE_FAILSOFT)
    public void noEggSpoilers$execute(CommandSourceStack sender, String[] args, CallbackInfo ci, Pokemon pokemon, ServerPlayer player, PlayerPartyStorage storage, int var6) {
        if(pokemon.isEgg()) {
            sender.sendFailure(
                    Component.literal("You can't use this command on eggs!").withStyle(ChatFormatting.RED)
            );
            ci.cancel();
        }
    }
}
