package dev.sweety.quizgame;

import dev.sweety.quizgame.commands.api.ReflectionUtil;
import dev.sweety.quizgame.files.Config;
import dev.sweety.quizgame.utils.Logo;
import dev.sweety.quizgame.utils.db.DatabaseManager;
import dev.sweety.quizgame.utils.db.StatsManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import static dev.sweety.quizgame.files.Config.config;

@Getter
public final class QuizGame extends JavaPlugin {

    @Getter private static QuizGame instance;

    private DatabaseManager databaseManager;
    private StatsManager statsManager;

    @Override
    public void onEnable() {
        instance = this;
        Config.init();

        String host = config.getString("database.host");
        int port = config.getInt("database.port");
        String database = config.getString("database.name");
        String username = config.getString("database.username");
        String password = config.getString("database.password");

        databaseManager = new DatabaseManager(host,port,database,username,password);
        databaseManager.connect();
        databaseManager.createUsersTable();

        statsManager = new StatsManager(databaseManager);


        getLogger().info(Logo.logo("QuizGame","Enable","1.0.4"));

        ReflectionUtil.register("dev.sweety.quizgame.commands.imlp");
    }

    @Override
    public void onDisable() {
        databaseManager.close();
        getLogger().info(Logo.logo("QuizGame","Disable","1.0.4"));
    }
}
