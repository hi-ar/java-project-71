package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 911.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    private Path file1;

    @Parameters(index = "1", description = "path to second file")
    private Path file2;

    @Option(names = {"-f", "--format"}, description = "output format (stylish/plain/json) [default: stylish]")
    private String format = "stylish";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public final Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(file1, file2, format));
            return 0;
        } catch (Exception e) {
            System.out.println("Wrong path to file, or file doesn't exists: " + e.getMessage());
            return 1;
        }
    }
}
