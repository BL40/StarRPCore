package dev.bronzylobster.starrpcore.Commands;

import dev.bronzylobster.starrpcore.StarRPCore;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class fix extends AbstractCommand{
    public fix() {
        super("fix", StarRPCore.getInstance());
    }

    @Override
    public void execute(CommandSender sender, String[] s) {
        Collection<? extends Player> op = StarRPCore.getInstance().getServer().getOnlinePlayers();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p: op) {
                    p.setCustomNameVisible(false);
                    p.displayName(Component.text(p.getName()));
                }
            }
        }.runTaskAsynchronously(StarRPCore.getInstance());
    }
}
