package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.Map;

public class Formatter {
    public static String getFormattedString(Map<String, ItemData> diffMap, String formatName) throws IOException {
        return switch (formatName) {
            case "stylish" -> Stylish.getFormattedString(diffMap);
            case "plain" -> Plain.getFormattedString(diffMap);
            case "json" -> Json.getFormattedString(diffMap);
            default -> throw new RuntimeException(formatName + "- No such format available (stylish/plain)");
        };
    }
}
