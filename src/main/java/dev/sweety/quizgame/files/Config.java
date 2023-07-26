package dev.sweety.quizgame.files;

import dev.sweety.quizgame.utils.InstanceAccess;
import dev.sweety.quizgame.utils.models.Answer;
import dev.sweety.quizgame.utils.models.Category;
import dev.sweety.quizgame.utils.models.Question;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Config implements InstanceAccess {

    public static FileConfiguration config;
    @Getter private static final List<Category> categories = new ArrayList<>();
    @Getter private static final List<String> names = new ArrayList<>();

    public static void init() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();

        reloadCategories();
    }

    private static void reloadCategories() {
        categories.clear();
        names.clear();

        for (String cName : config.getStringList("categories.list")) {
            String category = config.getString(String.format("categories.%s.category", cName));
            List<Question> questions = new ArrayList<>();
            for (String qName : config.getStringList(String.format("categories.%s.questions.list", cName))) {
                String question = config.getString(String.format("categories.%s.questions.%s.question", cName, qName));
                List<Answer> answers = new ArrayList<>();
                for (String aName : config.getStringList(String.format("categories.%s.questions.%s.answers.list", cName, qName))) {
                    String answer = config.getString(String.format("categories.%s.questions.%s.answers.%s.answer", cName, qName, aName));
                    Double points = config.getDouble(String.format("categories.%s.questions.%s.answers.%s.points", cName, qName, aName));
                    answers.add(new Answer(aName, answer, points));
                }
                questions.add(new Question(qName, question, answers));
            }
            categories.add(new Category(cName, category, questions));
            names.add(cName);
        }
    }

    public static void reload() {
        plugin.saveConfig();
        plugin.reloadConfig();

        reloadCategories();
    }

    public static @NotNull Component component(String message) {
        return Component.text(color(message));
    }

    public static @NotNull Component message(String path) {
        return component(config.getString("messages." + path));
    }


    public static String string(String path) {
        return color(config.getString("messages." + path));
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

    public static int random(int size) {
        return Math.abs(new Random().nextInt()) % size;
    }

    @NotNull
    public static Component format(String path, Object... replacements) {
        String format = config.getString("messages." + path);
        if (format == null) return Component.empty();
        for (int i = 0; i < replacements.length; i++) {
            format = format.replace("%" + i + "%", replacements[i].toString());
        }
        return Component.text(color(format));
    }

    public static Double roundTo(Double points) {
        return new BigDecimal(points).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
