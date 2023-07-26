package dev.bronzylobster.localrp.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Utils {
    public static Component Placeholders (String msg, CommandSender cs, String result, String[] s){
        Player p = (Player) cs;

        Component cMSG = LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
        Component name = p.displayName();
        Component cResult = LegacyComponentSerializer.legacyAmpersand().deserialize(result);
        Component crMSG = messageParser(s);

        MiniMessage minimessage = MiniMessage.builder()
                .tags(TagResolver.builder()
                        .resolver(StandardTags.color())
                        .resolver(StandardTags.decorations())
                        .build()
                )
                .build();
        String sMSG = minimessage.serialize(cMSG);
        String sName = minimessage.serialize(name);
        String sResult = minimessage.serialize(cResult);
        String srMSG = minimessage.serialize(crMSG);

        sMSG = sMSG
                .replace("%player%", sName)
                .replace("%result%", sResult)
                .replace("%Message%", srMSG);

        return minimessage.deserialize(sMSG);
    }

    public static Component messageParser (String[] s){
        String[] ss = (String[]) Arrays.stream(s).skip(1).toArray();
        String msg = String.join(" ", ss);
        return LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
    };
}
