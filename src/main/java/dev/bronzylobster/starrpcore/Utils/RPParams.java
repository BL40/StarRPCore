package dev.bronzylobster.starrpcore.Utils;

import dev.bronzylobster.starrpcore.StarRPCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.simpleyaml.configuration.file.YamlFile;

import java.util.ArrayList;
import java.util.List;

public class RPParams {

    static FileConfiguration config = StarRPCore.getInstance().getConfig();
    final static YamlFile ymlParams = StarRPCore.getInstance().getYmlparams();
    private static List<String> params = new ArrayList<>();
    public static  void setDefaultParams() {
        params = config.getStringList("RPParams.parameters");
    }

    public static List<String> getParams() {
        setDefaultParams();
        return params;
    }
    
    public static void genParams(String nick) {
        setDefaultParams();
        if (!ymlParams.contains(nick)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (String par : params) {
                        ymlParams.set(nick + "." + par, "null");
                    }

                    try {
                        ymlParams.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(StarRPCore.getInstance());
        }
    }

    public static String getParamToString(String nick, String parString) {
        setDefaultParams();
        String result;

        try {
            ymlParams.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = parString.replace("%player%", nick);
        for (String par : params) {
            result = result.replace("%" + par + "%", ymlParams.getString(nick + "." + par));
        }

        return result;
    }
}
