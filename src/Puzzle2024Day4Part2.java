import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle2024Day4Part2 {
    static char[][] matrix;
    static int maxRows, maxColumns;
    static int diagonalSum = 'S' + 'M';

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
                if (matrix[i][j] == 'A' && check4Corners(i, j)) {
                    total++;
                }
            }
        }

        System.out.println("Answer: " + total);
    }

    private static boolean check4Corners(int row, int column) {
        try {
            Pair<Integer, Integer> topRight = resolveDirection(row, column, 2);
            Pair<Integer, Integer> bottomLeft = resolveDirection(row, column, 6);
            Pair<Integer, Integer> topLeft = resolveDirection(row, column, 8);
            Pair<Integer, Integer> bottomRight = resolveDirection(row, column, 4);
            char topRightLetter = matrix[topRight.row][topRight.column];
            char bottomLeftLetter = matrix[bottomLeft.row][bottomLeft.column];
            char topLeftLetter = matrix[topLeft.row][topLeft.column];
            char bottomRightLetter = matrix[bottomRight.row][bottomRight.column];
            return topRightLetter + bottomLeftLetter == diagonalSum && topLeftLetter + bottomRightLetter == diagonalSum;
        } catch (IndexOutOfBoundsException ignored) {
        }
        return false;
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
            throw new IndexOutOfBoundsException();
        }
        return new Pair<>(row, column);
    }

    public record Pair<K, V>(K row, V column) {
    }
}
