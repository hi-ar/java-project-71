package hexlet.code;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String fileExtension, String fileContent) throws IOException {

        return switch (fileExtension) {
            case "yaml", "yml" -> YamlSupplier.getData(fileContent);
            case "json" -> JsonSupplier.getData(fileContent);
            default -> throw new RuntimeException("wrong file extension (available json/yaml)");
        };
    }
}
