package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ItemData;


import java.io.IOException;
import java.util.Map;
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Json {
    public static String getFormattedString(Map<String, ItemData> diffMap) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        Date date = new Date();
//        SimpleDateFormat filename = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
//        mapper.writeValue(new File(filename.format(date) + ".json"), diffMap); //нужно ли создавать новый json файл?
        return mapper.writeValueAsString(diffMap);
    }
}
