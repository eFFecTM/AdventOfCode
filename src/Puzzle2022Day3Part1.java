import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Puzzle2022Day3Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_03");

        int totalPriority = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                char[] chars = reader.readLine().toCharArray();

                Set<Character> compartment1 = new HashSet<>();
                Set<Character> compartment2 = new HashSet<>();

                for (int i = 0; i < chars.length / 2; i++) { // 1st compartment
                    compartment1.add(chars[i]);
                }
                for (int i = chars.length / 2; i < chars.length; i++) { // 2nd compartment
                    compartment2.add(chars[i]);
                }
                compartment1.retainAll(compartment2);

                for (char c : compartment1) {
                    if (c > 96) { // lower case
                        c -= 96;
                    } else { // upper case
                        c -= 38;
                    }
                    totalPriority += c;
                }
            }
        }
        System.out.println("Answer: " + totalPriority);
    }
}