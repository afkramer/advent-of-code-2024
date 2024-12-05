package adventofcode2024;

import adventofcode2024.days.Day;

public class Main {
    private static final int DAY = 5;
    private static final boolean TEST = false;
    private static final boolean RUN_1 = false;
    private static final boolean RUN_2 = true;

    public static void main(String[] args) {
        Day day = DayFactory.getDay(DAY);
        if (RUN_1) {
            day.part1(getFileNameForDay());
        }

        if (RUN_2) {
            day.part2(getFileNameForDay());
        }
    }

    private static String getFileNameForDay() {
        String testString = TEST ? "test" : "";
        return String.format("day%02d%s.txt", DAY, testString);
    }

}