package dev.robinsyl.day4;

import dev.robinsyl.FileLoader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cleanup {
    public static void main(String[] args) {
        Pattern elfPairPattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");
        long result = FileLoader.asStream("day4.txt")
                .map(elfPairPattern::matcher)
                .filter(Cleanup::elfIsSubset)
                .count();
        System.out.println(result);
    }

    private static boolean elfIsSubset(Matcher matcher) {
        matcher.find();
        IntPair elf1 = parseElf(matcher.group(1), matcher.group(2));
        IntPair elf2 = parseElf(matcher.group(3), matcher.group(4));
        return isSubsetOf(elf1, elf2) || isSubsetOf(elf2, elf1);
    }

    private static boolean isSubsetOf(IntPair outer, IntPair inner) {
        return outer.lower <= inner.lower && outer.upper >= inner.upper;
    }

    private static IntPair parseElf(String lower, String upper) {
        return new IntPair(Integer.parseInt(lower), Integer.parseInt(upper));
    }

    private record IntPair(int lower, int upper) {
    }
}
