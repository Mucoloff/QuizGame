package dev.sweety.quizgame.commands.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandHandler {

    private static final HashMap<Command, List<Subcommand>> commands = new HashMap<>();

    public static void addSubCommand(Command parent, Subcommand command) {
        List<Subcommand> subCommands = commands.containsKey(parent) ? commands.get(parent) : new ArrayList<>();

        subCommands.add(command);
        commands.put(parent, subCommands);
    }

    public static void addSubCommands(Command parent, Subcommand ...command) {
        List<Subcommand> subCommands = commands.containsKey(parent) ? commands.get(parent) : new ArrayList<>();

        subCommands.addAll(Arrays.asList(command));
        commands.put(parent, subCommands);
    }

    public static List<Subcommand> getSubCommands(Command parent) {
        if (!commands.containsKey(parent)) return new ArrayList<>();
        return commands.get(parent);
    }

    public static <T extends Command> Command getCommand(Class<T> clazz) {
        return commands.keySet().stream().filter(c -> c.getClass() == clazz).findAny().orElse(null);
    }

    public static <T extends Subcommand> Subcommand getSubCommand(Command c, Class<T> clazz) {
        return commands.get(c).stream().filter(s -> s.getClass() == clazz).findAny().orElse(null);
    }


    public static void removeSubCommand(Command parent, Subcommand command) {
        if (!commands.containsKey(parent)) return;

        List<Subcommand> subCommands = commands.get(parent);
        subCommands.remove(command);

        commands.put(parent, subCommands);
    }

    public static void removeSubCommands(Command parent, Subcommand... command) {
        if (!commands.containsKey(parent)) return;

        List<Subcommand> subCommands = commands.get(parent);
        subCommands.removeAll(Arrays.asList(command));

        commands.put(parent, subCommands);
    }

}