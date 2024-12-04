package adventofcode2024.days.day04;

import java.util.List;

import adventofcode2024.Utils;
import adventofcode2024.days.Day;

public class Day04 implements Day {
    private static final String WORD_TO_FIND = "XMAS";

    @Override
    public void part1(String input) {
        List<String> inputs = Utils.readData(input);
        int totalCount = 0;

        for (String line : inputs) {
            totalCount += findWordCountInLine(line, WORD_TO_FIND);
            totalCount += findWordCountInLine(line, Utils.reverseString(WORD_TO_FIND));
        }

        // pivot the inputs to find words going vertically
        List<String> inputsBackwards = Utils.pivotStringList(inputs);

        for (String line : inputsBackwards) {
            totalCount += findWordCountInLine(line, WORD_TO_FIND);
            totalCount += findWordCountInLine(line, Utils.reverseString(WORD_TO_FIND));
        }

        totalCount += findWordCountDiagonallyDown(inputs, WORD_TO_FIND);
        totalCount += findWordCountDiagonallyDown(inputs, Utils.reverseString(WORD_TO_FIND));

        totalCount += findWordCountDiagonallyUp(inputs, WORD_TO_FIND);
        totalCount += findWordCountDiagonallyUp(inputs, Utils.reverseString(WORD_TO_FIND));

        System.out.println(String.format("Day 4 part 1: %d", totalCount));
    }

    @Override
    public void part2(String input) {
        List<String> inputs = Utils.readData(input);
        int totalCount = 0;

        totalCount += findWordCrosses(inputs);

        System.out.println(String.format("Day 4 part 2: %d", totalCount));
    }

    private int findWordCountInLine(String input, String wordToFind) {
        int count = 0;
        for (int i = 0; i <= (input.length() - wordToFind.length()); i++) {
            boolean wordMatches = true;
            for (int j = 0; j < wordToFind.length(); j++) {
                if (input.charAt(i + j) != wordToFind.charAt(j)) {
                    wordMatches = false;
                }
            }
            count += wordMatches ? 1 : 0;
        }
        return count;
    }

    private int findWordCountDiagonallyDown(List<String> lines, String wordToFind) {
        int rowsToCheck = lines.size() - wordToFind.length();
        int colsToCheck = lines.get(0).length() - wordToFind.length();
        int wordCount = 0;

        for (int row = 0; row <= rowsToCheck; row++) {
            for (int col = 0; col <= colsToCheck; col++) {
                boolean wordMatches = true;
                for (int j = 0; j < wordToFind.length(); j++) {
                    if (lines.get(row + j).charAt(col + j) != wordToFind.charAt(j)) {
                        wordMatches = false;
                        break;
                    }
                }
                wordCount += wordMatches ? 1 : 0;
            }
        }

        return wordCount;
    }

    private int findWordCountDiagonallyUp(List<String> lines, String wordToFind) {
        int rowsMax = lines.size() - 1;
        int rowsMin = wordToFind.length() - 1;
        int colsToCheck = lines.get(0).length() - wordToFind.length();
        int wordCount = 0;

        for (int row = rowsMax; row >= rowsMin; row--) {
            for (int col = 0; col <= colsToCheck; col++) {
                boolean wordMatches = true;
                for (int j = 0; j < wordToFind.length(); j++) {
                    if (lines.get(row - j).charAt(col + j) != wordToFind.charAt(j)) {
                        wordMatches = false;
                        break;
                    }
                }
                wordCount += wordMatches ? 1 : 0;
            }
        }

        return wordCount;
    }

    private int findWordCrosses(List<String> inputs) {
        int wordCount = 0;
        int rowMax = inputs.size() - 2;
        int colMax = inputs.get(0).length() - 2;

        for (int row = 1; row <= rowMax; row++) {
            for (int col = 1; col <= colMax; col++) {

                if (inputs.get(row).charAt(col) == 'A') {
                    char topLeft = inputs.get(row - 1).charAt(col - 1);
                    char topRight = inputs.get(row - 1).charAt(col + 1);
                    char bottomLeft = inputs.get(row + 1).charAt(col - 1);
                    char bottomRight = inputs.get(row + 1).charAt(col + 1);
                    wordCount += isValidCombo(topLeft, topRight, bottomLeft, bottomRight) ? 1 : 0;
                }
            }
        }

        return wordCount;
    }

    private boolean isValidCombo(char topLeft, char topRight, char bottomLeft, char bottomRight) {
        boolean validPattern1 = topLeft == 'M' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'S';
        boolean validPattern2 = topLeft == 'S' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'M';
        boolean validPattern3 = topLeft == 'S' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'M';
        boolean validPattern4 = topLeft == 'M' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'S';

        return validPattern1 || validPattern2 || validPattern3 || validPattern4;
    }

}
