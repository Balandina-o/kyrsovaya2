package UtilFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PairFromFile implements ReadFile {
    /**
     * Метод чтение строки из файла
     *
     * @param path - путь
     * @return Список строк
     */
    @Override
    public List<String> readFileAsString(String path) {
        List<String> strings = new ArrayList<>();
        try {
            strings.addAll(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    /**
     * @param path - Путь
     * @return - Пары элементов разделенные ;
     */
    //TODO порядок . поменять на LINKED MAP
    public LinkedHashMap<String, String> readFileAsPair(Path path) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (var x : readFileAsString((String.valueOf(path)))) {
            String[] xx = x.split(";");
            map.put(xx[0], xx[1]);
        }
        return map;
    }
}
