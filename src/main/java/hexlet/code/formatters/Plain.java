package hexlet.code.formatters;

import hexlet.code.ItemData;

import java.util.Map;
import java.util.StringJoiner;

public class Plain {
    public static String getFormattedString(Map<String, ItemData> diffMap) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Map.Entry<String, ItemData> entry : diffMap.entrySet()) {
            if (entry.getValue().getStatus().equals("without")) {
                continue;
            } else if (entry.getValue().getStatus().equals("modified")) {
                joiner.add("Property '" + entry.getKey() + "' was updated. From "
                        + toString(entry.getValue().getFirst())
                        + " to " + toString(entry.getValue().getLast()));
            } else if (entry.getValue().getStatus().equals("removed")) {
                joiner.add("Property '" + entry.getKey() + "' was removed");
            } else if (entry.getValue().getStatus().equals("added")) {
                joiner.add("Property '" + entry.getKey() + "' was added with value "
                        + toString(entry.getValue().getLast()));
            } else {
                throw new RuntimeException(entry.getKey() + " out of without/modified/removed/added (Plain)");
            }
        }
        return joiner.toString();
    }

    private static Object toString(Object value) {
        if (!(value instanceof String
                || value instanceof Integer
                || value instanceof Boolean)) {
            return new String("[complex value]");
        } else if (value instanceof String) {
            return new String("'" + value + "'");
        }
        return value;
    }
}
