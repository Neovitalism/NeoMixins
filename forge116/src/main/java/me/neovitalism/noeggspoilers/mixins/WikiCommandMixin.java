package me.neovitalism.noeggspoilers.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.command.impl.wiki.WikiCommand;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = WikiCommand.class, remap = false)
public class WikiCommandMixin {
    @Inject(method = "execute", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/command/CommandSource;sendSuccess(Lnet/minecraft/util/text/ITextComponent;Z)V",
            ordinal = 2),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILSOFT)
    public void noEggSpoilers$execute(CommandSource sender, String[] args, CallbackInfo ci, Pokemon pokemon, ServerPlayerEntity player, PlayerPartyStorage storage, int var6) {
        if(pokemon.isEgg()) {
            sender.sendFailure(
                new StringTextComponent("You can't use this command on eggs!").withStyle(TextFormatting.RED)
            );
            ci.cancel();
        }
    }
}
