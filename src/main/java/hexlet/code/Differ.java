package hexlet.code;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Differ {
    public static String generate(Map<String, String> map1, Map<String, String> map2) {
        Set<String> sortedKeychain = new TreeSet<>(Comparator.naturalOrder());

        Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .forEach(sortedKeychain::add);

        Map<String, String> result = new LinkedHashMap<>();

        for (String key : sortedKeychain) {
            if (map1.keySet().contains(key) && map2.keySet().contains(key) && map1.get(key)
                    .equals(map2.get(key))) {
                result.put(key, map1.get(key));
            } else if (map1.keySet().contains(key) && map2.keySet().contains(key)) {
                result.put("- " + key, map1.get(key));
                result.put("+ " + key, map2.get(key));
            } else if (map1.keySet().contains(key) && !map2.keySet().contains(key)) {
                result.put("- " + key, map1.get(key));
            } else {
                result.put("+ " + key, map2.get(key));
            }
        }
        return App.getFormat().equals("stylish") ? getStylishString(result) : "";
    }

    private static String getStylishString(Map<String, String> map) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("{");
        for (Map.Entry entry : map.entrySet()) {
            joiner.add(entry.getKey() + ": " + entry.getValue());
        }
        joiner.add("}");
        return joiner.toString();
    }
}
