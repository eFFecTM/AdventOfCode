import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Puzzle2022Day5Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_05");

        List<Stack<Character>> stacks = new ArrayList<>(List.of(new Stack<>()));

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();

                if (line.startsWith("[")) {
                    boolean expectBox = false, foundEmpty = false, skipNextChar = false;
                    int count = 0;

                    for (char c : line.toCharArray()) {
                        if (expectBox) {
                            Stack<Character> stack;
                            if (stacks.size() <= count) {
                                stack = new Stack<>();
                                stacks.add(stack);
                            } else {
                                stack = stacks.get(count);
                            }
                            if (!foundEmpty) {
                                stack.add(0, c);
                            } else {
                                foundEmpty = false;
                            }
                            expectBox = false;
                            skipNextChar = true;
                            count++;
                        } else if (foundEmpty && c == ' ') {
                            expectBox = true;
                        } else if (!skipNextChar) {
                            if (c == '[') {
                                expectBox = true;
                                foundEmpty = false;
                            } else if (c == ' ') {
                                foundEmpty = true;
                            }
                        } else {
                            skipNextChar = false;
                        }
                    }
                } else if (line.startsWith("move")) {
                    String[] s = line.split(" ");
                    int amount = Integer.parseInt(s[1]);
                    int fromStack = Integer.parseInt(s[3]) - 1;
                    int toStack = Integer.parseInt(s[5]) - 1;
                    for (int i = 0; i < amount; i++) {
                        Character pop = stacks.get(fromStack).pop();
                        stacks.get(toStack).push(pop);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Stack<Character> stack : stacks) {
            if (!stack.isEmpty()) {
                sb.append(stack.peek());
            }
        }
        System.out.println("Answer: " + sb);
    }
}