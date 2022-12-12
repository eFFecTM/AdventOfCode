import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Puzzle2022Day12Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_12");

        int Y = 61, X = 41;

        int[][] map = new int[X][Y];
        int count = 0;

        Coord end = null;

        Set<Coord> flooding = new HashSet<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                for (int i = 0; i < X; i++) {
                    for (int j = 0; j < Y; j++) {
                        int read = reader.read();
                        if (read == 83 || read == 97) {
                            flooding.add(new Coord(i, j));
                            map[i][j] = 0;
                        } else if (read == 69) {
                            end = new Coord(i, j);
                            map[i][j] = 25;
                        } else {
                            map[i][j] = read - 97;
                        }
                    }
                    reader.read(); // LF
                }
            }
        }

        Set<Coord> newFlooding = new HashSet<>();
        while (!flooding.contains(end)) {
            for (Coord spot : flooding) {
                int height = map[spot.x][spot.y];
                if (spot.y != 0) {
                    int left = map[spot.x][spot.y - 1];
                    if (left < height || Math.abs(height - left) <= 1) { // left
                        newFlooding.add(new Coord(spot.x, spot.y - 1));
                    }
                }
                if (spot.x != 0) {
                    int up = map[spot.x - 1][spot.y];
                    if (up < height || Math.abs(height - up) <= 1) { // up
                        newFlooding.add(new Coord(spot.x - 1, spot.y));
                    }
                }
                if (spot.y != Y - 1) {
                    int right = map[spot.x][spot.y + 1];
                    if (right < height || Math.abs(height - right) <= 1) { // right
                        newFlooding.add(new Coord(spot.x, spot.y + 1));
                    }
                }
                if (spot.x != X - 1) {
                    int down = map[spot.x + 1][spot.y];
                    if (down < height || Math.abs(height - down) <= 1) { // down
                        newFlooding.add(new Coord(spot.x + 1, spot.y));
                    }
                }
            }
            flooding.addAll(newFlooding);
            newFlooding.clear();
            count++;
        }

        System.out.println("Answer: " + count);
    }

    public static class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Coord other) {
                return x == other.x && y == other.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}