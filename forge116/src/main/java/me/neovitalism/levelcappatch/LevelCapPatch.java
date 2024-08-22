package me.neovitalism.levelcappatch;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("levelcappatch")
public class LevelCapPatch {
    private static final Logger LOGGER = LogManager.getLogger();

    public LevelCapPatch() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
