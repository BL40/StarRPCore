package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.StarRPCore;
import dev.bronzylobster.localrp.Utils.RPParams;
import dev.bronzylobster.localrp.Utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.List;

public class pinfo extends AbstractCommand {

    public pinfo(){super("pinfo");}
    final YamlFile ymlParams = StarRPCore.getInstance().getYmlparams();
    public List<String> params = RPParams.getParams();
    @NotNull FileConfiguration config = StarRPCore.getInstance().getConfig();

    @Override
    public void execute(CommandSender sender, String[] s) {
        String nick = s[0];
        CommandSender cs = (CommandSender) Bukkit.getOfflinePlayer(nick);
        List<String> format = config.getStringList("RPParams.format");
        final Component[] msg = new Component[1];

        new BukkitRunnable() {
            @Override
            public void run() {
                String[] s = new String[0];
                for (String str : format) {
                    str = RPParams.getParamToString(nick, str);
                    msg[0] = Utils.Placeholders(str, cs, "0", s, "0");
                    sender.sendMessage(msg[0]);
                }
            }
        }.runTaskAsynchronously(StarRPCore.getInstance());
    }
}
