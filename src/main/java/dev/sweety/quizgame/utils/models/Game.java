package dev.sweety.quizgame.utils.models;

import org.bukkit.entity.Player;

public record Game(Player user, Category category, Question question) {
}