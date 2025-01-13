package me.neovitalism.evolutionquerymixin.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.pixelmonmod.pixelmon.entities.pixelmon.helpers.EvolutionQuery;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EvolutionQuery.class, remap = false)
public class EvolutionQueryMixin {
    @Shadow Pokemon pokemon;

    @Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
    public void neoMixins$tick(World world, CallbackInfo callback) {
        ServerPlayerEntity player = this.pokemon.getOwnerPlayer();
        if (player != null && world != player.level) callback.cancel();
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lcom/pixelmonmod/pixelmon/api/pokemon/Pokemon;getOrCreatePixelmon()Lcom/pixelmonmod/pixelmon/entities/pixelmon/PixelmonEntity;"))
    public PixelmonEntity getOrCreatePicklemon(Pokemon pokemon) {
        return pokemon.getOrCreatePixelmon(pokemon.getOwnerPlayer());
    }
}
