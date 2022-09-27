package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class DifferTest {

    final Path getAbsolutePaths(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    final Path getRelativePaths(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName);
    }

    private String correctCompare = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 0\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    private String correctCompareEmpty = "{\n"
            + "  + host: hexlet.io\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";
    private String correctComparePlain1 = "Property 'follow' was removed\n"
            + "Property 'proxy' was removed\n"
            + "Property 'timeout' was updated. From 0 to 20\n"
            + "Property 'verbose' was added with value true";

    private String json1 = "file1.json";
    private String json2 = "file2.json";
    private String empty = "empty.json";
    private String yaml1 = "file1.yml";
    private String yaml2 = "file2.yaml";
    private String helloworld = "helloworld.txt";
    @Test
    void appTest() throws Exception { //basic functionality, stylish
        assertThat(Differ.generate(getAbsolutePaths(json1), getRelativePaths(json2))).isEqualTo(correctCompare);
        assertThat(Differ.generate(getAbsolutePaths(yaml1), getRelativePaths(yaml2))).isEqualTo(correctCompare);
        assertThat(Differ.generate(getAbsolutePaths(yaml1), getRelativePaths(json2))).isEqualTo(correctCompare);
        assertThat(Differ.generate(getAbsolutePaths(empty), getRelativePaths(json2))).isEqualTo(correctCompareEmpty);
        assertThat(Differ.generate(getAbsolutePaths(empty), getRelativePaths(empty))).isEqualTo("{\n}");
    // plain
        assertThat(Differ.generate(getAbsolutePaths(json1), getRelativePaths(yaml2), "plain"))
                .isEqualTo(correctComparePlain1);
    }

    @Test
    void exceptionsTest() {
        //testing with wrong type
        var thrown1 = catchThrowable(() -> Differ.generate(getAbsolutePaths(helloworld), getRelativePaths(json1)));
        assertThat(thrown1).isInstanceOf(JsonParseException.class);

        var thrown2 = catchThrowable(() -> Differ.generate(getAbsolutePaths(yaml1), null)); //null
        assertThat(thrown2).isInstanceOf(NullPointerException.class);
    }
}
