package dev.bronzylobster.starrpcore;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import dev.bronzylobster.starrpcore.Utils.DataBase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Eff implements Listener {

    DataBase db;

    {
        try {
            db = new DataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void dead(PlayerPostRespawnEvent e) {
        Player p = e.getPlayer();
        ArrayList<String> effe = db.getEffects(p.getName());

        if (!(effe == null || effe.isEmpty())) {
            for (String eff : effe) {
            int lvl = db.getEffectLVL(p.getName(), eff);
            p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(eff), -1, lvl, true, false, true));
            }
        }
    }

}

