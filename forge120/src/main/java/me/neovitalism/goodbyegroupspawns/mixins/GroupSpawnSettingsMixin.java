package me.neovitalism.goodbyegroupspawns.mixins;

import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.api.spawning.SpawnLocation;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnInfoPokemon;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.group.GroupSpawnSettings;
import com.pixelmonmod.pixelmon.entities.SpawnLocationType;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GroupSpawnSettings.class, remap = false)
public abstract class GroupSpawnSettingsMixin {
    /**
     * @author Neovitalism
     * @reason GoodbyeGroupSpawns
     */
    @Overwrite
    public boolean shouldSpawn() {
        return false;
    }

    /**
     * @author Neovitalism
     * @reason GoodbyeGroupSpawns
     */
    @Overwrite
    public int getRandomAmount() {
        return 0;
    }

    /**
     * @author Neovitalism
     * @reason GoodbyeGroupSpawns
     */
    @Overwrite
    public PixelmonEntity spawnPokemon(Level par1, BlockPos par2, SpawnLocationType par3, int par4,
                                       AbstractSpawner par5, SpawnInfoPokemon par6, SpawnLocation par7) {
        return null;
    }
}
