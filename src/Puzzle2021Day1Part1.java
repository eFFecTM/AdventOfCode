import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Puzzle2021Day1Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2021_01");

        int prevDepth = Integer.MAX_VALUE, depth, counter = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                depth = Integer.parseInt(reader.readLine());
                if (depth > prevDepth) {
                    counter++;
                }
                prevDepth = depth;
            }
        }
        System.out.println("Answer: " + counter);
    }
}