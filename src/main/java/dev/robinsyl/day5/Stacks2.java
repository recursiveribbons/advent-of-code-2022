package dev.robinsyl.day5;

import dev.robinsyl.FileLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Stacks2 {
    public static void main(String[] args) {
        final List<Stack<Character>> stacks = loadStacks();
        List<Action> actions = loadActions();
        actions.forEach(a -> moveStack(stacks, a));
        stacks.forEach(s -> System.out.print(s.peek()));
    }

    private static void moveStack(final List<Stack<Character>> stacks, Action action) {
        List<Character> moving = new ArrayList<>();
        for (int i = 0; i < action.num; i++) {
            moving.add(stacks.get(action.orig).pop());
        }
        Collections.reverse(moving);
        moving.forEach(item -> stacks.get(action.dest).push(item));
    }

    private static List<Stack<Character>> loadStacks() {
        List<Stack<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            stacks.add(i, new Stack<>());
        }
        List<String> raw = FileLoader.asList("day5-stack.txt");
        Collections.reverse(raw);
        for (String row : raw) {
            for (int i = 0; i < 9; i++) {
                char item = row.charAt(4 * i + 1);
                if (Character.isLetter(item)) {
                    stacks.get(i).push(item);
                }
            }
        }
        return stacks;
    }

    private static List<Action> loadActions() {
        Pattern actionPattern = Pattern.compile("^move (\\d+) from (\\d+) to (\\d+)$");
        return FileLoader.asStream("day5-steps.txt")
                .map(actionPattern::matcher)
                .filter(Matcher::find)
                .map(m -> new Action(parseInt(m.group(1)), parseInt(m.group(2)) - 1, parseInt(m.group(3)) - 1))
                .toList();
    }

    private record Action(int num, int orig, int dest) {
    }
}
