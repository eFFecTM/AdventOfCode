import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle2024Day3Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2024_03");

        int total = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    if (matcher.groupCount() == 2) {
                        int number1 = Integer.parseInt(matcher.group(1));
                        int number2 = Integer.parseInt(matcher.group(2));

                        total += number1 * number2;
                    }
                }
            }
        }
        System.out.println("Answer: " + total);
    }
}