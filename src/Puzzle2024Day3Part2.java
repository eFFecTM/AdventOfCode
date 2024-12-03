import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzle2024Day3Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2024_03");

        int total = 0;
        boolean isEnabled = true;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");

                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String group = matcher.group();
                    if (isEnabled && group.startsWith("mul(")) {
                        if (matcher.groupCount() == 2) {
                            int number1 = Integer.parseInt(matcher.group(1));
                            int number2 = Integer.parseInt(matcher.group(2));

                            total += number1 * number2;
                        }
                    } else if (group.equals("do()")) {
                        isEnabled = true;
                    } else if (group.equals("don't()")) {
                        isEnabled = false;
                    }
                }
            }
        }
        System.out.println("Answer: " + total);
    }
}