package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static Map<String, String> parse(Path file) throws IOException {
        String fileContent = Files.readString(file);
        String fileExtension = file.getFileName().toString().split("\\.")[1];

        if (fileExtension.equals("yaml") || fileExtension.equals("yml")) {
            return getMapFromYML(fileContent);
        }
        return getMapFromJson(fileContent);
    }

    private static Map<String, String> getMapFromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> result = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
        });
        return result;
    }
    private static Map<String, String> getMapFromYML(String yaml) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Map<String, String> result = objectMapper.readValue(yaml, new TypeReference<Map<String, String>>() {
        });
        return result;
    }
}
