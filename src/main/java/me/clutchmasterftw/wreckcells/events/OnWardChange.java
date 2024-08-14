package me.clutchmasterftw.wreckcells.events;

import com.google.common.eventbus.Subscribe;
import me.jet315.prisoncells.JetsPrisonCells;
import me.jet315.prisoncells.cells.Cell;
import me.jet315.prisoncells.users.CellUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import tech.mcprison.prison.ranks.events.RankUpEvent;
import tech.mcprison.prison.util.ChatColor;

import java.util.Objects;
import java.util.UUID;

public class OnWardChange implements Listener {
    public static final String PREFIX = ChatColor.BLUE + "Wreck" + ChatColor.DARK_BLUE + "MC " + ChatColor.WHITE + "Cells " + ChatColor.GRAY + "» " + ChatColor.RESET;
    private final String[] wardChangeRanks = {"F5", "E5", "D5", "C5", "B5", "A5"};

    @Subscribe
    public void onRankup(RankUpEvent e) {
//        e.setCanceled(true);
//        e.setCancelReason("Just because :)");
//
//        System.out.println("Rankup was canceled.");

        //Need to check if rankup is successful (if the player doesn't have enough money, this code will still be executed :/

        String nextRank = e.getNewRank().getName();
        System.out.println(nextRank);
        for(String rank:wardChangeRanks) {
            if(rank.equals(nextRank)) {
                System.out.println("You ranked into a new ward!");
                break;
            }
        }

        UUID uuid = e.getPlayer().getUUID();
        Player player = Bukkit.getPlayer(uuid);

        CellUser cellUser = JetsPrisonCells.getInstance().getPlayerManager().getCellUser(uuid);
        if(!cellUser.getOwnedCells().isEmpty()) {
            Cell cellOwned = cellUser.getOwnedCells().get(0);
            cellOwned.setUnclaimed();
            player.sendMessage(PREFIX + ChatColor.RED + "You've been evicted from your cell due to your ward change. You were warned!");
            //Log the cell number that was lost upon changing wards
        } else {
            player.sendMessage("You don't own a cell :/ (TEST)");
        }
    }
}
