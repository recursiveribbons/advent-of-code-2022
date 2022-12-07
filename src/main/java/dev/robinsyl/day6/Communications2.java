package dev.robinsyl.day6;

import dev.robinsyl.FileLoader;

public class Communications2 {
    public static void main(String[] args) {
        String input = FileLoader.asString("day6.txt");
        for (int i = 0; i <= input.length() - 14; i++) {
            if (areDifferent(input.substring(i, i + 14))) {
                System.out.println(i + 14);
                break;
            }
        }
    }

    private static boolean areDifferent(String substring) {
        return substring.chars()
                .distinct()
                .count() == 14;
    }
}
