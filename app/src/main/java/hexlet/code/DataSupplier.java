package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class DataSupplier {
    public static Map<String, Object> getData(String filePath) throws IOException { //one of the options: data fom parse
        Path absolutePaths = Utils.getAbsolutePaths(filePath);
        return Parser.parse(absolutePaths);
    }

}
