package hexlet.code;

import java.util.*;

public class Differ {
    public static String generate(String file1, String file2) {
        Map<String, String> mappedFile1 = getMap(file1);
        Map<String, String> mappedFile2 = getMap(file2);

        String result = mappedFile1.toString() + " second: " + mappedFile2.toString();
        return result;
    }
    private static Map<String, String> getMap(String file) {
        Map<String, String> result = new HashMap<>();

        Arrays.stream(file.split("\n"))
                .filter(str -> str.contains(":"))
                .map(str -> str.replaceAll("\"", ""))
                .map(str -> result.put(str.split(":")[0], str.split(":")[1]))
                .count(); //terminal operation to pulling stream iteration

        return result;
    }
}
