package dev.robinsyl.day3;

import dev.robinsyl.FileLoader;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Rucksack2 {
    public static void main(String[] args) {
        List<String> file = FileLoader.asList("day3.txt");
        int accumulator = 0;
        int elfIndex = 0;
        Set<Integer> overlap = Collections.emptySet();
        for (String rucksack : file) {
            if (elfIndex == 0) {
                overlap = rucksack.chars()
                        .boxed()
                        .collect(Collectors.toSet());
            } else {
                overlap = rucksack.chars()
                        .filter(overlap::contains)
                        .boxed()
                        .collect(Collectors.toSet());
            }
            if (elfIndex == 2) {
                accumulator += calculateValue(overlap.iterator().next());
                elfIndex = 0;
            } else {
                elfIndex++;
            }
        }
        System.out.println(accumulator);
    }

    private static int calculateValue(int item) {
        if (Character.isUpperCase(item)) {
            return item - 64 + 26;
        } else if (Character.isLowerCase(item)) {
            return item - 96;
        }
        throw new IllegalArgumentException(item + " is not a valid object");
    }
}
