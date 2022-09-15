package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    //открыие по относительной ссылке
    // открытие по прямой ссылке
    //чтение файла в массив чаров
    // исключение
    private static String data1;
    private static String data2;

    @BeforeAll
    public static void beforeAll() throws Exception {
        data1 = readFixture("file1.json");
        data2 = readFixture("file2.json");
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    @Test
    void testGetForvardedVariables() {
        String result1 = "";
        String expected1 = "";
        assertThat(result1).isEqualTo(expected1);
    }
}
