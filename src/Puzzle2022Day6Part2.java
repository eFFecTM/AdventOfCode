import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Puzzle2022Day6Part2 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_06");

        int count = 1;
        LinkedList<Integer> list = new LinkedList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                int read = reader.read();
                if (list.contains(read)) {
                    int i = list.indexOf(read);
                    for (int j = 0; j <= i; j++) {
                        list.pop();
                    }
                }
                list.add(read);

                if (list.size() == 14) {
                    break;
                }
                count++;
            }
        }
        System.out.println("Answer: " + count);
    }
}