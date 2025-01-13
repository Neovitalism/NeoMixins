package me.neovitalism.evolutionquerymixin.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.pixelmonmod.pixelmon.entities.pixelmon.helpers.EvolutionQuery;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EvolutionQuery.class, remap = false)
public class EvolutionQueryMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lcom/pixelmonmod/pixelmon/api/pokemon/Pokemon;getOrCreatePixelmon()Lcom/pixelmonmod/pixelmon/entities/pixelmon/PixelmonEntity;"))
    public PixelmonEntity getOrCreatePicklemon(Pokemon pokemon) {
        return pokemon.getOrCreatePixelmon(pokemon.getOwnerPlayer());
    }
}
