package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(Path filePath) throws IOException {
        String fileContent = Utils.getFileContent(filePath);
        String fileExtension = Utils.getFileExtension(filePath);

        return switch (fileExtension) {
            case "yaml", "yml" -> DataFromYaml.getData(fileContent);
            case "json" -> DataFromJson.getData(fileContent);
            default -> throw new RuntimeException("wrong file extension (available json/yaml)");
        };
    }
}
