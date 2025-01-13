package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.Completers.effclrCompleter;
import dev.bronzylobster.localrp.Utils.DataBase;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class effclr extends AbstractCommand{

    DataBase db;
    {
        try {
            db = new DataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public effclr(){super("effclr", new effclrCompleter());}


    @Override
    public void execute(CommandSender sender, String[] s) {
        int len = s.length;
        Player p = Bukkit.getPlayer(s[0]);
        assert p != null;
        if (len == 1) {
            ArrayList<String> effs = db.getEffects(s[0]);

            for (String eff: effs) {
                p.removePotionEffect(PotionEffectType.getByName(eff));
            }

            db.remove(s[0]);
            sender.sendMessage("C игрока " + s[0] + " сняты все вечные эффекты");
        } else if (len == 2){
            db.remove(s[0], s[1]);
            p.removePotionEffect(PotionEffectType.getByName(s[1]));
            sender.sendMessage("C игрока " + s[0] + " снят вечный эффект " + s[1]);
        }
    }
}
