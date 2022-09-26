package hexlet.code.formatters;

import hexlet.code.ItemData;


import java.util.Map;
import java.util.StringJoiner;

public class Stylish {
    public static String getFormattedString(Map<String, ItemData> diffMap) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("{");
        for (Map.Entry<String, ItemData> entry : diffMap.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case "without" -> joiner.add("    " + entry.getKey() + ": " + entry.getValue().getLast());
                case "modified" -> {
                    joiner.add("  - " + entry.getKey() + ": " + entry.getValue().getFirst());
                    joiner.add("  + " + entry.getKey() + ": " + entry.getValue().getLast());
                }
                case "removed" -> joiner.add("  - " + entry.getKey() + ": " + entry.getValue().getFirst());
                case "added" -> joiner.add("  + " + entry.getKey() + ": " + entry.getValue().getLast());
                default -> throw new RuntimeException(entry.getKey() + " out of without/modified/removed/added");
            }
        }
        joiner.add("}");
        return joiner.toString();
    }
}
