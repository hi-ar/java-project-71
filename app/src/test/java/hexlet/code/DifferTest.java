package hexlet.code;

import org.junit.jupiter.api.Test;

//import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class DifferTest {

    final Path getAbsolutePaths(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    final Path getRelativePaths(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName);
    }

    private String empty = "empty.json";
    private String helloworld = "helloworld.txt";
    private String jsn1 = "with.nested.values.json"; //dots for checking correct file extension definition
    private String yml1 = "with.nested.123.yml";
    private String jsn2 = "with.nested.values-2.json";

    @Test
    void stylishBasicTest() throws Exception {
        //jsn absolute, jsn relative, stylish:
        assertThat(Differ.generate(getAbsolutePaths(jsn1).toString(), getRelativePaths(jsn2).toString()))
                .isEqualTo(Utils.getFileContent(getAbsolutePaths("compare_result_sty_1-2.txt")));
        //yml absolute, jsn relative, stylish:
        assertThat(Differ.generate(getAbsolutePaths(yml1).toString(), getRelativePaths(jsn2).toString()))
                .isEqualTo(Utils.getFileContent(getAbsolutePaths("compare_result_sty_1-2.txt")));
        //comparing with empty:
        assertThat(Differ.generate(getAbsolutePaths(empty).toString(), getRelativePaths(jsn2).toString()))
                .isEqualTo(Utils.getFileContent(getAbsolutePaths("compare_result_sty_empty-2.txt")));
        assertThat(Differ.generate(getAbsolutePaths(empty).toString(), getRelativePaths(empty).toString()))
                .isEqualTo("{\n}");
    }

    @Test
    void plainBasicTest() throws Exception {
        assertThat(Differ.generate(getAbsolutePaths(yml1).toString(), getRelativePaths(jsn2).toString(), "plain"))
                .isEqualTo(Utils.getFileContent(getAbsolutePaths("compare_result_pln_1-2.txt")));
    }

    @Test
    void jsonBasicTest() throws Exception {
        assertThat(Differ.generate(getAbsolutePaths(yml1).toString(), getRelativePaths(jsn2).toString(), "json"))
                .isEqualTo(Utils.getFileContent(getAbsolutePaths("compare_result_jsn_1-2.json")));
//        FileWriter writer = new FileWriter("compare_result_jsn_1-2.json");
//        writer.write(Differ.generate(getAbsolutePaths(yml1).toString(), getRelativePaths(jsn2).toString(), "json"));
//        writer.close();
    }

    @Test
    void exceptionsTest() {
        //wrong extension:
        var thrown1 = catchThrowable(() -> Differ.generate(getAbsolutePaths(helloworld).toString(),
                getRelativePaths(jsn1).toString()));
        assertThat(thrown1).isInstanceOf(RuntimeException.class);
        //single instead of a pair:
        var thrown2 = catchThrowable(() -> Differ.generate(getAbsolutePaths(yml1).toString(), null));
        assertThat(thrown2).isInstanceOf(NullPointerException.class);
    }
}
