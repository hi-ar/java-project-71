package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String getFormattedString(Map<String, ItemData> diffMap, String formatName) {
        return switch (formatName) {
            case "stylish" -> Stylish.getFormattedString(diffMap);
            case "plain" -> Plain.getFormattedString(diffMap);
            default -> throw new RuntimeException(formatName + "- No such format available (stylish/plain)");
        };
    }
}
