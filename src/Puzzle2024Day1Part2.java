import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Puzzle2024Day1Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2024_01");

        Map<Integer, Integer> leftList = new HashMap<>();
        Map<Integer, Integer> rightList = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] s = line.split(" {3}");
                int left = Integer.parseInt(s[0]);
                int right = Integer.parseInt(s[1]);
                leftList.put(left, leftList.getOrDefault(left, 0) + 1);
                rightList.put(right, rightList.getOrDefault(right, 0) + 1);
            }
        }

        int simularityScore = 0;
        for (Entry<Integer, Integer> entry : leftList.entrySet()) {
            simularityScore += entry.getKey() * entry.getValue() * rightList.getOrDefault(entry.getKey(), 0);
        }
        System.out.println("Answer: " + simularityScore);
    }
}