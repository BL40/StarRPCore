package dev.bronzylobster.starrpcore;

import dev.bronzylobster.starrpcore.Commands.*;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.simpleyaml.configuration.file.YamlFile;

public final class StarRPCore extends JavaPlugin {

    @Getter
    private static StarRPCore instance;
    private Scoreboard scoreboard;
    @Getter
    private Team team;
    @Getter
    private YamlFile ymlparams;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        ymlparams = new YamlFile("LocalRP/parameters.yml");
        try {
            if (!ymlparams.exists()) {
                ymlparams.createNewFile();
            }
            ymlparams.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new locme();
        new gme();
        new locdo();
        new gdo();
        new loctry();
        new locroll();
        new fix();
        new pinfo();

        scoreboard = getServer().getScoreboardManager().getMainScoreboard();

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
