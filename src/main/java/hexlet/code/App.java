package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Callable;


/*

 */
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 911.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {
    @Parameters(index = "0", description = "path to first file")
    private static File filePath1; //added static

    @Parameters(index = "1", description = "path to second file")
    private static File filePath2; //added static
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
        //System.out.println("Begin");
    }
    @Override
    public String call() throws Exception {
        byte[] charsFile1 = Files.readAllBytes(filePath1.toPath());
        byte[] charsFile2 = Files.readAllBytes(filePath2.toPath());
        String strFile1 = new String(charsFile1);
        String strFile2 = new String(charsFile2);
        return Differ.generate(strFile1, strFile2);
    }

//    private static Path getPath(String fileName) {
//        return Paths.get("src", "test", "fixtures", fileName).toAbsolutePath().normalize();
//    }
//    private static String readFixture(String fileName) throws Exception {
//        Path filePath = getPath(fileName);
//        return Files.readString(filePath).trim();
//    }
}