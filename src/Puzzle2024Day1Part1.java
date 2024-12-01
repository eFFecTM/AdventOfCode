import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Queue;

public class Puzzle2024Day1Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2024_01");

        Queue<Integer> leftList = new PriorityQueue<>();
        Queue<Integer> rightList = new PriorityQueue<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] s = line.split(" {3}");
                leftList.add(Integer.valueOf(s[0]));
                rightList.add(Integer.valueOf(s[1]));
            }
        }

        int totalDistance = 0;
        Integer left, right;
        while ((left = leftList.poll()) != null && (right = rightList.poll()) != null) {
            totalDistance += Math.abs(left - right);
        }
        System.out.println("Answer: " + totalDistance);
    }
}