package me.neovitalism.neobattlefixes;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("neobattlefixes")
public class NeoBattleFixes {
    private static final Logger LOGGER = LogManager.getLogger();

    public NeoBattleFixes() {
        LOGGER.log(Level.INFO, "Loaded!");
    }

    public static void logForDebug(String output) {
        LOGGER.log(Level.WARN, output);
    }
}
