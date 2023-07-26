package dev.sweety.quizgame.utils.models;

import dev.sweety.quizgame.files.Config;

import java.util.List;

public record Category(String name, String category, List<Question> questions) {

    public Question getRandom() {
        return questions.get(Config.random(questions.size()));
    }

}
