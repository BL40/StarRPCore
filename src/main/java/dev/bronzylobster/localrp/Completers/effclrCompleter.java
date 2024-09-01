package dev.bronzylobster.localrp.Completers;

import dev.bronzylobster.localrp.Utils.DataBase;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class effclrCompleter implements TabCompleter {

    DataBase db;
    {
        try {
            db = new DataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        ArrayList<String> encants = null;

        if (args.length == 1) {
            encants = new ArrayList<>();
            for (Player p:Bukkit.getOnlinePlayers()) {
                encants.add(p.getName());
            }
        } else if (args.length == 2) {
            encants = db.getEffects(args[0]);
        }

        return encants;
    }
}
