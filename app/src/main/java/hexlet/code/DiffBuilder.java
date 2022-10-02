package hexlet.code;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class DiffBuilder {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2, String format)
            throws IOException {
        Set<String> sortedKeychain = new TreeSet<>(Comparator.naturalOrder());
        Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .forEach(sortedKeychain::add);

        Map<String, ItemData> diffMap = new LinkedHashMap<>(); //"внутреннее представление"

        for (String key : sortedKeychain) {
            if (key == null) {
                key = new String("null");
            }
            if (map1.containsKey(key) && map2.containsKey(key) && compare(map1.get(key), map2.get(key))) { //без изм
                diffMap.put(key, new ItemData("without", map1.get(key), map2.get(key)));
            } else if (map1.containsKey(key) && map2.containsKey(key)) { //изменилось значение
                diffMap.put(key, new ItemData("modified", map1.get(key), map2.get(key)));
            } else if (map1.containsKey(key) && !map2.containsKey(key)) { //удален
                diffMap.put(key, new ItemData("removed", map1.get(key), map1.get(key)));
            } else {  // добавлен
                diffMap.put(key, new ItemData("added", map2.get(key), map2.get(key)));
            }
        }
        return Formatter.getFormattedString(diffMap, format);
    }

    private static boolean compare(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        } else if (value1 == null || value2 == null) {
            return false;
        }
        return value1.equals(value2);
    }
}
