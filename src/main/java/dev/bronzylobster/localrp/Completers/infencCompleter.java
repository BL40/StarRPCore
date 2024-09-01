package dev.bronzylobster.localrp.Completers;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class infencCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        ArrayList<String> encants = new ArrayList<String>();

        if (args.length == 1) {
            for (Player p: Bukkit.getOnlinePlayers()) {
                encants.add(p.getName());
            }
        } else if (args.length == 2) {
            final @NotNull PotionEffectType[] eff = PotionEffectType.values();
            for (PotionEffectType effect: eff) {
                encants.add(effect.getName());
            }
        } else if (args.length == 3) {
            encants.add("level");
        }

        return encants;
    }
}
