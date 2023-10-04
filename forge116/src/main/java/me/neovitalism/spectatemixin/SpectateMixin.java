package me.neovitalism.spectatemixin;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("spectatemixin")
public class SpectateMixin {
    private static final Logger LOGGER = LogManager.getLogger();

    public SpectateMixin() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
