package me.neovitalism.spectatemixin.mixins;

import com.pixelmonmod.pixelmon.command.impl.SpectateCommand;
import net.minecraft.command.CommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = SpectateCommand.class, remap = false)
public class SpectateCommandMixin {
    /**
     * @author Neovitalism
     * @reason Stop conflicting with Minecraft's /spectate command.
     */
    @Overwrite
    public String getName() {
        return "spectatebattle";
    }

    /**
     * @author Neovitalism
     * @reason Change usage since we changed the command.
     */
    @Overwrite
    public String getUsage(CommandSource sender) {
        return "/spectatebattle <playerName>";
    }
}