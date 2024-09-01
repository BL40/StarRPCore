package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.Completers.infencCompleter;
import dev.bronzylobster.localrp.Utils.DataBase;
import dev.bronzylobster.localrp.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class infeff extends AbstractCommand{

    DataBase db;
    {
        try {
            db = new DataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public infeff(){super("infeff", new infencCompleter());}

    @Override
    public void execute(CommandSender sender, String[] s) {
        Player p = Bukkit.getPlayer(s[0]);
        if (p == null) {
            sender.sendMessage("Player " + s[0] + " is not exists");
            return;
        }

        PotionEffectType eff = PotionEffectType.getByName(s[1]);
        if (eff == null) {
            sender.sendMessage("Effect " + s[1] + " is not exists");
            return;
        }

        int lvl = Utils.getNumber(s[2], sender);
        PotionEffect peff = new PotionEffect(eff, -1, lvl, true, true, true);
        p.addPotionEffect(peff);
        db.add(s[0], s[1], lvl);
    }
}
