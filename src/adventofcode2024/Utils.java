package adventofcode2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    private Utils() {
    }

    public static List<String> readData(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("./inputs/" + fileName))) {
            return reader.lines()
                    .toList();
        } catch (IOException e) {
            System.err.print("Could not read data.");
            return new ArrayList<>();
        }
    }

    public static List<String> pivotStringList(List<String> toPivot) {
        int numRowsSource = toPivot.size();
        int numColsSource = toPivot.get(0).length();
        int numRowsTarget = toPivot.get(0).length();
        int numColsTarget = toPivot.size();

        String[][] pivotedStrings = new String[numRowsTarget][numColsTarget];

        for (int x = 0; x < numRowsSource; x++) {
            String reversed = reverseString(toPivot.get(x));
            for (int y = 0; y < numColsSource; y++) {
                pivotedStrings[y][x] = reversed.charAt(y) + "";
            }
        }

        List<String> pivotedStringList = new ArrayList<>();

        for (int row = 0; row < pivotedStrings.length; row++) {
            String string = Arrays.stream(pivotedStrings[row]).collect(Collectors.joining(""));
            pivotedStringList.add(string);
        }

        return pivotedStringList;
    }

    public static String reverseString(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = string.length() - 1; i >= 0; i--) {
            sb.append(string.charAt(i) + "");
        }
        return sb.toString();
    }
}
