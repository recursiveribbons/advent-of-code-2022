package dev.robinsyl.day2;

import dev.robinsyl.FileLoader;

public class RockPaperScissors2 {
    public static void main(String[] args) {
        int score = FileLoader.asStream("day2.txt")
                .mapToInt(RockPaperScissors2::calculateScore)
                .sum();
        System.out.println(score);
    }

    private static int calculateScore(String raw) {
        String[] hands = raw.split(" ", 2);
        return (mapHand(hands[0]) + mapAdjustment(hands[1])) % 3 + 1 + mapWin(hands[1]);
    }

    private static int mapAdjustment(String result) {
        return switch (result) {
            case "X" -> 2;
            case "Y" -> 0;
            case "Z" -> 1;
            default -> throw new IllegalArgumentException(result + " is not a valid result");
        };
    }

    private static int mapHand(String hand) {
        return switch (hand) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            default -> throw new IllegalArgumentException(hand + " is not a valid hand");
        };
    }

    private static int mapWin(String result) {
        return switch (result) {
            case "X" -> 0;
            case "Y" -> 3;
            case "Z" -> 6;
            default -> throw new IllegalArgumentException(result + " is not a valid result");
        };
    }
}
