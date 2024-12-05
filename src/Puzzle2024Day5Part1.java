import org.graalvm.collections.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Puzzle2024Day5Part1 {
    static Map<Integer, Pair<Set<Integer>, Set<Integer>>> pageRules = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2024_05");

        int total = 0;
        boolean nextSection = false;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                if ("".equals(line)) {
                    nextSection = true;
                } else if (!nextSection) { // first section
                    processSection1(line);
                } else { // 2nd section
                    total += processSection2(line);
                }
            }
        }
        System.out.println("Answer: " + total);
    }

    private static void processSection1(String line) {
        int[] pageNumbers = Arrays.stream(line.split("\\|")).mapToInt(Integer::parseInt).toArray();
        for (int pageNumber : pageNumbers) {
            pageRules.computeIfAbsent(pageNumber, k -> Pair.create(new HashSet<>(), new HashSet<>()));
        }
        pageRules.get(pageNumbers[0]).getRight().add(pageNumbers[1]);
        pageRules.get(pageNumbers[1]).getLeft().add(pageNumbers[0]);
    }

    private static int processSection2(String line) {
        List<Integer> update = Stream.of(line.split(",")).map(Integer::parseInt).toList();

        for (int i = 0; i < update.size(); i++) {
            Pair<Set<Integer>, Set<Integer>> beforeAfter = pageRules.get(update.get(i));
            List<Integer> leftSide = update.subList(0, i);
            List<Integer> rightSide = update.subList(i + 1, update.size());
            for (Integer left : leftSide) {
                if (beforeAfter.getRight().contains(left)) {
                    return 0;
                }
            }
            for (Integer right : rightSide) {
                if (beforeAfter.getLeft().contains(right)) {
                    return 0;
                }
            }
        }
        int middle = (update.size() - 1) / 2;
        return update.get(middle);
    }
}