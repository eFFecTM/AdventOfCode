import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Puzzle2022Day1Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_01");

        int maxCalories = Integer.MIN_VALUE;
        int calories = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                if ("".equals(line)) {
                    if (calories > maxCalories) {
                        maxCalories = calories;
                    }
                    calories = 0;
                } else {
                    calories += Integer.parseInt(line);
                }
            }
        }
        System.out.println("Answer: " + maxCalories);
    }
}