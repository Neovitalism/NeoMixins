package me.neovitalism.pokeballdatastorage;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("pokeballdatastorage")
public class PokeballDataStorage {
    private static final Logger LOGGER = LogManager.getLogger();

    public PokeballDataStorage() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
