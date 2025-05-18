package xyz.huskydog.advchatneo;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DevMod implements ClientModInitializer {
    public static final String MOD_ID = "advchatneodevmod";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Devmod initialized");
    }
}