package me.neovitalism.noeggspoilers.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.command.impl.GiftCommand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GiftCommand.class, remap = false)
public class GiftCommandMixin {
    @Redirect(method = "execute", at = @At(value = "INVOKE",
            target = "Lcom/pixelmonmod/pixelmon/api/pokemon/Pokemon;getTranslatedName()Lnet/minecraft/network/chat/MutableComponent;"))
    public MutableComponent noEggSpoilers$execute(Pokemon instance) {
        if(instance.isEgg()) return Component.translatable("pixelmon.egg");
        return instance.getTranslatedName();
    }
}
