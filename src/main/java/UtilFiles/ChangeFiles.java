package UtilFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ChangeFiles implements ReadWriter{
    @Override
    public List<String> readFileAsString(String path) {
        List<String> strings= new ArrayList<>();
        try {
            strings.addAll(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    @Override
    public void write(String path) {

    }
    public  Map<String,String> readFileAsPair(Path path){
        Map<String,String> map = new HashMap<>();
        for (var x : readFileAsString((String.valueOf(path)))) {
            String[] xx =x.split(";");
            map.put(xx[0],xx[1]);
        }
        return map;
    }
}
