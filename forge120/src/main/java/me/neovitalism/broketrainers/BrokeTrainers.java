package me.neovitalism.broketrainers;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("broketrainers")
public class BrokeTrainers {
    private static final Logger LOGGER = LogManager.getLogger();

    public BrokeTrainers() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
