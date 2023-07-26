package dev.bronzylobster.localrp;

import dev.bronzylobster.localrp.Commands.locdo;
import dev.bronzylobster.localrp.Commands.locme;
import dev.bronzylobster.localrp.Commands.loctry;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class LocalRP extends JavaPlugin {

    @Getter
    private static LocalRP instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        new locme();
        new locdo();
        new loctry();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
