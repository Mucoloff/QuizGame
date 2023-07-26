package dev.sweety.quizgame.utils.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class PlayerStats {

    private UUID uuid;
    private Double score;

    public PlayerStats increment(double i) {
        score += i;
        return this;
    }
}
