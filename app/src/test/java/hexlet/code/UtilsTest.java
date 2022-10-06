package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {

    final Path getRelativePaths(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName);
    }

    private String json = "with.nested.values-2.json";
    private String yaml = "with.nested.123.yml";
    private final double numPI = 3.1415926;

    @Test
    void extensionTest() {
        assertThat(Utils.getFileExtension(getRelativePaths(json))).isEqualTo("json");
        assertThat(Utils.getFileExtension(getRelativePaths(yaml))).isEqualTo("yml");
    }

    @Test
    void compareTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("", null);
        map.put("1", numPI);

        assertThat(Utils.compare(null, null)).isTrue();
        assertThat(Utils.compare(null, "null")).isFalse();
        assertThat(Utils.compare(0, "0")).isFalse();
        assertThat(Utils.compare(numPI, numPI)).isTrue();
        assertThat(Utils.compare(map, map)).isTrue();
    }
}
