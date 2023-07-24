package dev.sweety.quizgame;

import dev.sweety.quizgame.files.Config;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class QuizGame extends JavaPlugin {

    @Getter private static QuizGame instance;

    @Override
    public void onEnable() {
        instance = this;
        Config.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
