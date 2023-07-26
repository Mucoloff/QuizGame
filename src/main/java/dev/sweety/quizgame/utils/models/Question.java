package dev.sweety.quizgame.utils.models;

import java.util.List;

public record Question(String name, String question, List<Answer> answers) {
}