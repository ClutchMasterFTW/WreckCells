package me.clutchmasterftw.wreckcells.events;

import me.jet315.prisoncells.JetsPrisonCells;
import me.jet315.prisoncells.cells.Cell;
import me.jet315.prisoncells.gui.GUISession;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import tech.mcprison.prison.PrisonAPI;
import tech.mcprison.prison.ranks.data.PlayerRank;
import tech.mcprison.prison.ranks.data.RankPlayer;

import java.util.UUID;

import static me.clutchmasterftw.wreckcells.WreckCells.PREFIX;

public class OnCellPurchase implements Listener {
    @EventHandler
    public void onGUICellsInteract(InventoryClickEvent e) {
        if (e.getClickedInventory() != null) {
            final Player player = (Player) e.getWhoClicked();
            if(e.getInventory().getItem(13) == null) {
                return;
            }
            if (e.getInventory().getItem(13).getType() == Material.CLOCK) {
                if (JetsPrisonCells.getInstance().getGuiManager().getActiveSessions().containsKey(player.getUniqueId())) {
                    final GUISession guiSession = (GUISession) JetsPrisonCells.getInstance().getGuiManager().getActiveSessions().get(player.getUniqueId());
//                    e.setResult(Event.Result.DENY);
                    if (e.getClickedInventory().equals(guiSession.getInventory())) {
                        String title = ChatColor.translateAlternateColorCodes('&', "&ePurchase Cell");

                        Cell cell = guiSession.getCell();
                        String cellID = cell.getCellName();
                        char cellWard = cellID.charAt(0);
                        UUID playerUUID = player.getUniqueId();
                        RankPlayer rankPlayer = PrisonAPI.getPlayer(playerUUID).get().getRankPlayer();
                        PlayerRank playerRank = rankPlayer.getPlayerRank("default");
                        char playerRankName = playerRank.getRank().getName().charAt(0);
//                        player.sendMessage(PREFIX + "cellWard: " + cellWard + " | playerRankName: " + playerRankName);

                        if (e.getView().getTitle().equals(title) && cellWard != playerRankName) {
                            e.setCancelled(true);
                            ItemStack clickedItem = e.getCurrentItem();

                            if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                                return;
                            }

                            // Assuming the clicked item represents a rentable cell
                            // Implement cell renting logic here
                            // For example: deducting money, assigning the cell, etc.
                            player.closeInventory();

                            player.sendMessage(PREFIX + ChatColor.RED + "You are unable to rent this cell because you have a higher ward status.");
                        }
                    }
                }
            }
        }
    }
}
