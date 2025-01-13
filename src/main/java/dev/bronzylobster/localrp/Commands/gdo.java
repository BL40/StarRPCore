package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.StarRPCore;
import dev.bronzylobster.localrp.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class gdo extends AbstractCommand{
    public gdo() {
        super("gdo");
    }

    FileConfiguration config = StarRPCore.getInstance().getConfig();

    @Override
    public void execute(CommandSender sender, String[] s) {
        Component msg = Utils.Placeholders(config.getString("DoFormat"), sender, "NULL", s, "NULL");

        Collection<? extends Player> viewers = Bukkit.getOnlinePlayers();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player viewer: viewers) {
                    viewer.sendMessage(msg);
                }
            }
        }.runTaskAsynchronously(StarRPCore.getInstance());
    }
}

