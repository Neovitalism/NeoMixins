package me.neovitalism.goodbyegroupspawns.mixins;

import com.pixelmonmod.pixelmon.api.spawning.archetypes.group.GroupSpawnSettings;
import com.pixelmonmod.pixelmon.entities.SpawnLocationType;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GroupSpawnSettings.class, remap = false)
public abstract class GroupSpawnSettingsMixin {
    /**
     * @author Neovitalism
     * @reason GoodbyeGroupSpawning
     */
    @Overwrite
    public boolean shouldSpawn() {
        return false;
    }

    /**
     * @author Neovitalism
     * @reason GoodbyeGroupSpawning
     */
    @Overwrite
    public int getRandomAmount() {
        return 0;
    }

    /**
     * @author Neovitalism
     * @reason GoodbyeGroupSpawning
     */
    @Overwrite
    public PixelmonEntity spawnPokemon(World level, BlockPos center, SpawnLocationType spawnLocationType, int flockId) {
        return null;
    }
}
