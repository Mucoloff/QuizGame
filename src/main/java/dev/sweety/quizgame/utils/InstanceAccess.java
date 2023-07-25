package dev.sweety.quizgame.utils;

import dev.sweety.quizgame.QuizGame;
import dev.sweety.quizgame.utils.db.DatabaseManager;
import dev.sweety.quizgame.utils.db.StatsManager;

public interface InstanceAccess {
    QuizGame plugin = QuizGame.getInstance();

    DatabaseManager database = QuizGame.getInstance().getDatabaseManager();
    StatsManager playerstats = QuizGame.getInstance().getStatsManager();

}
