package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class DataFromYaml {
    public static Map<String, Object> getData(String yaml) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Map<String, Object> result = objectMapper.readValue(yaml, new TypeReference<Map<String, Object>>() {
        });
        return result;
    }
}
