import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Puzzle2022Day8Part1 {

    private static int[][] forest;
    private static boolean[][] visible;

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_08");

        forest = new int[99][99];
        visible = new boolean[99][99];

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int i = 0;
            while (reader.ready()) {
                String line = reader.readLine();
                forest[i] = Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray();
                i++;
            }
        }

        for (int i = 0; i < 99; i++) {
            int[] lastHeight = {-1, -1, -1, -1}; // LR, RL, UD, DU
            for (int j = 0; j < 99; j++) {
                if (Arrays.stream(lastHeight).allMatch(x -> x == 9)) {
                    break; // No reason to continue, max height is 9
                }
                lastHeight[0] = lookThroughTrees(lastHeight[0], i, j); // LR
                lastHeight[1] = lookThroughTrees(lastHeight[1], i, 98 - j); // RL
                lastHeight[2] = lookThroughTrees(lastHeight[2], j, i); // UD
                lastHeight[3] = lookThroughTrees(lastHeight[3], 98 - j, i); // DU
            }
        }

        int count = 0;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (visible[i][j]) {
                    count++;
                }
            }
        }

        System.out.println("Answer: " + count);
    }

    private static int lookThroughTrees(int lastHeight, int i, int j) {
        if (lastHeight < forest[i][j]) {
            visible[i][j] = true;
            lastHeight = forest[i][j];
        }
        return lastHeight;
    }
}