package me.neovitalism.neobattlefixes.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.Element;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.status.ElectricTerrain;
import com.pixelmonmod.pixelmon.battles.status.StatusType;
import com.pixelmonmod.pixelmon.battles.status.Terrain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ElectricTerrain.class, remap = false)
public abstract class ElectricTerrainMixin extends Terrain {
    public ElectricTerrainMixin(StatusType type, String langStart, String langEnd, boolean extended) {
        super(type, langStart, langEnd, extended);
    }

    /**
     * @author Neovitalism
     * @reason Fix Electric Terrain doubling Rising Voltage attack power twice.
     */
    @Overwrite
    public int[] modifyPowerAndAccuracyTarget(int power, int accuracy, PixelmonWrapper user, PixelmonWrapper target, Attack a) {
        if (this.affectsPokemon(user) && a.getType() == Element.ELECTRIC) power = (int) ((double) power * 1.3);
        return new int[]{power, accuracy};
    }
}