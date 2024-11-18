package me.neovitalism.advancedspecpatch;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("advancedspecpatch")
public class AdvancedSpecPatch {
    private static final Logger LOGGER = LogManager.getLogger();

    public AdvancedSpecPatch() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
