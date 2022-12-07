package dev.robinsyl.day3;

import dev.robinsyl.FileLoader;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rucksack {
    public static void main(String[] args) {
        int result = FileLoader.asStream("day3.txt")
                .map(Rucksack::splitItems)
                .flatMapToInt(Rucksack::findDuplicates)
                .map(Rucksack::calculateValue).sum();
        System.out.println(result);
    }

    private static StringPair splitItems(String rucksack) {
        int midpoint = rucksack.length() / 2;
        return new StringPair(rucksack.substring(0, midpoint), rucksack.substring(midpoint));
    }

    private static IntStream findDuplicates(StringPair rucksack) {
        Set<Integer> firstHalf = rucksack.left
                .chars()
                .boxed()
                .collect(Collectors.toSet());
        return rucksack.right
                .chars()
                .filter(firstHalf::contains)
                .distinct();
    }

    private static int calculateValue(int item) {
        if (Character.isUpperCase(item)) {
            return item - 64 + 26;
        } else if (Character.isLowerCase(item)) {
            return item - 96;
        }
        throw new IllegalArgumentException(item + " is not a valid object");
    }

    private record StringPair(String left, String right) {
    }
}
