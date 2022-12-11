import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Puzzle2022Day10Part1 {

    private static int cycles = 1, index = 0, sum = 0;
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
        System.out.println("Answer: " + sum);
    }

    private static void increaseCycle() {
        if (cycles == 20 + 40 * index && index <= 5) {
            sum += registers[0] * cycles;
            System.err.println("Sum: " + sum + "\t\t Register X: " + registers[0] + "\t\tCycles: " + cycles);
            index++;
        }
        cycles++;
    }
}