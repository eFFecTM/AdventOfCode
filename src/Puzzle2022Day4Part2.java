import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Puzzle2022Day4Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_04");

        int count = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();

                int[] s = Arrays.stream(line.split("[,\\-]")).mapToInt(Integer::parseInt).toArray();

                if (s[1] >= s[2] && s[0] <= s[3]) {
                    count++;
                }
            }
        }
        System.out.println("Answer: " + count);
    }
}