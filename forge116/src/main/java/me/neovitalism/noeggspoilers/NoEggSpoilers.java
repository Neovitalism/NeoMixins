package me.neovitalism.noeggspoilers;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("noeggspoilers")
public class NoEggSpoilers {
    private static final Logger LOGGER = LogManager.getLogger();

    public NoEggSpoilers() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
