package me.neovitalism.noeggspoilers.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.command.impl.GiftCommand;
import net.minecraft.util.text.TranslationTextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = GiftCommand.class, remap = false)
public class GiftCommandMixin {
    @Redirect(method = "execute", at = @At(value = "INVOKE",
            target = "Lcom/pixelmonmod/pixelmon/api/pokemon/Pokemon;getTranslatedName()Lnet/minecraft/util/text/TranslationTextComponent;"))
    public TranslationTextComponent noEggSpoilers$execute(Pokemon instance) {
        if(instance.isEgg()) return new TranslationTextComponent("pixelmon.egg");
        return instance.getTranslatedName();
    }
}
