package dev.robinsyl.day1;

import dev.robinsyl.FileLoader;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Calories2 {
    public static void main(String[] args) {
        PriorityQueue<Integer> calories = new PriorityQueue<>(Comparator.reverseOrder());
        List<String> raw = FileLoader.asList("day1.txt");
        int accumulator = 0;
        for (String current : raw) {
            if (current.isBlank()) {
                calories.add(accumulator);
                accumulator = 0;
            } else {
                accumulator += Integer.parseInt(current);
            }
        }
        System.out.println(calories.poll() + calories.poll() + calories.poll());
    }
}
