package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.LocalRP;
import dev.bronzylobster.localrp.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class locme extends AbstractCommand{
    public locme() {
        super("locme");
    }

    FileConfiguration config = LocalRP.getInstance().getConfig();

    @Override
    public void execute(CommandSender sender, String[] s) {
        Component msg = Utils.Placeholders(config.getString("MeFormat"), sender, "NULL", s);

       Collection<Player> viewers = ((Player) sender).getLocation().getNearbyPlayers(config.getDouble("Radius"));

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player viewer: viewers) {
                    viewer.sendMessage(msg);
                }
            }
        }.runTaskAsynchronously(LocalRP.getInstance());
    }
}
