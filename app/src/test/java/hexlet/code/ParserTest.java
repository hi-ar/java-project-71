package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
    private Map<String, String> emptyMap = new HashMap<>();
    private Map<String, Object> correctMapObj = new LinkedHashMap<>();
    @Test
    void parserTest() throws IOException { //basic functionality
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("nestedKey", "value");
        nestedMap.put("isNested", true);
        correctMapObj.put("obj1", nestedMap);
        correctMapObj.put("", null);
        correctMapObj.put("list.of_chars", List.of("a", "b", "c"));
        correctMapObj.put("0", List.of(0, 1, 2));

        String empty = "empty.json";
        String json = "parser.test.json";
        String yml = "parser.test.yml";

        assertThat(Parser.parse(getAbsolutePaths(json))).isEqualTo(correctMapObj);
        assertThat(Parser.parse(getAbsolutePaths(yml))).isEqualTo(correctMapObj);
        assertThat(Parser.parse(getRelativePaths(empty))).isEqualTo(emptyMap);
    }

    @Test
    void exceptionsTest() {
        String helloworld = "helloworld.txt";
        var thrown1 = catchThrowable(() -> Parser.parse(getAbsolutePaths(helloworld))); //wrong type
        assertThat(thrown1).isInstanceOf(RuntimeException.class);
        var thrown2 = catchThrowable(() -> Parser.parse(null)); //null
        assertThat(thrown2).isInstanceOf(NullPointerException.class);
    }
}
