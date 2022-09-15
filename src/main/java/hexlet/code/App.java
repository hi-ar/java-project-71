package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;

/*

 */
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 911.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {
    @Parameters(index = "0", description = "path to first file")
    private File filePath1;

    @Parameters(index = "1", description = "path to second file")
    private File filePath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private static String format = "stylish";
    public static String getFormat() {
        return format;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public final String call() throws Exception {
        byte[] charsFile1 = Files.readAllBytes(filePath1.toPath());
        byte[] charsFile2 = Files.readAllBytes(filePath2.toPath());
        String strFile1 = new String(charsFile1);
        String strFile2 = new String(charsFile2);
        System.out.println(Differ.generate(strFile1, strFile2));
        return "";
    }
}
