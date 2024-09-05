package me.clutchmasterftw.wreckcells.events;

import com.google.common.eventbus.Subscribe;
import me.clutchmasterftw.wreckcells.WreckCells;
import me.jet315.prisoncells.JetsPrisonCells;
import me.jet315.prisoncells.cells.Cell;
import me.jet315.prisoncells.users.CellUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import tech.mcprison.prison.ranks.events.RankUpEvent;
import tech.mcprison.prison.util.ChatColor;

import java.util.UUID;

import static me.clutchmasterftw.wreckcells.WreckCells.PREFIX;

public class OnWardChange implements Listener {
    private final String[] wardChangeRanks = {"F5", "E5", "D5", "C5", "B5", "A5"};

    @Subscribe
    public void onRankup(RankUpEvent e) {
        String nextRank = e.getNewRank().getName();
        for(String rank:wardChangeRanks) {
            if(rank.equals(nextRank)) {
                UUID uuid = e.getPlayer().getUUID();
                Player player = Bukkit.getPlayer(uuid);

                if(!e.isCanceled() && e.getPlayer().getBalance() >= e.getCost()) {
                    WreckCells.getPlugin().getLogger().info("{Ward Change Event} " + player.getName() + " has ranked into a new ward (" + rank.charAt(0) + ").");
                }

                CellUser cellUser = JetsPrisonCells.getInstance().getPlayerManager().getCellUser(uuid);
                if(!cellUser.getOwnedCells().isEmpty() && !e.isCanceled() && e.getPlayer().getBalance() >= e.getCost()) {
                    Cell cellOwned = cellUser.getOwnedCells().get(0);
                    cellOwned.setUnclaimed();
                    player.sendMessage(PREFIX + ChatColor.RED + "You've been evicted from your cell due to your ward change. You were warned!");
                    //Log the cell number that was lost upon changing wards
                    WreckCells.getPlugin().getLogger().info("{Ward Change Event} " + player.getName() + " has lost their cell (ID: " + cellOwned.getCellName() + ").");
                }

                break;
            }
        }
    }
}
