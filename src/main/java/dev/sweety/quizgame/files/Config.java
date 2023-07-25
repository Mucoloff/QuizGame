package dev.sweety.quizgame.files;

import dev.sweety.quizgame.utils.InstanceAccess;
import dev.sweety.quizgame.utils.models.Answer;
import dev.sweety.quizgame.utils.models.Category;
import dev.sweety.quizgame.utils.models.Question;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Config implements InstanceAccess {

    public static FileConfiguration config;
    @Getter
    private static final List<Category> categories = new ArrayList<>();

    public static void init() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        test();

        config.getStringList("categories")
                .forEach(category -> categories
                        .add(new Category(category, getQuestionList(("categories." + category)))));
    }

    private static void test() {

        List<Answer> ans1 = List.of(new Answer("si", 1),
                new Answer("no", 0),
                new Answer("sussolino (si)", 104));

        List<Answer> ans2 = List.of(new Answer("si", 0),
                new Answer("no", 10));

        List<Question> q1 = List.of(new Question("sei frocio?",
                        ans1
                ),
                new Question("sei un dev?",
                        ans1
                ));

        List<Question> q2 = List.of(new Question("sei frocio?",
                        ans2
                ),
                new Question("sei un dev?",
                        ans2
                ));

        List<Category> c1 = List.of(
                new Category("sussolino",
                        q1
                ),
                new Category("sweety",
                        q2
                )
        );



        //todo fai a mano


        List<String> c2 = config.getStringList("categories");
        for (Category c : c1){

        }


        reload();
    }

    public static void reload() {
        plugin.saveConfig();
        plugin.reloadConfig();
    }

    @NotNull
    public static List<Question> getQuestionList(@NotNull String path) {
        List<?> list = config.getList(path);
        if (list == null) {
            return new ArrayList<>(0);
        }
        return list.stream().filter(object -> object instanceof Question).map(object -> (Question) object).collect(Collectors.toList());
    }

    public static @NotNull TextComponent component(String path) {
        return Component.text(path);
    }


    public static String string(String path) {
        return color(config.getString(path));
    }



    public static String color(String message) {
        final char colorChar = '§';
        message = message.replace('&', colorChar);
        final Matcher matcher = Pattern.compile("&#([A-Fa-f\\d]{6})").matcher(message);
        final StringBuilder buffer = new StringBuilder(message.length() + 4 * 8);
        while (matcher.find()) {
            final String group = matcher.group(1);
            matcher.appendReplacement(buffer, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }
        return matcher.appendTail(buffer).toString();
    }

}
