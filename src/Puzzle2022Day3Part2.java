import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Puzzle2022Day3Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_03");

        int totalPriority = 0;
        int count = 0;
        List<Set<Character>> group = List.of(new HashSet<>(), new HashSet<>(), new HashSet<>());

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                char[] chars = reader.readLine().toCharArray();

                Set<Character> rucksack = group.get(count);
                rucksack.clear();
                for (char c : chars) {
                    rucksack.add(c);
                }

                count++;
                if (count == 3) {
                    Set<Character> intersection = group.get(0);
                    for (int i = 1; i < group.size(); i++) {
                        intersection.retainAll(group.get(i));
                    }

                    if (intersection.size() == 1) {
                        int c = intersection.iterator().next();
                        if (c > 96) { // lower case
                            c -= 96;
                        } else { // upper case
                            c -= 38;
                        }
                        totalPriority += c;
                    } else {
                        System.err.println("Finding " + intersection.size() + " common items in group.");
                    }
                    count = 0;
                }
            }
        }
        System.out.println("Answer: " + totalPriority);
    }
}