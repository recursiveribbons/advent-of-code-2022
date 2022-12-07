package dev.robinsyl.day6;

import dev.robinsyl.FileLoader;

public class Communications {
    public static void main(String[] args) {
        String input = FileLoader.asString("day6.txt");
        for (int i = 0; i <= input.length() - 4; i++) {
            if (areDifferent(input.substring(i, i + 4))) {
                System.out.println(i + 4);
                break;
            }
        }
    }

    private static boolean areDifferent(String substring) {
        return substring.chars()
                .distinct()
                .count() == 4;
    }
}
