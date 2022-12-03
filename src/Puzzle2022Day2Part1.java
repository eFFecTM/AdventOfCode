import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Puzzle2022Day2Part1 {
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

        switch (you) {
            case "X" -> score += 1;
            case "Y" -> score += 2;
            case "Z" -> score += 3;
        }

        switch (opponent) {
            case "A" -> { // rock
                switch (you) {
                    case "X" -> score += 3; // rock
                    case "Y" -> score += 6; // paper
                }
            }
            case "B" -> { // paper
                switch (you) {
                    case "Y" -> score += 3; // paper
                    case "Z" -> score += 6; // scissors
                }
            }
            case "C" -> { // scissors
                switch (you) {
                    case "X" -> score += 6; // rock
                    case "Z" -> score += 3; // scissors
                }
            }
        }
        return score;
    }
}