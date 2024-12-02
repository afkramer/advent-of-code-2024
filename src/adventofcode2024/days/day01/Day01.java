package adventofcode2024.days.day01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adventofcode2024.Utils;
import adventofcode2024.days.Day;

public class Day01 implements Day {
    @Override
    public void part1(String fileString) {
        List<String> inputs = Utils.readData(fileString);

        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        inputs.stream().forEach(line -> {

            String[] elements = line.split("   ");

            firstList.add(Integer.parseInt(elements[0]));
            secondList.add(Integer.parseInt(elements[1]));
        });

        Collections.sort(firstList);
        Collections.sort(secondList);

        // List<Integer> firstSorted = firstList.stream().distinct().toList();
        // List<Integer> secondSorted = secondList.stream().distinct().toList();

        int sum = 0;

        for (int i = 0; i < firstList.size(); i++) {
            sum += Math.abs(secondList.get(i) - firstList.get(i));
        }

        System.out.println(String.format("Part one: %d", sum));
        System.out.println();
    }

    @Override
    public void part2(String fileString) {
        List<String> inputs = Utils.readData(fileString);

        List<Long> firstList = new ArrayList<>();
        List<Long> secondList = new ArrayList<>();

        inputs.stream().forEach(line -> {

            String[] elements = line.split("   ");

            firstList.add(Long.parseLong(elements[0]));
            secondList.add(Long.parseLong(elements[1]));
        });

        Long sum = secondList.stream().reduce(Long.valueOf(0), (subtotal, element) -> subtotal
                + (element * firstList.stream().filter(i -> i.equals(element)).count()));

        System.out.println(String.format("Part one: %d", sum));
        System.out.println();
    }
}
