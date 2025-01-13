package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.Completers.gravityCompleter;
import dev.bronzylobster.localrp.StarRPCore;
import dev.bronzylobster.localrp.Utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;

public class gravity extends AbstractCommand{

    public gravity(){super("gravity", new gravityCompleter());}

    FileConfiguration config = StarRPCore.getInstance().getConfig();
    @Override
    public void execute(CommandSender sender, String[] s) {
        Boolean grav = Utils.getBool(s[0], sender);
        assert grav != null;
        Player pSender = (Player) sender;

        Collection<? extends Entity> entities = pSender.getWorld().getEntities();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Entity e: entities) {
                    if (!e.getType().equals(EntityType.ARMOR_STAND)) {
                        e.setGravity(grav);
                        if (e.getType().equals(EntityType.PLAYER)) {
                            Player p = (Player) e;
                            p.setAllowFlight(!grav);
                            p.setFlying(!grav);
                            if (grav) {
                                p.setFlySpeed((float) config.getDouble("DefaultSpeed"));
                            } else {
                                p.setFlySpeed((float) config.getDouble("GravitySpeed"));
                            }
                        }
                    }
                }
            }
        }.runTaskAsynchronously(StarRPCore.getInstance());
    }
}
