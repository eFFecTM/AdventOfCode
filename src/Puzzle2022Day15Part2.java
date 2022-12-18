import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Puzzle2022Day15Part2 {
    public static void main(String[] args) throws IOException {
        Instant startTime = Instant.now();
        Path path = Paths.get("resources/input2022_15");

        int range = 4_000_000;
        Set<Coord> edges = new HashSet<>();
        Set<Coord> sensors = new HashSet<>();
        Set<Coord> beacons = new HashSet<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] words = line.split("[=,:]");
                int sensorX = Integer.parseInt(words[1]);
                int sensorY = Integer.parseInt(words[3]);
                int beaconX = Integer.parseInt(words[5]);
                int beaconY = Integer.parseInt(words[7]);

                // outside edge
                sensors.add(new Coord(sensorX, sensorY,
                        Math.abs(sensorX - beaconX) + Math.abs((sensorY - beaconY)) + 1));
                beacons.add(new Coord(beaconX, beaconY));
            }
        }

        for (Coord sensor : sensors) {
            List<Coord> corners = new ArrayList<>(List.of(
                    new Coord(sensor.x - sensor.d, sensor.y),
                    new Coord(sensor.x, sensor.y - sensor.d),
                    new Coord(sensor.x + sensor.d, sensor.y),
                    new Coord(sensor.x, sensor.y + sensor.d)));

            corners.add(corners.get(0));
            for (int i = 0; i < 4; i++) {
                Coord start = corners.get(i);
                Coord end = corners.get(i + 1);

                int directionX = end.x > start.x ? 1 : -1;
                int directionY = end.y > start.y ? 1 : -1;

                for (int j = 0; j < sensor.d; j++) {
                    int x = start.x + (directionX * j);
                    int y = start.y + (directionY * j);
                    if (x >= 0 && x <= range && y >= 0 && y <= range
                            && sensors.stream().noneMatch(c -> Math.abs(x - c.x) + Math.abs(y - c.y) < c.d)) {
                        edges.add(new Coord(x, y));
                    }
                }
            }
        }
        edges.removeAll(sensors);
        edges.removeAll(beacons);

        if (edges.size() == 1) {
            Coord next = edges.iterator().next();
            long answer = (long) next.x * range + next.y;
            System.out.println("Answer: " + answer);
        }
        Instant endTime = Instant.now();
        System.out.println("Elapsed Time: " + Duration.between(startTime, endTime).toMillis() + " ms");
    }

    public static class Coord {
        int x;
        int y;

        int d;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Coord(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
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