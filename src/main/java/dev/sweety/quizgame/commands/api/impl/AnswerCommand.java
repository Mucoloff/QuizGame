package dev.sweety.quizgame.commands.api.impl;

import dev.sweety.quizgame.commands.api.Command;
import dev.sweety.quizgame.commands.api.Sweety;
import dev.sweety.quizgame.files.Config;
import dev.sweety.quizgame.utils.db.StatsManager;
import dev.sweety.quizgame.utils.models.Answer;
import dev.sweety.quizgame.utils.models.Category;
import dev.sweety.quizgame.utils.models.Game;
import dev.sweety.quizgame.utils.models.Question;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Sweety(name = "answer", permission = "quizgame.play")
public class AnswerCommand extends Command {

    StatsManager playerstats = plugin.getStatsManager();
    HashMap<Player, Game> game = plugin.getGame();

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Config.message("notAPlayer"));
            return;
        }

        if (!game.containsKey(p)) {
            sender.sendMessage(Config.message("startGame"));
            return;
        }

        if (args.size() < 3) {
            p.sendMessage(Config.message("invalidAnswer"));
            return;
        }

        String categoryName = args.get(0);
        String questionName = args.get(1);
        String answerName = args.get(2);

        Game game = this.game.get(p);

        Category categoryByName = Config.getCategories().stream().filter(c -> c.name().equalsIgnoreCase(categoryName)).findFirst().orElse(null);
        Category categoryByGame = game.category();

        if (categoryByName == null || !categoryByName.equals(categoryByGame)) {
            p.sendMessage(Config.message("invalidAnswer"));
            return;
        }

        Question questionByName = categoryByGame.questions().stream().filter(q -> q.name().equalsIgnoreCase(questionName)).findFirst().orElse(null);
        Question questionByGame = game.question();

        if (questionByName == null || !questionByName.equals(questionByGame)) {
            p.sendMessage(Config.message("invalidAnswer"));
            return;
        }

        Answer answer = questionByGame.answers().stream().filter(a -> a.name().equalsIgnoreCase(answerName)).findFirst().orElse(null);

        if (answer == null) {
            p.sendMessage(Config.message("invalidAnswer"));
            return;
        }

        Double points = answer.points();

        playerstats.addScore(p.getUniqueId(), points);

        p.sendMessage(Config.format("points", Config.roundTo(points)));

        this.game.remove(p);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!(sender instanceof Player p) || !game.containsKey(p)) return null;
        Game game = this.game.get(p);
        Category category = game.category();
        if (args.length == 1) return List.of(category.name());

        Question question = game.question();

        if (args.length == 2) {
            return List.of(question.name());
        }

        if (args.length == 3) {
            List<String> answers = new ArrayList<>();
            question.answers().forEach(a -> answers.add(a.name()));
            return answers;
        }

        return null;
    }
}