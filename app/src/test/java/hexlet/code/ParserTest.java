package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
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
    private String empty = "empty.json";
    private String json = "parser.test.json";
    private String yml = "parser.test.yml";
    private Map<String, String> emptyMap = new HashMap<>();
    private static Map<String, Object> correctMapObj = new LinkedHashMap<>();
    @BeforeAll
    static void beforeAll() {
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put("nestedKey", "value");
        nestedMap.put("isNested", true);
        correctMapObj.put("obj1", nestedMap);
        correctMapObj.put("", null);
        correctMapObj.put("list.of_chars", List.of("a", "b", "c"));
        correctMapObj.put("0", List.of(0, 1, 2));
    }
    @Test
    void parserTest() throws IOException { //basic functionality
        assertThat(DataSupplier.getData(getAbsolutePaths(json).toString())).isEqualTo(correctMapObj);
        assertThat(DataSupplier.getData(getAbsolutePaths(json).toString())).isEqualTo(correctMapObj);
        assertThat(DataSupplier.getData(getAbsolutePaths(yml).toString())).isEqualTo(correctMapObj);
        assertThat(DataSupplier.getData(getRelativePaths(empty).toString())).isEqualTo(emptyMap);
    }
    @Test
    void dataSupplierTest() throws IOException {
        assertThat(DataSupplier.getData(getAbsolutePaths(json).toString())).isEqualTo(correctMapObj);
    }
    @Test
    void exceptionsTest() {
        String helloworld = "helloworld.txt";
        //wrong type
        var thrown1 = catchThrowable(() -> DataSupplier.getData(getAbsolutePaths(helloworld).toString()));
        assertThat(thrown1).isInstanceOf(RuntimeException.class);
        var thrown2 = catchThrowable(() -> DataSupplier.getData(null)); //null
        assertThat(thrown2).isInstanceOf(NullPointerException.class);
    }
}
