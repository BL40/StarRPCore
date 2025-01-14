package dev.bronzylobster.starrpcore.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static int getNumber (String sNum, CommandSender p) throws NumberFormatException{
        try {
            return NumberUtils.createInteger(sNum);
        } catch (NumberFormatException e) {
            p.sendMessage(Component.text("Value is not number").color(TextColor.color(0xFF0000)).clickEvent(ClickEvent.openUrl("https://www.youtube.com/watch?v=L1YkydtyTt8")));
            return 0;
        }
    }

    public static double getDouble (String sNum, CommandSender p) throws NumberFormatException{
        try {
            return NumberUtils.createDouble(sNum);
        } catch (NumberFormatException e) {
            p.sendMessage(Component.text("Value is not number").color(TextColor.color(0xFF0000)).clickEvent(ClickEvent.openUrl("https://www.youtube.com/watch?v=L1YkydtyTt8")));
            return 0;
        }
    }

    public static Boolean getBool (String sBool, CommandSender p) throws NumberFormatException{
        if (sBool.equalsIgnoreCase("true")) {
            return true;
        } else if (sBool.equalsIgnoreCase("false")) {
            return false;
        } else {
            p.sendMessage(Component.text("Value is not number").color(TextColor.color(0xFF0000)).clickEvent(ClickEvent.openUrl("https://www.youtube.com/watch?v=L1YkydtyTt8")));
            return  null;
        }
    }

    public static long parseTime(String timeString) {
        long totalTicks = 0;
        Pattern pattern = Pattern.compile("(\\d+)([smhdwM])");
        Matcher matcher = pattern.matcher(timeString);

        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            String unit = matcher.group(2);

            switch (unit) {
                case "s":
                    totalTicks += value * 20L;
                    break;
                case "m":
                    totalTicks += value * 1200L;
                    break;
                case "h":
                    totalTicks += value * 72000L;
                    break;
                case "d":
                    totalTicks += value * 1728000L;
                    break;
                case "w":
                    totalTicks += value * 12096000L;
                    break;
                case "M":
                    totalTicks += value * 518400000L;
                    break;
                default:
                    return -1;
            }
        }

        return totalTicks;
    }
}
