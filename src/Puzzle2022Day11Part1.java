import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Puzzle2022Day11Part1 {
    public static void main(String[] args) throws IOException, ScriptException {
        Path path = Paths.get("resources/input2022_11");

        List<Monkey> monkeys = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.startsWith("Monkey")) {
                    List<Integer> startingItems = Stream.of(reader.readLine().split(": ")[1].split(", ")).map(Integer::parseInt).toList();
                    String[] operationString = reader.readLine().split("= ")[1].split(" ");
                    String operation = operationString[1];
                    int operationElement;
                    try {
                        operationElement = Integer.parseInt(operationString[2]);
                    } catch (NumberFormatException e) {
                        operationElement = -1;
                    }
                    int divisibleTest = Integer.parseInt(reader.readLine().split(": ")[1].split(" ")[2]);
                    int whenTrueThrowTo = Integer.parseInt(reader.readLine().split(": ")[1].split(" ")[3]);
                    int whenFalseThrowTo = Integer.parseInt(reader.readLine().split(": ")[1].split(" ")[3]);
                    monkeys.add(new Monkey(startingItems, operation, operationElement, divisibleTest, whenTrueThrowTo, whenFalseThrowTo));
                }
            }
        }

        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                for (int item : monkey.items) {
                    monkey.inspected++;
                    int other = monkey.operationElement == -1 ? item : monkey.operationElement;
                    switch (monkey.operation) {
                        case "*" -> item *= other;
                        case "+" -> item += other;
                    }
                    item /= 3;

                    Monkey throwToMonkey;
                    if (item % monkey.divisibleTest == 0) {
                        throwToMonkey = monkeys.get(monkey.whenTrueThrowTo);
                    } else {
                        throwToMonkey = monkeys.get(monkey.whenFalseThrowTo);
                    }
                    throwToMonkey.items.add(item);
                }
                monkey.items.clear();
            }
            if (i == 0 || i == 19) {
                System.out.println("== After round " + (i + 1) + " ==");
                for (Monkey monkey : monkeys) {
                    System.out.println(monkey.inspected);
                }
                System.out.println();
            }
        }

        int monkeyBusiness = monkeys.stream().map(x -> x.inspected).sorted(Comparator.reverseOrder())
                .limit(2).reduce((a, b) -> a * b).orElseThrow();
        System.out.println("Answer: " + monkeyBusiness);
    }

    static class Monkey {

        List<Integer> items;
        String operation;
        int operationElement;
        int divisibleTest;
        int whenTrueThrowTo, whenFalseThrowTo;
        int inspected = 0;

        public Monkey(List<Integer> startingItems, String operation, int operationElement, int divisibleTest, int whenTrueThrowTo, int whenFalseThrowTo) {
            items = new ArrayList<>(startingItems);
            this.operation = operation;
            this.operationElement = operationElement;
            this.divisibleTest = divisibleTest;
            this.whenTrueThrowTo = whenTrueThrowTo;
            this.whenFalseThrowTo = whenFalseThrowTo;
        }
    }
}
