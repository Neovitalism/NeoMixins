package me.neovitalism.neobattlefixes.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.ability.Ability;
import com.pixelmonmod.pixelmon.api.pokemon.ability.abilities.Protosynthesis;
import com.pixelmonmod.pixelmon.api.pokemon.ability.abilities.QuarkDrive;
import com.pixelmonmod.pixelmon.battles.controller.BattleController;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.status.StatusType;
import com.pixelmonmod.pixelmon.battles.status.Terrain;
import com.pixelmonmod.pixelmon.battles.status.Weather;
import com.pixelmonmod.pixelmon.items.heldItems.BoosterEnergyItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BoosterEnergyItem.class, remap = false)
public abstract class BoosterEnergyItemMixin {
    /**
     * @author Neovitalism
     * @reason Fix NPE related to Booster Energy.
     */
    @Overwrite
    public void onTerrainSwitch(BattleController bc, PixelmonWrapper user, Terrain terrain) {
        Ability ability = user.pokemon.getAbility();
        if (ability instanceof QuarkDrive && (terrain == null || terrain.type != StatusType.ElectricTerrain)) {
            QuarkDrive boostAbility = (QuarkDrive) ability;
            boostAbility.activateViaItem(user);
        }
    }

    /**
     * @author Neovitalism
     * @reason Fix NPE related to Booster Energy.
     */
    @Overwrite
    public void onWeatherSwitch(BattleController bc, PixelmonWrapper user, Weather weather) {
        Ability ability = user.pokemon.getAbility();
        if (ability instanceof Protosynthesis && (weather == null || weather.type != StatusType.Sunny)) {
            Protosynthesis boostAbility = (Protosynthesis) ability;
            boostAbility.activateViaItem(user);
        }
    }
}