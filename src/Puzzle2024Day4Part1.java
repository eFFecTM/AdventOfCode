import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle2024Day4Part1 {
    static char[][] matrix;
    static int maxRows, maxColumns;

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2024_04");

        int total = 0;
        List<String> puzzle = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                puzzle.add(line);
            }
        }

        maxRows = puzzle.size();
        maxColumns = puzzle.get(0).length();
        matrix = new char[maxRows][maxColumns];
        for (int i = 0; i < maxRows; i++) {
            matrix[i] = puzzle.get(i).toCharArray();
        }

        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxColumns; j++) {
                if (nextXmasMatch(0, matrix[i][j]) == 1) {
                    for (int d = 1; d <= 8; d++) { // go over all directions
                        boolean isXmas = findXmas(1, i, j, d);
                        if (isXmas) {
                            total++;
                        }
                    }
                }
            }
        }

        System.out.println("Answer: " + total);
    }

    private static boolean findXmas(int matchingCount, int row, int column, int direction) {
        Pair<Integer, Integer> pair = resolveDirection(row, column, direction);
        if (pair == null) {
            return false;
        }
        int newMatchingCount = nextXmasMatch(matchingCount, matrix[pair.row][pair.column]);
        if (newMatchingCount == 4) {
            return true;
        }
        if (newMatchingCount > matchingCount) {
            return findXmas(newMatchingCount, pair.row, pair.column, direction);
        } else {
            return false;
        }
    }

    private static int nextXmasMatch(int matchingCount, char nextChar) {
        return switch (nextChar) {
            case 'X': yield 1;
            case 'M': yield matchingCount == 1 ? ++matchingCount : 0;
            case 'A': yield matchingCount == 2 ? ++matchingCount : 0;
            case 'S': yield matchingCount == 3 ? ++matchingCount : 0;
            default: yield 0;
        };
    }

    private static Pair<Integer, Integer> resolveDirection(int row, int column, int direction) {
        switch (direction) { // 1 till 8 (top till top left, clock wise)
            case 1, 2, 8 -> row--;
            case 4, 5, 6 -> row++;
        }
        switch (direction) {
            case 6, 7, 8 -> column--;
            case 2, 3, 4 -> column++;
        }
        if (row < 0 || row >= maxRows || column < 0 || column >= maxColumns) {
            return null;
        }
        return new Pair<>(row, column);
    }

    public record Pair<K, V>(K row, V column) {
    }
}
