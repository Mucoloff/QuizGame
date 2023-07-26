package dev.sweety.quizgame.commands.api.impl;

import dev.sweety.quizgame.commands.api.Command;
import dev.sweety.quizgame.commands.api.Sweety;
import dev.sweety.quizgame.files.Config;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@Sweety(name = "reload", permission = "quizgame.staff.reload")
public class Reload extends Command {

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Config.message("notAPlayer"));
            return;
        }

        Config.reload();
    }
}
