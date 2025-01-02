package me.neovitalism.loadeddicepatch;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("loadeddicepatch")
public class LoadedDicePatch {
    private static final Logger LOGGER = LogManager.getLogger();

    public LoadedDicePatch() {
        LOGGER.log(Level.INFO, "Loaded!");
    }

    public static void logForDebug(String output) {
        LOGGER.log(Level.WARN, output);
    }
}
