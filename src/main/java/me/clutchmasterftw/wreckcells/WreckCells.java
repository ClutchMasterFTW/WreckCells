package me.clutchmasterftw.wreckcells;

import me.clutchmasterftw.wreckcells.events.OnCellPurchase;
import me.clutchmasterftw.wreckcells.events.OnWardChange;
import me.jet315.prisoncells.JetsPrisonCells;
import me.jet315.prisoncells.listeners.player.CellPlayerSignInteract;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tech.mcprison.prison.Prison;
import tech.mcprison.prison.util.ChatColor;

import java.util.logging.Logger;

public final class WreckCells extends JavaPlugin {
    public static final String PREFIX = ChatColor.BLUE + "Wreck" + ChatColor.DARK_BLUE + "MC " + ChatColor.WHITE + "Cells " + ChatColor.GRAY + "» " + ChatColor.RESET;

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
//        CellPlayerSignInteract test = new CellPlayerSignInteract();
//
//        test.onSignClick();
        Bukkit.getServer().getPluginManager().registerEvents(new OnCellPurchase(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
