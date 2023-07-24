package dev.sweety.quizgame.files;

import dev.sweety.quizgame.QuizGame;
import dev.sweety.quizgame.utils.models.Category;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;


public class Config {

    public static FileConfiguration config;
    @Getter private static final Set<Category> categories = new HashSet<>();

    public static void init() {
        config = QuizGame.getInstance().getConfig();
        config
                .getStringList("categories")
                .forEach(category -> categories
                        .add(new Category(category, config
                                .getStringList("categories." + category))));
    }

}
