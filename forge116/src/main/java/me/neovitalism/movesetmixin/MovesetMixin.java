package me.neovitalism.movesetmixin;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("movesetmixin")
public class MovesetMixin {
    private static final Logger LOGGER = LogManager.getLogger();

    public MovesetMixin() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
