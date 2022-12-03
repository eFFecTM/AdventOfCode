import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Puzzle2022Day2Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_02");

        int totalScore = 0;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] s = line.split(" ");
                String opponent = s[0];
                String you = s[1];
                totalScore += calculateScore(opponent, you);
            }
        }
        System.out.println("Answer: " + totalScore);
    }

    private static int calculateScore(String opponent, String you) {
        int score = 0;

        switch (opponent) {
            case "A" -> { // rock
                switch (you) {
                    case "X" -> score += 3; // lose with scissors
                    case "Y" -> score += 4; // draw with rock
                    case "Z" -> score += 8; // win with paper
                }
            }
            case "B" -> { // paper
                switch (you) {
                    case "X" -> score += 1; // lose with rock
                    case "Y" -> score += 5; // draw with paper
                    case "Z" -> score += 9; // win with scissors
                }
            }
            case "C" -> { // scissors
                switch (you) {
                    case "X" -> score += 2; // lose with paper
                    case "Y" -> score += 6; // draw with scissors
                    case "Z" -> score += 7; // win with rock
                }
            }
        }
        return score;
    }
}