package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.LocalRP;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class fix extends AbstractCommand{
    public fix() {
        super("fix");
    }

    @Override
    public void execute(CommandSender sender, String[] s) {
        Collection<? extends Player> op = LocalRP.getInstance().getServer().getOnlinePlayers();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p: op) {
                    p.setCustomNameVisible(false);
                    p.displayName(Component.text(p.getName()));
                }
            }
        }.runTaskAsynchronously(LocalRP.getInstance());
    }
}
