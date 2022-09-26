package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AppTest {

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
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";

    private String correctCompareEmpty = "{\n"
            + "  + host: hexlet.io\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}";
    private String json1 = "file1.json";
    private String json2 = "file2.json";
    private String empty = "empty.json";
    private String yaml1 = "file1.yml";
    private String yaml2 = "file2.yaml";
    private String helloworld = "helloworld.txt";
    @Test
    void appTest() throws Exception { //basic functionality
        assertThat(App.call(getAbsolutePaths(json1), getRelativePaths(json2))).isEqualTo(correctCompare);
        assertThat(App.call(getAbsolutePaths(yaml1), getRelativePaths(yaml2))).isEqualTo(correctCompare);
        assertThat(App.call(getAbsolutePaths(yaml1), getRelativePaths(json2))).isEqualTo(correctCompare);
        assertThat(App.call(getAbsolutePaths(empty), getRelativePaths(json2))).isEqualTo(correctCompareEmpty);
        assertThat(App.call(getAbsolutePaths(empty), getRelativePaths(empty))).isEqualTo("{\n}");
    }

    @Test
    void exceptionsTest() {
        //testing with wrong type
        var thrown1 = catchThrowable(() -> App.call(getAbsolutePaths(helloworld), getRelativePaths(json1)));
        assertThat(thrown1).isInstanceOf(JsonParseException.class);

        var thrown2 = catchThrowable(() -> App.call(getAbsolutePaths(yaml1), null)); //null
        assertThat(thrown2).isInstanceOf(NullPointerException.class);
    }
}
