package adventofcode2024.days.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adventofcode2024.Utils;
import adventofcode2024.days.Day;

public class Day02 implements Day {
    private static final int MIN_GAP = 1;
    private static final int MAX_GAP = 3;

    public void part1(String fileName) {
        List<String> inputs = Utils.readData(fileName);
        int count = 0;
        for (String input : inputs) {
            String[] numbers = input.split(" ");

            boolean safe = true;
            for (int i = 0; i < numbers.length - 2; i++) {
                int a = Integer.parseInt(numbers[i]);
                int b = Integer.parseInt(numbers[i + 1]);
                int c = Integer.parseInt(numbers[i + 2]);
                int gapFromAToB = Math.abs(a - b);
                int gapFromBToC = Math.abs(b - c);

                boolean ascending = a < b && b < c;
                boolean descending = a > b && b > c;
                boolean minGap = gapFromAToB >= MIN_GAP && gapFromBToC >= MIN_GAP;
                boolean maxGap = gapFromAToB <= MAX_GAP && gapFromBToC <= MAX_GAP;

                if ((!ascending && !descending) || (!minGap || !maxGap)) {
                    safe = false;
                }
            }
            if (safe) {
                count++;
            }
        }

        System.out.println(String.format("Part one: %d%n", count));
    }

    public void part2(String fileString) {
        List<String> inputs = Utils.readData(fileString);
        int count = 0;
        for (String input : inputs) {
            List<String> numbers = List.of(input.split(" "));
            if (isSafe(numbers)) {
                count++;
            } else {
                for (int i = 0; i < numbers.size(); i++) {
                    List<String> numbersWithOneRemoved = new ArrayList<>(Arrays.asList(input.split(" ")));
                    numbersWithOneRemoved.remove(i);
                    if (isSafe(numbersWithOneRemoved)) {
                        count++;
                        break;
                    }
                }
            }

        }
        System.out.println(String.format("Part two: %d%n", count));
    }

    boolean isSafe(List<String> numbers) {
        boolean safe = true;

        for (int i = 0; i < numbers.size() - 2; i++) {
            int a = Integer.parseInt(numbers.get(i));
            int b = Integer.parseInt(numbers.get(i + 1));
            int c = Integer.parseInt(numbers.get(i + 2));
            int gapFromAToB = Math.abs(a - b);
            int gapFromBToC = Math.abs(b - c);

            boolean ascending = a < b && b < c;
            boolean descending = a > b && b > c;
            boolean minGap = gapFromAToB >= MIN_GAP && gapFromBToC >= MIN_GAP;
            boolean maxGap = gapFromAToB <= MAX_GAP && gapFromBToC <= MAX_GAP;

            if ((!ascending && !descending) || (!minGap || !maxGap)) {
                safe = false;
            }
        }
        return safe;
    }
}
