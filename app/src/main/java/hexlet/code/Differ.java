package hexlet.code;

import hexlet.code.formatters.Formatter;
import java.io.IOException;
import java.util.Map;

public class Differ {

    public static String generate(String file1Path, String file2Path, String format) throws IOException {
        Map<String, Object> firstMap = DataSupplier.getData(file1Path);
        Map<String, Object> secondMap = DataSupplier.getData(file2Path);

        Map<String, ItemData> diffMap = DiffBuilder.getDiff(firstMap, secondMap);

        return Formatter.getFormattedString(diffMap, format);
    }
    public static String generate(String file1, String file2) throws IOException {
        return generate(file1, file2, "stylish");
    }
}
