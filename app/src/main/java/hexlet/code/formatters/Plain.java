package hexlet.code.formatters;

import hexlet.code.ItemData;

import java.util.Map;
import java.util.StringJoiner;

public class Plain {
    public static String getFormattedString(Map<String, ItemData> diffMap) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Map.Entry<String, ItemData> entry : diffMap.entrySet()) {
            switch (entry.getValue().getStatus()) {
                case "unchanged":
                    continue;
                case "changed":
                    joiner.add("Property '" + entry.getKey() + "' was updated. From "
                            + toString(entry.getValue().getFirst())
                            + " to " + toString(entry.getValue().getLast()));
                    break;
                case "removed":
                    joiner.add("Property '" + entry.getKey() + "' was removed");
                    break;
                case "added":
                    joiner.add("Property '" + entry.getKey() + "' was added with value "
                            + toString(entry.getValue().getLast()));
                    break;
                default:
                    throw new RuntimeException(entry.getKey() + " out of without/modified/removed/added (Plain)");
            }
        }
        return joiner.toString();
    }

    private static Object toString(Object value) {
        if (!(value instanceof String
                //если объект не из перечисленных, а любой неизвестный: List, Map, Car, User (втч без .toString()
                || value instanceof Integer
                || value instanceof Boolean
                || value == null)) {
            return new String("[complex value]");
        } else if (value instanceof String) {
            return new String("'" + value + "'");
        }
        return value;
    }
}
