package adventofcode2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
}