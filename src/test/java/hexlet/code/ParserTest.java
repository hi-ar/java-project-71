package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ParserTest {
    final Path getAbsolutePaths(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    final Path getRelativePaths(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName);
    }

    private Map<String, String> correctMap = new HashMap<>();
    private Map<String, String> emptyMap = new HashMap<>();

    @Test
    void parserTest() throws IOException { //basic functionality
        correctMap.put("follow", "false");
        correctMap.put("host", "hexlet.io");
        correctMap.put("proxy", "123.234.53.22");
        correctMap.put("timeout", "50");

        String json1 = "file1.json";
        String empty = "empty.json";
        String yaml1 = "file1.yml";
        assertThat(Parser.parse(getAbsolutePaths(json1))).isEqualTo(correctMap);
        assertThat(Parser.parse(getRelativePaths(json1))).isEqualTo(correctMap);
        assertThat(Parser.parse(getRelativePaths(empty))).isEqualTo(emptyMap);
        assertThat(Parser.parse(getAbsolutePaths(yaml1))).isEqualTo(correctMap);
        assertThat(Parser.parse(getRelativePaths(yaml1))).isEqualTo(correctMap);
    }

    @Test
    void exceptionsTest() {
        String helloworld = "helloworld.txt";
        var thrown1 = catchThrowable(() -> Parser.parse(getAbsolutePaths(helloworld))); //wrong type
        assertThat(thrown1).isInstanceOf(JsonParseException.class);
        var thrown2 = catchThrowable(() -> Parser.parse(null)); //null
        assertThat(thrown2).isInstanceOf(NullPointerException.class);
    }
}
