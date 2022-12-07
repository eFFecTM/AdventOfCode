import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Puzzle2022Day7Part1 {
    private static int sumDirectorySize = 0;

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_07");

        Path currentDir = Paths.get("/");

        Map<String, Object> rootDir = new HashMap<>();
        Map<String, Object> walkingDir = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                String line = reader.readLine();

                if (line.startsWith("$")) { // command
                    if (line.startsWith("$ cd ")) {
                        String s = line.split(" ")[2];
                        if ("..".equals(s)) {
                            currentDir = currentDir.getParent();
                        } else {
                            currentDir = currentDir.resolve(s);
                        }
                    } else if ("$ ls".equals(line)) {
                        walkingDir = rootDir;
                        for (String folder : currentDir.toString().split("/")) {
                            if ("".equals(folder) || "/".equals(folder)) {
                                continue;
                            }
                            Object item = walkingDir.computeIfAbsent(folder, k -> new HashMap<String, Object>());
                            if (item instanceof Map) {
                                walkingDir = (Map<String, Object>) item;
                            }
                        }
                    }
                } else {
                    String[] s = line.split(" ");
                    if (line.startsWith("dir")) {
                        if (!walkingDir.containsKey(s[1])) {
                            walkingDir.put(s[1], new HashMap<String, Object>());
                        }
                    } else if (Character.isDigit(line.charAt(0))) {
                        if (!walkingDir.containsKey(s[1])) {
                            walkingDir.put(s[1], s[0]);
                        }
                    }
                }
            }
        }

        int size = getDirectorySize(rootDir);

        System.out.println("Total Size: " + size);
        System.out.println("Answer: " + sumDirectorySize);
    }

    private static int getDirectorySize(Map dir) {
        int size = 0;

        for (Object o : dir.values()) {
            if (o instanceof Map) {
                size += getDirectorySize((Map) o);
            } else {
                size += Integer.parseInt(o.toString());
            }
        }
        if (size <= 100000) {
            sumDirectorySize += size;
        }
        return size;
    }
}