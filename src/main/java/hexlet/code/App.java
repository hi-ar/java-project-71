package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.concurrent.Callable;

/*

 */
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 911.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {
    @Parameters(index = "0", description = "path to first file")
    private Path file1;

    @Parameters(index = "1", description = "path to second file")
    private Path file2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private static String format = "stylish";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public final String call() throws Exception {
        return call(file1, file2);
    }

    public static String call(Path file1, Path file2) throws Exception { // для тестрирования
        try {
            return Differ.generate(Parser.parse(file1), Parser.parse(file2), format);
        } catch (Exception e) {
            return e.toString();
        }
    }


}
