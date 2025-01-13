package dev.bronzylobster.starrpcore.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class MessageManager {

    private final JavaPlugin plugin;
    private final Map<String, String> messages = new HashMap<>();

    public MessageManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadMessages();
    }

    private void loadMessages() {
        FileConfiguration config = plugin.getConfig();
        for (String key : config.getConfigurationSection("messages").getKeys(false)) {
            messages.put(key, config.getString("messages." + key));
        }
    }

    public String getPlaceholders(String key, Map<String, String> placeholders) {
        String message = messages.getOrDefault(key, "&cСообщение не найдено: " + key);

        // Заменяем переменные в сообщении
        if (placeholders != null) {
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                message = message.replace("%" + entry.getKey() + "%", entry.getValue());
            }
        }
        return message;
    }

    public String getPlaceholders(Player p, String key, Map<String, String> placeholders) {
        String message = messages.getOrDefault(key, "&cСообщение не найдено: " + key);

        // Заменяем переменные в сообщении
        if (placeholders != null) {
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                message = message.replace("%" + entry.getKey() + "%", entry.getValue());
            }
        }

        message = PlaceholderAPI.setPlaceholders(p, message);

        return message;
    }

    public Component messageToComponent(String message) {

        MiniMessage minimessage = MiniMessage.builder()
                .tags(TagResolver.builder()
                        .resolver(StandardTags.defaults())
                        .resolver(StandardTags.decorations())
                        .resolver(StandardTags.clickEvent())
                        .resolver(StandardTags.font())
                        .resolver(StandardTags.color())
                        .resolver(StandardTags.hoverEvent())
                        .resolver(StandardTags.insertion())
                        .resolver(StandardTags.keybind())
                        .resolver(StandardTags.nbt())
                        .resolver(StandardTags.newline())
                        .build()
                )
                .build();

        Component cMSG = LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        String sMSG = minimessage.serialize(cMSG);

        return minimessage.deserialize(sMSG);
    }

    public Component messageParser (String[] s){
        String msg = String.join(" ", s);
        return messageToComponent(msg);
    }

    public String messageParserToString (String[] s){
        String msg = String.join(" ", s);
        return msg;
    }
}
