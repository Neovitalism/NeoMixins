package me.neovitalism.capturecalculationfix;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("capturecalculationfix")
public class CaptureCalculationFix {
    private static final Logger LOGGER = LogManager.getLogger();

    public CaptureCalculationFix() {
        LOGGER.log(Level.INFO, "Loaded!");
    }
}
