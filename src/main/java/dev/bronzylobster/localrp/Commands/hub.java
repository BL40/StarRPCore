package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.LocalRP;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hub extends AbstractCommand{

    public hub() {super("hub");};

    @Override
    public void execute(CommandSender sender, String[] s) {
        Bukkit.getServer().dispatchCommand(sender, "server lobby");
    }
}
