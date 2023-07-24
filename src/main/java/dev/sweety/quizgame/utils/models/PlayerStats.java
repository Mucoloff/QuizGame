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
    private Integer score;
}
