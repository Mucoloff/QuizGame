package dev.sweety.quizgame.commands.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Register {
    String name();

    String permission();

    String parent();

}
