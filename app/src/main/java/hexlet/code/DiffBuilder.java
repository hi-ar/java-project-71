package hexlet.code;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static hexlet.code.Utils.compare;

public class DiffBuilder {
    public static Map<String, ItemData> getDiff(Map<String, Object> map1, Map<String, Object> map2)
            throws IOException {
        Set<String> sortedKeychain = new TreeSet<>(); //Comparator.naturalOrder()
        Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .map(key -> key == null ? new String("null") : key)
                .forEach(sortedKeychain::add);

        Map<String, ItemData> diffMap = new LinkedHashMap<>(); //"внутреннее представление"

        for (String key : sortedKeychain) {
            if (map1.containsKey(key) && map2.containsKey(key) && compare(map1.get(key), map2.get(key))) { //без изм
                diffMap.put(key, new ItemData("unchanged", map1.get(key), map2.get(key)));
            } else if (map1.containsKey(key) && map2.containsKey(key)) { //изменен
                diffMap.put(key, new ItemData("changed", map1.get(key), map2.get(key)));
            } else if (map1.containsKey(key) && !map2.containsKey(key)) { //удален
                diffMap.put(key, new ItemData("removed", map1.get(key), map1.get(key)));
            } else {  // добавлен
                diffMap.put(key, new ItemData("added", map2.get(key), map2.get(key)));
            }
        }
        return diffMap;
    }
}
