import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Puzzle2022Day10Part2 {

    private static int cycles = 1, index = 0;
    private static final int[] registers = {1};

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_10");

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready() && index <= 5) {
                String line = reader.readLine();
                String[] s = line.split(" ");

                switch (s[0]) {
                    case "noop" -> increaseCycle();
                    case "addx" -> {
                        increaseCycle();
                        increaseCycle();
                        registers[0] += Integer.parseInt(s[1]);
                    }
                }
            }
        }
    }

    private static void increaseCycle() {
        String symbol = ".";
        // Cycles are 1 to 40, but positions are e.g. from 0 to 39, thus -1 on cycles, or +1 on registers
        if (cycles - (40 * index) >= registers[0] && cycles - (40 * index) <= registers[0] + 2) {
            symbol = "#";
        }
        System.out.print(symbol);

        if (cycles == 40 * (index + 1) && index <= 5) {
            System.out.println();
            index++;
        }
        cycles++;
    }
}