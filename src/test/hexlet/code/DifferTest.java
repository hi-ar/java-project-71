package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

class DifferTest {
    // generate исключение
    //generate принимает два стринга, возвр дифф

    private String json1 = "{\n" +
            "  \"host\": \"hexlet.io\",\n" +
            "  \"timeout\": 50,\n" +
            "  \"proxy\": \"123.234.53.22\",\n" +
            "  \"follow\": false\n" +
            "}";
    private String json2 = "{\n" +
            "  \"timeout\": 20,\n" +
            "  \"verbose\": true,\n" +
            "  \"host\": \"hexlet.io\"\n" +
            "}";
    private String diff1 = "{\n" +
            "- follow: false\n" +
            "host: hexlet.io\n" +
            "- proxy: 123.234.53.22\n" +
            "- timeout: 50\n" +
            "+ timeout: 20\n" +
            "+ verbose: true\n" +
            "}\n";
    private String diff2 = "{\n" +
            "follow: false\n" +
            "host: hexlet.io\n" +
            "proxy: 123.234.53.22\n" +
            "timeout: 50\n" +
            "}\n";

    private String notJson = "Hello world!";

    private String empty = "";

    @Test
    void generateTest() throws IOException { //basic functionality
        assertThat(Differ.generate(json1, json2)).isEqualTo(diff1);
        assertThat(Differ.generate(json1, json1)).isEqualTo(diff2);
    }

    @Test
    void exceptionsTest() {
        var thrown = catchThrowable(() -> Differ.generate(json1)); //проброс исключения в перемен
        assertThat(thrown).isInstanceOf(IOException.class);
    }
}
