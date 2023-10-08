package me.neovitalism.goodbyegroupspawns.mixins;

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
    public PixelmonEntity spawnPokemon(Level level, BlockPos center, SpawnLocationType spawnLocationType, int flockId) {
        return null;
    }
}
