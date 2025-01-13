package me.neovitalism.evolutionquerymixin;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("evolutionquerymixin")
public class EvolutionQueryMixin {
    private static final Logger LOGGER = LogManager.getLogger();

    public EvolutionQueryMixin() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
