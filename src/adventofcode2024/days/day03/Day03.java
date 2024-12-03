package adventofcode2024.days.day03;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adventofcode2024.Utils;
import adventofcode2024.days.Day;

public class Day03 implements Day {
    private String regex = "mul\\([0-9]{1,},[0-9]{1,}\\)";
    private String enabler = "do\\(\\)";

    private String disabler = "don't\\(\\)";

    public void part1(String fileName) {
        List<String> inputs = Utils.readData(fileName);
        Pattern pattern = Pattern.compile(regex);
        long sumOfValidInputs = 0l;

        for (String input : inputs) {
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String operation = matcher.group();
                sumOfValidInputs += multiplyNumbersInString(operation);
            }
        }

        System.out.println(String.format("Day 3 part 1: %d", sumOfValidInputs));
    }

    public void part2(String fileName) {
        List<String> inputs = Utils.readData(fileName);
        Pattern mulPattern = Pattern.compile(regex);
        Pattern enablePattern = Pattern.compile(enabler);
        Pattern disablePattern = Pattern.compile(disabler);

        int sumOfValidInputs = 0;
        boolean enabled = true;
        int startFrom = 0;
        int startLastDisabler = 0;

        for (String input : inputs) {
            Matcher mulMatcher = mulPattern.matcher(input);
            Matcher enableMatcher = enablePattern.matcher(input);
            Matcher disableMatcher = disablePattern.matcher(input);

            if (!enabled) {
                if (enableMatcher.find()) {
                    startFrom = enableMatcher.end();
                    enabled = true;
                } else {
                    continue;
                }
            }

            boolean foundDisabler = disableMatcher.find(startFrom);
            startLastDisabler = foundDisabler ? disableMatcher.start() : input.length();

            while (mulMatcher.find(startFrom)) {
                if (mulMatcher.start() < startLastDisabler) {
                    String operation = mulMatcher.group();
                    sumOfValidInputs += multiplyNumbersInString(operation);
                    startFrom = mulMatcher.end();
                } else {
                    if (enableMatcher.find(startLastDisabler)) {
                        startFrom = enableMatcher.end();
                        foundDisabler = disableMatcher.find(startFrom);
                        startLastDisabler = foundDisabler ? disableMatcher.start() : input.length();
                    } else {
                        enabled = false;
                        break;
                    }
                }
            }
            startFrom = 0;

        }

        System.out.println(String.format("Day 3 part 2: %d", sumOfValidInputs));
    }

    private int multiplyNumbersInString(String mulMatch) {
        mulMatch = mulMatch.replace("mul(", "");
        mulMatch = mulMatch.replace(")", "");
        String[] nums = mulMatch.split(",");
        return Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
    }
}
