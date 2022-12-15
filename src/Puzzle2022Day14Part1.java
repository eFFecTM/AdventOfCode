import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Puzzle2022Day14Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_14");

        Set<Coord> cave = new HashSet<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                int[] prevSplit = null;
                for (String s : line.split(" -> ")) {
                    int[] split = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
                    if (prevSplit != null) {
                        if (split[0] != prevSplit[0] && split[1] == prevSplit[1]) {
                            if (split[0] > prevSplit[0]) {
                                for (int i = prevSplit[0]; i <= split[0]; i++) {
                                    cave.add(new Coord(i, split[1]));
                                }
                            } else {
                                for (int i = split[0]; i <= prevSplit[0]; i++) {
                                    cave.add(new Coord(i, split[1]));
                                }
                            }
                        } else if (split[0] == prevSplit[0] && split[1] != prevSplit[1]) {
                            if (split[1] > prevSplit[1]) {
                                for (int i = prevSplit[1]; i <= split[1]; i++) {
                                    cave.add(new Coord(split[0], i));
                                }
                            } else {
                                for (int i = split[1]; i <= prevSplit[1]; i++) {
                                    cave.add(new Coord(split[0], i));
                                }
                            }
                        } else {
                            throw new RuntimeException("Both axes are different.");
                        }
                    }
                    prevSplit = split;
                }
            }
        }

        int abyss = cave.stream().max(Comparator.comparingInt(o -> o.y)).orElseThrow().y;

        boolean isFallingInAbyss = false;
        int count = 0;
        while (!isFallingInAbyss) {
            Coord sand = new Coord(500, 0);
            boolean falling = true;

            while (falling) {
                if (sand.y > abyss) {
                    isFallingInAbyss = true;
                    break;
                }
                sand.y++;
                if (cave.contains(sand)) {
                    sand.x--;
                    if (cave.contains(sand)) {
                        sand.x++;
                        sand.x++;
                        if (cave.contains(sand)) {
                            sand.x--;
                            sand.y--;
                            falling = false;
                        }
                    }
                }
            }
            if (!falling) {
                cave.add(sand);
                count++;
            }
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

        @Override
        public String toString() {
            return "" + x + "," + y;
        }
    }
}