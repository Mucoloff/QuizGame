package dev.sweety.quizgame.utils;

import dev.sweety.quizgame.QuizGame;

public interface InstanceAccess {
    QuizGame plugin = QuizGame.getInstance();
}