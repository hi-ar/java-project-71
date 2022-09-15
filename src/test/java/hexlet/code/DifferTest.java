package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

class DifferTest {
    // generate исключение
    //generate принимает два стринга, возвр дифф

    private String json1 = "{\n"
            + "  \"host\": \"hexlet.io\",\n"
            + "  \"timeout\": 50,\n"
            + "  \"proxy\": \"123.234.53.22\",\n"
            + "  \"follow\": false\n"
            + "}";
    private String json2 = "{\n"
            + "  \"timeout\": 20,\n"
            + "  \"verbose\": true,\n"
            + "  \"host\": \"hexlet.io\"\n"
            + "}";
    private String emptyjson = "{\n"  // {"":null}
            + "\"\":null\n"
            + "}";
    private String diff1 = "{\n"
            + "- follow: false\n"
            + "host: hexlet.io\n"
            + "- proxy: 123.234.53.22\n"
            + "- timeout: 50\n"
            + "+ timeout: 20\n"
            + "+ verbose: true\n"
            + "}";
    private String diff2 = "{\n"
            + "follow: false\n"
            + "host: hexlet.io\n"
            + "proxy: 123.234.53.22\n"
            + "timeout: 50\n"
            + "}";
    private String diff3 = "{\n"
            + "- : null\n"
            + "+ follow: false\n"
            + "+ host: hexlet.io\n"
            + "+ proxy: 123.234.53.22\n"
            + "+ timeout: 50\n"
            + "}";

    @Test
    void generateTest() throws IOException { //basic functionality
        assertThat(Differ.generate(json1, json2)).isEqualTo(diff1);
        assertThat(Differ.generate(json1, json1)).isEqualTo(diff2);
        assertThat(Differ.generate(emptyjson, json1)).isEqualTo(diff3);
    }

    @Test
    void exceptionsTest() {
        var thrown1 = catchThrowable(() -> Differ.generate(json1, "hello")); //wrong type, not json
        assertThat(thrown1).isInstanceOf(JsonParseException.class);
        var thrown2 = catchThrowable(() -> Differ.generate(json1, null)); //null
        assertThat(thrown2).isInstanceOf(NullPointerException.class);
    }
}
