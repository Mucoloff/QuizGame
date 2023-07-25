package dev.sweety.quizgame.utils.models;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Category {

    private final String name;
    private final List<Question> questions;
    private final Map<Question, List<Answer>> composition;

    public Category(String name, Question... questions) {
        this(name, new ArrayList<>(Arrays.asList(questions)));
    }

    public Category(String name, List<Question> questions) {
        this.name = name;
        this.questions = new ArrayList<>(questions);
        composition = new HashMap<>();
        questions.forEach(q -> composition.put(q,q.getAnswers()));
    }


}
