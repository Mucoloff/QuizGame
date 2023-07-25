package dev.sweety.quizgame.utils;

public class Logo {

    public static String logo(String plugin, String status, String version) {
        return print( plugin + " ",
                "\u001b[34;1mStatus : " + status,
                "\u001b[34;1mVersion: " + version
        );
    }

    private static String print(String text, String add1, String add2) {
        StringBuilder topppp = new StringBuilder("\u001b[31m");
        StringBuilder center = new StringBuilder("\u001b[31m");
        StringBuilder bottom = new StringBuilder("\u001b[31m");

        for (char c : text.toCharArray()) {
            switch (c) {
                case 'A' -> {
                    topppp.append("╔═╗");
                    center.append("╠═╣");
                    bottom.append("╩ ╩");
                }
                case 'a' -> {
                    topppp.append("┌─┐");
                    center.append("├─┤");
                    bottom.append("┴ ┴");
                }
                case 'B' -> {
                    topppp.append("╔╗ ");
                    center.append("╠╩╗");
                    bottom.append("╚═╝");
                }
                case 'b' -> {
                    topppp.append("┌┐ ");
                    center.append("├┴┐");
                    bottom.append("└─┘");
                }
                case 'C' -> {
                    topppp.append("╔═╗");
                    center.append("║  ");
                    bottom.append("╚═╝");
                }
                case 'c' -> {
                    topppp.append("┌─┐");
                    center.append("│  ");
                    bottom.append("└─┘");
                }
                case 'D' -> {
                    topppp.append("╔╦╗");
                    center.append(" ║║");
                    bottom.append("═╩╝");
                }
                case 'd' -> {
                    topppp.append("┌┬┐");
                    center.append(" ││");
                    bottom.append("─┴┘");
                }
                case 'E' -> {
                    topppp.append("╔═╗");
                    center.append("║╣ ");
                    bottom.append("╚═╝");
                }
                case 'e' -> {
                    topppp.append("┌─┐");
                    center.append("├┤ ");
                    bottom.append("└─┘");
                }
                case 'F' -> {
                    topppp.append("╔═╗");
                    center.append("╠╣ ");
                    bottom.append("╚  ");
                }
                case 'f' -> {
                    topppp.append("┌─┐");
                    center.append("├┤ ");
                    bottom.append("└  ");
                }
                case 'G' -> {
                    topppp.append("╔═╗");
                    center.append("║ ╦");
                    bottom.append("╚═╝");
                }
                case 'g' -> {
                    topppp.append("┌─┐");
                    center.append("│ ┬");
                    bottom.append("└─┘");
                }
                case 'H' -> {
                    topppp.append("╦ ╦");
                    center.append("╠═╣");
                    bottom.append("╩ ╩");
                }
                case 'h' -> {
                    topppp.append("┬ ┬");
                    center.append("├─┤");
                    bottom.append("┴ ┴");
                }
                case 'I' -> {
                    topppp.append("╦");
                    center.append("║");
                    bottom.append("╩");
                }
                case 'i' -> {
                    topppp.append("┬");
                    center.append("│");
                    bottom.append("┴");
                }
                case 'J' -> {
                    topppp.append(" ╦");
                    center.append(" ║");
                    bottom.append("╚╝");
                }
                case 'j' -> {
                    topppp.append(" ┬");
                    center.append(" │");
                    bottom.append("└┘");
                }
                case 'K' -> {
                    topppp.append("╦╔═");
                    center.append("╠╩╗");
                    bottom.append("╩ ╩");
                }
                case 'k' -> {
                    topppp.append("┬┌─");
                    center.append("├┴┐");
                    bottom.append("┴ ┴");
                }
                case 'L' -> {
                    topppp.append("╦  ");
                    center.append("║  ");
                    bottom.append("╩═╝");
                }
                case 'l' -> {
                    topppp.append("┬  ");
                    center.append("│  ");
                    bottom.append("┴─┘");
                }
                case 'M' -> {
                    topppp.append("╔╦╗");
                    center.append("║║║");
                    bottom.append("╩ ╩");
                }
                case 'm' -> {
                    topppp.append("┌┬┐");
                    center.append("│││");
                    bottom.append("┴ ┴");
                }
                case 'N' -> {
                    topppp.append("╔╗╔");
                    center.append("║║║");
                    bottom.append("╝╚╝");
                }
                case 'n' -> {
                    topppp.append("┌┐┌");
                    center.append("│││");
                    bottom.append("┘└┘");
                }
                case 'O' -> {
                    topppp.append("╔═╗");
                    center.append("║ ║");
                    bottom.append("╚═╝");
                }
                case 'o' -> {
                    topppp.append("┌─┐");
                    center.append("│ │");
                    bottom.append("└─┘");
                }
                case 'P' -> {
                    topppp.append("╔═╗");
                    center.append("╠═╝");
                    bottom.append("╩  ");
                }
                case 'p' -> {
                    topppp.append("┌─┐");
                    center.append("├─┘");
                    bottom.append("┴  ");
                }
                case 'Q' -> {
                    topppp.append("╔═╗ ");
                    center.append("║═╬╗");
                    bottom.append("╚═╝╚");
                }
                case 'q' -> {
                    topppp.append("┌─┐ ");
                    center.append("│─┼┐");
                    bottom.append("└─┘└");
                }
                case 'R' -> {
                    topppp.append("╦═╗");
                    center.append("╠╦╝");
                    bottom.append("╩╚═");
                }
                case 'r' -> {
                    topppp.append("┬─┐");
                    center.append("├┬┘");
                    bottom.append("┴└─");
                }
                case 'S' -> {
                    topppp.append("╔═╗");
                    center.append("╚═╗");
                    bottom.append("╚═╝");
                }
                case 's' -> {
                    topppp.append("┌─┐");
                    center.append("└─┐");
                    bottom.append("└─┘");
                }
                case 'T' -> {
                    topppp.append("╔╦╗");
                    center.append(" ║ ");
                    bottom.append(" ╩ ");
                }
                case 't' -> {
                    topppp.append("┌┬┐");
                    center.append(" │ ");
                    bottom.append(" ┴ ");
                }
                case 'U' -> {
                    topppp.append("╦ ╦");
                    center.append("║ ║");
                    bottom.append("╚═╝");
                }
                case 'u' -> {
                    topppp.append("┬ ┬");
                    center.append("│ │");
                    bottom.append("└─┘");
                }
                case 'V' -> {
                    topppp.append("╦  ╦");
                    center.append("╚╗╔╝");
                    bottom.append(" ╚╝ ");
                }
                case 'v' -> {
                    topppp.append("┬  ┬");
                    center.append("└┐┌┘");
                    bottom.append(" └┘ ");
                }
                case 'W' -> {
                    topppp.append("╦ ╦");
                    center.append("║║║");
                    bottom.append("╚╩╝");
                }
                case 'w' -> {
                    topppp.append("┬ ┬");
                    center.append("│││");
                    bottom.append("└┴┘");
                }
                case 'X' -> {
                    topppp.append("═╗ ╦");
                    center.append("╔╩╦╝");
                    bottom.append("╩ ╚═");
                }
                case 'x' -> {
                    topppp.append("─┐ ┬");
                    center.append("┌┴┬┘");
                    bottom.append("┴ └─");
                }
                case 'Y' -> {
                    topppp.append("╦ ╦");
                    center.append("╚╦╝");
                    bottom.append(" ╩ ");
                }
                case 'y' -> {
                    topppp.append("┬ ┬");
                    center.append("└┬┘");
                    bottom.append(" ┴ ");
                }
                case 'Z' -> {
                    topppp.append("╔═╗");
                    center.append("╔═╝");
                    bottom.append("╚═╝");
                }
                case 'z' -> {
                    topppp.append("┌─┐");
                    center.append("┌─┘");
                    bottom.append("└─┘");
                }
                case ' ' -> {
                    topppp.append("  ");
                    center.append("  ");
                    bottom.append("  ");
                }
                default -> {
                    topppp.append(c);
                    center.append(c);
                    bottom.append(c);
                }
            }
        }
        return "\n" + topppp + add1 + "\u001b[37m\n" +
                center + add2 + "\u001b[37m\n" +
                bottom + "\u001b[34;1mDiscord: mksweety\u001b[37m";
    }
}
