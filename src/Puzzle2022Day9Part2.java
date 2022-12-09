import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Puzzle2022Day9Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_09");

        int[][] positionKnots = new int[10][2];

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
                        case "D" -> positionKnots[0][1]++;
                        case "U" -> positionKnots[0][1]--;
                        case "R" -> positionKnots[0][0]++;
                        case "L" -> positionKnots[0][0]--;
                    }
                    for (int k = 0; k < 9; k++) { // 9 other knots to be updated
                        for (int j = 0; j < 2; j++) { // trying to avoid duplicate code by looping over X, then Y
                            if (Math.abs(positionKnots[k][j] - positionKnots[k + 1][j]) > 1) {
                                if (positionKnots[k][j] > positionKnots[k + 1][j]) {
                                    positionKnots[k + 1][j]++;
                                } else {
                                    positionKnots[k + 1][j]--;
                                }

                                // knots can move diagonal and be 2 steps away on both axes
                                if (positionKnots[k][1 - j] > positionKnots[k + 1][1 - j]) {
                                    positionKnots[k + 1][1 - j]++;
                                } else if (positionKnots[k][1 - j] < positionKnots[k + 1][1 - j]) {
                                    positionKnots[k + 1][1 - j]--;
                                }
                            }
                        }
                    }
                    visited.add(positionKnots[9][0] + "," + positionKnots[9][1]);
                }
                System.out.println(positionKnots[9][0] + "," + positionKnots[9][1]);
            }
        }
        System.out.println("Answer: " + visited.size());
    }
}