package dev.sweety.quizgame.utils.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public final class Question {
    private final String question;
    private final List<Answer> answers;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question(String question, Answer... answer) {
        this(question, new ArrayList<>(Arrays.asList(answer)));
    }


}
