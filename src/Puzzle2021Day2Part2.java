import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Puzzle2021Day2Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2021_02");

        String course;
        int horizontalPosition = 0, depth = 0, aim = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String[] line = reader.readLine().split(" ");
                course = line[0];
                int units = Integer.parseInt(line[1]);

                switch (course) {
                    case "forward" -> {
                        horizontalPosition += units;
                        depth += aim * units;
                    }
                    case "up" -> aim -= units;
                    case "down" -> aim += units;
                }
            }
        }
        System.out.println("Answer: " + horizontalPosition * depth);
    }
}