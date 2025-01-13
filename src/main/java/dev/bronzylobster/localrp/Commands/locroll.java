package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.StarRPCore;
import dev.bronzylobster.localrp.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class locroll extends AbstractCommand{
    public locroll() {
        super("locroll");
    }

    FileConfiguration config = StarRPCore.getInstance().getConfig();

    @Override
    public void execute(CommandSender sender, String[] s) {
        int max = s.length > 0 ? Utils.getNumber(s[0], sender) : 6;
        if (max > config.getInt("RollMax")) {
            max = config.getInt("RollMax");
        } else if (max < config.getInt("RollMin")) {
            max = config.getInt("RollMin");
        }
        int res = (int) ((Math.random() * max) + 1);

        Component msg = Utils.Placeholders(config.getString("RollFormat"), sender, String.valueOf(res), s, String.valueOf(max));

        Collection<Player> viewers = ((Player) sender).getLocation().getNearbyPlayers(config.getDouble("Radius"));

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
