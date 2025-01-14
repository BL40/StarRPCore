package dev.bronzylobster.starrpcore.Commands;

import dev.bronzylobster.starrpcore.StarRPCore;
import dev.bronzylobster.starrpcore.Utils.MessageManager;
import dev.bronzylobster.starrpcore.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class locme extends AbstractCommand{
    public locme() {
        super("locme", StarRPCore.getInstance());
    }

    FileConfiguration config = StarRPCore.getInstance().getConfig();
    MessageManager messageManager = new MessageManager(StarRPCore.getInstance());
    private final Map<String, String> message = new HashMap<>();

    @Override
    public void execute(CommandSender sender, String[] s) {
        message.put("message", messageManager.messageParserToString(s));
        Component msg = messageManager.messageToComponent(messageManager.getPlaceholders((Player) sender, "MeFormat", message));

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
