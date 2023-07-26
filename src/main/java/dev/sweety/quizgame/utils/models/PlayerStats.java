package dev.sweety.quizgame.utils.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PlayerStats {

    private UUID uuid;
    private Double score;

    public PlayerStats increment(double i) {
        score += i;
        return this;
    }
}
