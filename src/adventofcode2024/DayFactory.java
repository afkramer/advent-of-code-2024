package adventofcode2024;

import adventofcode2024.days.Day;
import adventofcode2024.days.day01.Day01;
import adventofcode2024.days.day02.Day02;
import adventofcode2024.days.day03.Day03;
import adventofcode2024.days.day04.Day04;
import adventofcode2024.days.day05.Day05;
import adventofcode2024.days.day06.Day06;

public class DayFactory {
    public static Day getDay(int day) {
        return switch (day) {
            case 1 -> new Day01();
            case 2 -> new Day02();
            case 3 -> new Day03();
            case 4 -> new Day04();
            case 5 -> new Day05();
            case 6 -> new Day06();
            default -> throw new IllegalArgumentException("Day not implemented yet!");
        };
    }
}
