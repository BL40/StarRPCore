package dev.bronzylobster.localrp;

import dev.bronzylobster.localrp.Commands.*;
import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public final class LocalRP extends JavaPlugin {

    @Getter
    private static LocalRP instance;
    private final Scoreboard scoreboard = getServer().getScoreboardManager().getMainScoreboard();
    @Getter
    private Team team;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        new locme();
        new locdo();
        new loctry();
        new locroll();
        new fix();

        // Plugin startup logic

        if (scoreboard.getTeam("hide") == null) {
            scoreboard.registerNewTeam("hide");
            team = scoreboard.getTeam("hide");
            team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        } else {
            team = scoreboard.getTeam("hide");
        }

        getServer().getPluginManager().registerEvents(new HideNames(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
