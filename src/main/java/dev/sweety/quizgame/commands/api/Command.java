package dev.sweety.quizgame.commands.api;


import dev.sweety.quizgame.files.Config;
import dev.sweety.quizgame.utils.InstanceAccess;
import lombok.Getter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public abstract class Command implements CommandExecutor, TabExecutor, InstanceAccess {

    @Getter
    private final String name, permission;
    @Getter
    private String noPermissionMessage, noSubCommandFoundMessage;

    public Command() {
        if (getClass().isAnnotationPresent(Sweety.class)) {
            Sweety sweety = this.getClass().getAnnotation(Sweety.class);
            this.name = sweety.name();
            this.permission = sweety.permission();
            this.noPermissionMessage = sweety.noPermissionMessage();
            this.noSubCommandFoundMessage = sweety.noSubCommandFoundMessage();
        } else {
            throw new RuntimeException("Sweety annotation not found on " + this.getClass().getSimpleName());
        }
    }

    public Command(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }

    public void registerExecutor() {
        Objects.requireNonNull(getCommand(name, plugin)).setExecutor(this);
        Objects.requireNonNull(getCommand(name, plugin)).setTabCompleter(this);
    }

    public void addSubCommands(Subcommand... command) {
        CommandHandler.addSubCommands(this, command);
    }

    public void removeSubCommands(Subcommand... command) {
        CommandHandler.removeSubCommands(this, command);
    }


    @Nullable
    public PluginCommand getCommand(@NotNull String name, Plugin pl) {
        String alias = name.toLowerCase(java.util.Locale.ENGLISH);
        PluginCommand command = pl.getServer().getPluginCommand(alias);

        if (command == null || command.getPlugin() != pl) {
            command = pl.getServer().getPluginCommand(pl.getDescription().getName().toLowerCase(java.util.Locale.ENGLISH) + ":" + alias);
        }

        if (command != null && command.getPlugin() == pl) {
            return command;
        } else {
            return null;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1)
            return CommandHandler.getSubCommands(this).stream().map(Subcommand::getName).collect(Collectors.toList());
        return null;
    }

    public abstract void execute(CommandSender sender, List<String> args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, String[] args) {

        if (permission != null && !sender.hasPermission(permission) && noPermissionMessage != null) {
            sender.sendMessage(Config.color(noPermissionMessage));
            return true;
        }

        List<String> argList = Arrays.asList(args);
        if (args.length > 0 && CommandHandler.getSubCommands(this).size() > 0) {
            List<Subcommand> subcommands = CommandHandler.getSubCommands(this);
            Subcommand subcommand = subcommands.stream().filter(sub -> sub.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);

            if (subcommand == null) {
                if (noSubCommandFoundMessage != null) {
                    sender.sendMessage(Config.color(noSubCommandFoundMessage));
                }
                return true;
            }


            subcommand.execute(sender, argList);
        } else execute(sender, argList);
        return true;
    }

}
