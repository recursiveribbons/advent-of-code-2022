package dev.robinsyl.day2;

import dev.robinsyl.FileLoader;

public class RockPaperScissors {
    public static void main(String[] args) {
        int score = FileLoader.asStream("day2.txt")
                .mapToInt(RockPaperScissors::calculateScore)
                .sum();
        System.out.println(score);
    }

    private static int calculateScore(String raw) {
        String[] hands = raw.split(" ", 2);
        return rockPaperScissors(mapHand(hands[0]), mapHand(hands[1])) + mapHand(hands[1]);
    }

    private static int rockPaperScissors(int them, int me) {
        if (them == me) {
            // draw
            return 3;
        }
        if ((them - me + 3) % 3 == 2) {
            return 6;
        }
        return 0;
    }

    private static int mapHand(String hand) {
        return switch (hand) {
            case "X", "A" -> 1;
            case "Y", "B" -> 2;
            case "Z", "C" -> 3;
            default -> throw new IllegalArgumentException(hand + " is not a valid hand");
        };
    }
}
