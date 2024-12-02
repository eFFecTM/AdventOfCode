import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle2024Day2Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2024_02");

        int countSafe = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                List<String> originalReport = Arrays.stream(line.split(" ")).toList();


                boolean isSafe;
                int levelCount = -1;
                do {
                    isSafe = true;
                    Boolean isIncreasing = null;
                    int previous = -1;
                    List<String> report = new ArrayList<>(originalReport);
                    if (levelCount != -1) {
                        report.remove(levelCount);
                    }

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
                    levelCount++;
                } while (!isSafe && levelCount < originalReport.size());

                if (isSafe) {
                    countSafe++;
                }
            }
        }
        System.out.println("Answer: " + countSafe);
    }
}