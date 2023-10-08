package me.neovitalism.goodbyegroupspawns;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("goodbyegroupspawns")
public class GoodbyeGroupSpawns {
    private static final Logger LOGGER = LogManager.getLogger();

    public GoodbyeGroupSpawns() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
