package dev.sweety.quizgame.commands.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Sweety {
    String name();

    String permission();

    String noPermissionMessage() default "&7Running &bFFACore &a1.0 &7by &cSweetyDreams_";

    String noSubCommandFoundMessage() default "&cComando inesistente";

}
