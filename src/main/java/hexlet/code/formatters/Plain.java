package hexlet.code.formatters;

import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;

public class Plain {
    public String getFormattedString(Map<String, Object> map) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Map.Entry entry : map.entrySet()) {
            joiner.add("Property '" + entry.getKey() + "' : " + toString(entry.getValue())); //924 toStr что если на массиве вызывать туСтринг
        }
        return joiner.toString();
    }

    private static String toString(Object value) {
        if (value.getClass().isArray()) {
            return Arrays.toString((Object[]) value);
        }
        return value.toString();
    }
}
