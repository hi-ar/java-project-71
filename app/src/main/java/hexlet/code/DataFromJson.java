package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class DataFromJson {
    public static Map<String, Object> getData(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> result = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        return result;
    }
}
