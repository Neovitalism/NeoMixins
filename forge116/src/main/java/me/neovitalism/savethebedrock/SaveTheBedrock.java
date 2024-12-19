package me.neovitalism.savethebedrock;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("savethebedrock")
public class SaveTheBedrock {
    private static final Logger LOGGER = LogManager.getLogger();

    public SaveTheBedrock() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
