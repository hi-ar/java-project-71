package hexlet.code;

import java.util.*;
import java.util.stream.Stream;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        return generate(map1, map2, "stylish");
    }
    public static String generate(Map<String, Object> map1, Map<String, Object> map2, String formatName) {
        Set<String> sortedKeychain = new TreeSet<>(Comparator.naturalOrder());
        Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .forEach(sortedKeychain::add);

        Map<String, Object> internalRep = new LinkedHashMap<>(); //"внутреннее представление"

        for (String key : sortedKeychain) {
            if (map1.keySet().contains(key) && map2.keySet().contains(key) && map1.get(key)
                    .equals(map2.get(key))) {
                internalRep.put(key, map1.get(key));
            } else if (map1.keySet().contains(key) && map2.keySet().contains(key)) {
                internalRep.put("- " + key, map1.get(key));
                internalRep.put("+ " + key, map2.get(key));
            } else if (map1.keySet().contains(key) && !map2.keySet().contains(key)) {
                internalRep.put("- " + key, map1.get(key));
            } else {
                internalRep.put("+ " + key, map2.get(key));
            }
        }
        return Formatter.getFormattedString(internalRep, formatName);
    }
}
