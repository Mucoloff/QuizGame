package dev.sweety.quizgame.commands.api.impl;

import dev.sweety.quizgame.commands.api.Command;
import dev.sweety.quizgame.commands.api.Sweety;
import dev.sweety.quizgame.files.Config;
import dev.sweety.quizgame.utils.models.Answer;
import dev.sweety.quizgame.utils.models.Category;
import dev.sweety.quizgame.utils.models.Game;
import dev.sweety.quizgame.utils.models.Question;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Sweety(name = "play", permission = "quizgame.play")
public class Play extends Command {

    HashMap<Player, Game> game = plugin.getGame();

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Config.message("notAPlayer"));
            return;
        }

        StringBuilder categories = new StringBuilder();
        List<String> names = Config.getNames();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            categories.append(name);
            if (i != names.size() - 1) categories.append(Config.string("separator"));
        }

        if (args.size() < 1) {
            p.sendMessage(Config.format("invalidCategory", categories.toString()));
            return;
        }

        String categotyName = args.get(0);

        Category category = Config.getCategories().stream().filter(c -> c.name().equalsIgnoreCase(categotyName)).findFirst().orElse(null);

        if (category == null) {
            p.sendMessage(Config.format("invalidCategory", categories.toString()));
            return;
        }

        game.remove(p);

        Question question = category.getRandom();

        game.put(p, new Game(p, category, question));

        Component message = (Config.format("format", category.category(), question.question()));

        List<Answer> answers = question.answers();
        for (int i = 0; i < answers.size(); i++) {
            Answer answer = answers.get(i);
            message = message.append(Config.component(answer.answer())
                    .clickEvent(ClickEvent.runCommand(String.format("/answer %s %s %s", category.name(), question.name(), answer.name())))
                    .hoverEvent(HoverEvent.showText((Config.format("hover",
                            category.name(),
                            category.category(),
                            question.name(),
                            question.question(),
                            answer.name(),
                            answer.answer())))
                    ));

            if (i != answers.size() - 1) message = message.append(Config.message("separator"));
        }

        p.sendMessage(message);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> tab = new ArrayList<>();
        for (Category category : Config.getCategories()) {
            tab.add(category.name());
        }
        return tab;
    }
}
