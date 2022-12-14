import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzle2022Day13Part1 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("resources/input2022_13");

        List<Packet> packetsLeft = new ArrayList<>();
        List<Packet> packetsRight = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                packetsLeft.add(processPacket(reader.readLine().toCharArray()));
                packetsRight.add(processPacket(reader.readLine().toCharArray()));
                reader.readLine(); // Ignore empty line
            }
        }

        int sumOfInOrder = 0;

        for (int i = 0; i < packetsLeft.size(); i++) {
            Packet packet = packetsLeft.get(i);
            Boolean inOrder = packet.isInOrder(packetsRight.get(i));
            if (inOrder == Boolean.TRUE) {
                sumOfInOrder += i + 1;
            }
        }
        System.out.println("Answer: " + sumOfInOrder);
    }

    private static Packet processPacket(char[] chars) {
        Packet packet = new Packet();

        StringBuilder sb = new StringBuilder();
        Packet activeSubPacket = packet;
        for (int i = 1; i < chars.length; i++) { // skipping first bracket
            switch (chars[i]) {
                case '[' -> {
                    Packet subPacket = new Packet(activeSubPacket);
                    activeSubPacket.add(subPacket);
                    activeSubPacket = subPacket;
                    sb = new StringBuilder();
                }
                case ',' -> {
                    String s = sb.toString();
                    if (!s.isEmpty()) {
                        activeSubPacket.add(Integer.parseInt(s));
                    }
                    sb = new StringBuilder();
                }
                case ']' -> {
                    String s = sb.toString();
                    if (!s.isEmpty()) {
                        activeSubPacket.add(Integer.parseInt(s));
                    }
                    sb = new StringBuilder();
                    activeSubPacket = activeSubPacket.parent;
                }
                default -> sb.append(chars[i]); // digits
            }
        }
        return packet;
    }

    static class Packet extends ArrayList<Object> {
        Packet parent = null;

        Packet(Packet parent) {
            this.parent = parent;
        }

        Packet() { // parent of the top packet is itself
        }

        Boolean isInOrder(Packet other) {
            for (int i = 0; i < size(); i++) {
                Object o = get(i);
                if (i == other.size()) {
                    return false;
                }
                Object o2 = other.get(i);
                if (o instanceof Packet subPacket) {
                    Boolean isInOrder;
                    if (o2 instanceof Packet otherSubPacket) {
                        isInOrder = subPacket.isInOrder(otherSubPacket);
                    } else if (o2 instanceof Integer otherInteger) {
                        Packet replacingSubPacket = new Packet();
                        replacingSubPacket.add(otherInteger);
                        other.set(i, replacingSubPacket);
                        isInOrder = subPacket.isInOrder(replacingSubPacket);
                    } else {
                        throw new RuntimeException("o2 is not of instance Packet nor Integer!");
                    }
                    if (isInOrder != null) {
                        return isInOrder;
                    }
                } else if (o instanceof Integer integer) {
                    if (o2 instanceof Packet otherSubPacket) {
                        Packet replacingSubPacket = new Packet();
                        replacingSubPacket.add(integer);
                        set(i, replacingSubPacket);
                        Boolean isInOrder = replacingSubPacket.isInOrder(otherSubPacket);
                        if (isInOrder != null) {
                            return isInOrder;
                        }
                    } else if (o2 instanceof Integer otherInteger) {
                        if (integer > otherInteger) {
                            return false;
                        } else if (integer < otherInteger) {
                            return true;
                        }
                    } else {
                        throw new RuntimeException("o2 is not of instance Packet nor Integer!");
                    }
                }
            }
            if (size() < other.size()) { // all equal, but left is shorter, so this is sorted
                return true;
            }
            return null;
        }
    }
}
