package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String getFormattedString(Map<String, Object> map, String formatName) {
        return switch (formatName) {
            case "stylish" -> Stylish.getFormattedString(map);
            case "plain" -> Plain.getFormattedString(map);
            default -> throw new RuntimeException("No such format available");
        };
    }
}
