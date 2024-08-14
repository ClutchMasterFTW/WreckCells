package me.clutchmasterftw.wreckcells;

import me.clutchmasterftw.wreckcells.events.OnWardChange;
import org.bukkit.plugin.java.JavaPlugin;
import tech.mcprison.prison.Prison;

import java.util.logging.Logger;

public final class WreckCells extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger logger = getLogger();
        logger.severe("[WRECKCELLS] WreckCells has loaded!");
        logger.severe("[WRECKCELLS] WreckCells has loaded!");
        logger.severe("[WRECKCELLS] WreckCells has loaded!");
        logger.severe("[WRECKCELLS] WreckCells has loaded!");
        logger.severe("[WRECKCELLS] WreckCells has loaded!");

        Prison.get().getEventBus().register(new OnWardChange());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
