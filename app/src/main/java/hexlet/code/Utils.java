package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    public static Path getAbsolutePaths(String fileName) {
        return Paths.get(fileName).toAbsolutePath().normalize();
    }
    public static String getFileContent(Path file) throws IOException {
        return Files.readString(file);
    }

    public static String getFileExtension(Path file) {
        String[] arrFileExtension = file.getFileName().toString().split("\\.");
        return arrFileExtension[arrFileExtension.length - 1]; //если в названии больше одной точки
    }

    public static boolean compare(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        } else if (value1 == null || value2 == null) {
            return false;
        }
        return value1.equals(value2);
    }
}
