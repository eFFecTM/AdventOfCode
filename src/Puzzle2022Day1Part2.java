import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Puzzle2022Day1Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_01");

        Queue<Integer> top3Calories = new PriorityQueue<>(List.of(0, 0, 0));
        int calories = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                if ("".equals(line)) {
                    if (calories > top3Calories.element()) {
                        if (top3Calories.size() == 3) {
                            top3Calories.remove();
                        }
                        top3Calories.add(calories);
                    }
                    calories = 0;
                } else {
                    calories += Integer.parseInt(line);
                }
            }
        }
        System.out.println("Answer: " + top3Calories.stream().reduce(Integer::sum).orElseThrow());
    }
}