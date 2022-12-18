import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Puzzle2022Day15Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_15");


        Set<Integer> cannotContain = new HashSet<>();
        int Y = 2_000_000;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                String[] words = line.split("[=,:]");
                int sensorX = Integer.parseInt(words[1]);
                int sensorY = Integer.parseInt(words[3]);
                int beaconX = Integer.parseInt(words[5]);
                int beaconY = Integer.parseInt(words[7]);

                int distanceBetweenSensorAndBeacon = Math.abs(sensorX - beaconX) + Math.abs((sensorY - beaconY));
                int distanceBetweenSensorAndRow = Math.abs(sensorY - Y);
                if (distanceBetweenSensorAndRow <= distanceBetweenSensorAndBeacon) { // cannot be present
                    cannotContain.add(sensorX);
                    for (int i = 1; i <= distanceBetweenSensorAndBeacon - distanceBetweenSensorAndRow; i++) {
                        cannotContain.add(sensorX - i);
                        cannotContain.add(sensorX + i);
                    }
                }

                if (sensorY == Y) {
                    cannotContain.remove(sensorX);
                }
                if (beaconY == Y) {
                    cannotContain.remove(beaconX);
                }
            }
        }
        System.out.println("Answer: " + cannotContain.size());
    }
}