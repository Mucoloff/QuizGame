package dev.sweety.quizgame.commands.api.impl;

import dev.sweety.quizgame.commands.api.Command;
import dev.sweety.quizgame.commands.api.Sweety;
import dev.sweety.quizgame.files.Config;
import dev.sweety.quizgame.utils.db.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static dev.sweety.quizgame.files.Config.roundTo;

@Sweety(name = "score", permission = "quizgame.score")
public class Score extends Command {

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Config.message("notAPlayer"));
            return;
        }
        Player t = args.size() >= 1 ? Bukkit.getPlayer(args.get(0)) : p;

        if (t == null){
            p.sendMessage(Config.message("invalidPlayer"));
            return;
        }

        StatsManager playerstats = plugin.getStatsManager();

        Double score = playerstats.getStatsByUUID(t.getUniqueId()).getScore();


        p.sendMessage(Config.format("showPoint", t.getName(), roundTo(score)));
    }
}
