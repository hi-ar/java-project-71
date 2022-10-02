package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;

public class Differ {

    public static String generate(Path file1, Path file2, String format) throws IOException {
        return DiffBuilder.generate(Parser.parse(file1), Parser.parse(file2), format);
    }
    public static String generate(Path file1, Path file2) throws IOException {
        return generate(file1, file2, "stylish");
    }
}
