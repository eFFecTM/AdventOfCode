import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Puzzle2022Day9Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_09");

        int[] positionHead = new int[2], positionTail = new int[2];

        Set<String> visited = new HashSet<>();
        visited.add("0,0"); // start location

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();

                String[] s = line.split(" ");
                String direction = s[0];
                int steps = Integer.parseInt(s[1]);

                for (int i = 0; i < steps; i++) {
                    switch (direction) {
                        case "D" -> positionHead[1]++;
                        case "U" -> positionHead[1]--;
                        case "R" -> positionHead[0]++;
                        case "L" -> positionHead[0]--;
                    }

                    for (int j = 0; j < 2; j++) {
                        if (Math.abs(positionHead[j] - positionTail[j]) > 1) {
                            if (positionHead[1 - j] != positionTail[1 - j]) {
                                positionTail[1 - j] = positionHead[1 - j];
                            }

                            if (positionHead[j] > positionTail[j]) {
                                positionTail[j]++;
                            } else {
                                positionTail[j]--;
                            }
                        }
                    }
                    visited.add(positionTail[0] + "," + positionTail[1]);
                }
            }
        }
        System.out.println("Answer: " + visited.size());
    }
}