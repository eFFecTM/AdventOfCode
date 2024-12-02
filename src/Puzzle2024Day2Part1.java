import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Puzzle2024Day2Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2024_02");

        int countSafe = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] report = line.split(" ");

                Boolean isIncreasing = null;
                int previous = -1;
                boolean isSafe = true;
                for (String level : report) {
                    int next = Integer.parseInt(level);
                    if (previous != -1) {
                        if (isIncreasing == null) {
                            isIncreasing = next > previous;
                        }
                        if (isIncreasing != next > previous) {
                            isSafe = false;
                            break;
                        }
                        if (previous == next || Math.abs(next - previous) > 3) {
                            isSafe = false;
                            break;
                        }
                    }
                    previous = next;
                }
                if (isSafe) {
                    countSafe++;
                }
            }
        }
        System.out.println("Answer: " + countSafe);
    }
}