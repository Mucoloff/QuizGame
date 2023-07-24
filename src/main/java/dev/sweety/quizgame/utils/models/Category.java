package dev.sweety.quizgame.utils.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Category {

    private final String name;
    private final Set<String> questions;

    public Category(String name, String... questions) {
        this.name = name;
        this.questions = new HashSet<>(Arrays.asList(questions));
    }

    public Category(String name, List<String> questions) {
        this.name = name;
        this.questions = new HashSet<>(questions);
    }

}
