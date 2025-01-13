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
}
