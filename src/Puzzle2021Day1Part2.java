import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Puzzle2021Day1Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2021_01");

        int prevSum = Integer.MAX_VALUE, sum, counter = 0;
        LinkedList<Integer> slidingWindow = new LinkedList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                slidingWindow.offer(Integer.parseInt(reader.readLine()));
                if (slidingWindow.size() == 3) {
                    sum = slidingWindow.stream().reduce(Integer::sum).orElseThrow();
                    if (sum > prevSum) {
                        counter++;
                    }
                    prevSum = sum;
                    slidingWindow.poll();
                }
            }
        }
        System.out.println("Answer: " + counter);
    }
}