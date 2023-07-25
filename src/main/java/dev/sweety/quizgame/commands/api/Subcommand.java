package dev.sweety.quizgame.commands.api;

import dev.sweety.quizgame.utils.InstanceAccess;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class Subcommand implements InstanceAccess {

    @Getter
    @Setter
    private String name, permission, parent;

    public Subcommand() {
        if (getClass().isAnnotationPresent(Register.class)) {
            Register register = this.getClass().getAnnotation(Register.class);
            this.name = register.name();
            this.permission = register.permission();
            this.parent = register.parent();
        } else {
            throw new RuntimeException("Register annotation not found on " + this.getClass().getSimpleName());
        }
    }

    public Subcommand(String parent, String name, String permission) {
        this.parent = parent;
        this.name = name;
        this.permission = permission;
    }

    public abstract void execute(CommandSender sender, List<String> args);

}