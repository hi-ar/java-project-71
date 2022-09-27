package hexlet.code;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class DiffBuilder {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2, String format) {
        Set<String> sortedKeychain = new TreeSet<>(Comparator.naturalOrder());
        Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .forEach(sortedKeychain::add);

        Map<String, ItemData> diffMap = new LinkedHashMap<>(); //"внутреннее представление"

        for (String key : sortedKeychain) {
            if (map1.keySet().contains(key) && map2.keySet().contains(key) && map1.get(key)
                    .equals(map2.get(key))) { //без изменений
                diffMap.put(key, new ItemData<>("without", map1.get(key), map2.get(key)));
            } else if (map1.keySet().contains(key) && map2.keySet().contains(key)) { //изменилось значение
                diffMap.put(key, new ItemData<>("modified", map1.get(key), map2.get(key)));
            } else if (map1.keySet().contains(key) && !map2.keySet().contains(key)) { //удален
                diffMap.put(key, new ItemData<>("removed", map1.get(key), map1.get(key)));
            } else {  // добавлен
                diffMap.put(key, new ItemData<>("added", map2.get(key), map2.get(key)));
            }
        }
        return Formatter.getFormattedString(diffMap, format);
    }
}
