package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String file1, String file2) throws IOException {
        Map<String, String> data1 = getMap(file1);
        Map<String, String> data2 = getMap(file2);

        Set<String> sortedKeychain = new TreeSet<>(Comparator.naturalOrder());

        Stream.concat(data1.keySet().stream(), data2.keySet().stream())
                .forEach(sortedKeychain::add);

        Map<String, String> result = new LinkedHashMap<>();

        for (String key : sortedKeychain) {
            if (data1.keySet().contains(key) && data2.keySet().contains(key) && data1.get(key)
                    .equals(data2.get(key))) {
                result.put(key, data1.get(key));
            } else if (data1.keySet().contains(key) && data2.keySet().contains(key)) {
                result.put("- " + key, data1.get(key));
                result.put("+ " + key, data2.get(key));
            } else if (data1.keySet().contains(key) && !data2.keySet().contains(key)) {
                result.put("- " + key, data1.get(key));
            } else {
                result.put("+ " + key, data2.get(key));
            }
        }

        if (App.getFormat().equals("stylish")) {
            return stylish(result);
        }
        return "";
    }

    private static String stylish(Map<String, String> map) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("{");
        for (Map.Entry entry : map.entrySet()) {
            joiner.add(entry.getKey() + ": " + entry.getValue());
        }
        joiner.add("}");
        return joiner.toString();
    }

    private static Map<String, String> getMap(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> result = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
        });
        return result;
    }
}
