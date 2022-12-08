import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Puzzle2022Day8Part2 {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_08");

        int[][] forest = new int[99][99];

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            int i = 0;
            while (reader.ready()) {
                String line = reader.readLine();
                forest[i] = Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray();
                i++;
            }
        }

        int bestScore = 0;

        // Edges will give score of zero, excluding them from the range
        for (int i = 1; i < 98; i++) {
            for (int j = 1; j < 98; j++) {
                int height = forest[i][j];
                int countRight = 0;
                do {
                    countRight++;
                } while (j + countRight < 98 && height > forest[i][j + countRight]);

                int countLeft = 0;
                do {
                    countLeft++;
                } while (j - countLeft > 0 && height > forest[i][j - countLeft]);

                int countDown = 0;
                do {
                    countDown++;
                } while (i + countDown < 98 && height > forest[i + countDown][j]);

                int countUp = 0;
                do {
                    countUp++;
                } while (i - countUp > 0 && height > forest[i - countUp][j]);

                int score = countLeft * countRight * countDown * countUp;
                if (score > bestScore) {
                    bestScore = score;
                }
            }
        }

        System.out.println("Answer: " + bestScore);
    }
}