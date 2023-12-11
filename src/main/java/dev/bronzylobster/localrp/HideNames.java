package dev.bronzylobster.localrp;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Team;

public class HideNames implements Listener {

    Team team = LocalRP.getInstance().getTeam();

    FileConfiguration config = LocalRP.getInstance().getConfig();

    @EventHandler
    public void joinListener(PlayerJoinEvent e) {
        if(!config.getBoolean("HideNames")) {
            return;
        }

        Player p = e.getPlayer();
        String name = p.getName();
        if (!team.hasEntry(name)) {
            team.addEntry(name);
        }
    }

    @EventHandler
    public void onPlayerTouch(PlayerInteractEntityEvent e) {
        if(!config.getBoolean("HideNames")) {
            return;
        }

        Player interacter = e.getPlayer();
        Player interactee = e.getRightClicked() instanceof Player ? (Player) e.getRightClicked() : null;

        if (interactee != null) {
            interacter.sendActionBar(Component.text(interactee.getName()).color(TextColor.color(0x666666)));
        }
    }
}
