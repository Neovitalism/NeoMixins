package me.neovitalism.neomixins;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("neomixins")
public class NeoMixins {
    private static final Logger LOGGER = LogManager.getLogger();

    public NeoMixins() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
