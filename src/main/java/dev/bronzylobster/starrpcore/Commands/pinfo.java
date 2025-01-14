package dev.bronzylobster.starrpcore.Commands;

import dev.bronzylobster.starrpcore.StarRPCore;
import dev.bronzylobster.starrpcore.Utils.MessageManager;
import dev.bronzylobster.starrpcore.Utils.RPParams;
import dev.bronzylobster.starrpcore.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pinfo extends AbstractCommand {

    public pinfo(){super("pinfo", StarRPCore.getInstance());}
    final YamlFile ymlParams = StarRPCore.getInstance().getYmlparams();
    public List<String> params = RPParams.getParams();
    @NotNull FileConfiguration config = StarRPCore.getInstance().getConfig();
    MessageManager messageManager = new MessageManager(StarRPCore.getInstance());

    @Override
    public void execute(CommandSender sender, String[] s) {
        String nick = s[0];
        CommandSender cs = (CommandSender) Bukkit.getOfflinePlayer(nick);
        List<String> format = config.getStringList("RPParams.format");
        Component msg;

        for (String str : format) {
            str = RPParams.getParamToString(nick, str);
            msg = messageManager.messageToComponent(str);
            sender.sendMessage(msg);
        }
    }
}
