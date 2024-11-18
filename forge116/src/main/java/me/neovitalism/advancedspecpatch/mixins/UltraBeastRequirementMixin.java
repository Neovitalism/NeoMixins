package me.neovitalism.advancedspecpatch.mixins;

import com.pixelmonmod.api.pokemon.requirement.AbstractBooleanPokemonRequirement;
import com.pixelmonmod.api.pokemon.requirement.impl.UltraBeastRequirement;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Set;

@Mixin(value = UltraBeastRequirement.class, remap = false)
public abstract class UltraBeastRequirementMixin extends AbstractBooleanPokemonRequirement {
    public UltraBeastRequirementMixin(Set<String> keys) {
        super(keys);
    }

    /**
     * @author Neovitalism
     * @reason Make it work (and optimize it a bit)!
     */
    @Overwrite
    public void applyData(Pokemon pixelmon) {
        if (this.value && !pixelmon.isUltraBeast()) {
            pixelmon.setSpecies(PixelmonSpecies.getRandomUltraBeast(), false);
        } else if (!this.value && pixelmon.isUltraBeast()) {
            pixelmon.setSpecies(PixelmonSpecies.getRandomSpecies(false, false, true), false);
        }
    }
}
