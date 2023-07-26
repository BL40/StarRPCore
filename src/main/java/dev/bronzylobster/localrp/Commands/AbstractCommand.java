package dev.bronzylobster.localrp.Commands;

import dev.bronzylobster.localrp.LocalRP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCommand implements CommandExecutor {

    public AbstractCommand (String name) {
        LocalRP.getInstance().getCommand(name).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        execute(commandSender, strings);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] s);
}
