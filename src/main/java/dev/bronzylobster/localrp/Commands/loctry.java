package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.LocalRP;
import dev.bronzylobster.localrp.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class loctry extends AbstractCommand{
    public loctry() {
        super("loctry");
    }

    FileConfiguration config = LocalRP.getInstance().getConfig();

    @Override
    public void execute(CommandSender sender, String[] s) {
        String res = Math.random() > 0.5 ? config.getString("TryWin") : config.getString("TryLose");

        Component msg = Utils.Placeholders(config.getString("TryFormat"), sender, res, s);

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
