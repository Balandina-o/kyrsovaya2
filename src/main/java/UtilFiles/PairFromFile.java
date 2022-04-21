package UtilFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
/**
 * @author Artyom Zlygostev
 * @version 1.0
 *<br> Реализация Чтения Из файла Пар и Строк
 */
public class PairFromFile implements ReadFile {
    /**
     * Метод чтение строки из файла
     *
     * @param path - Путь до файла считывания
     * @return прочитанный список строк
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
     * @param path - Путь до файла считывания
     * @return - Пары элементов разделенные ";"
     */
    @Override
    public LinkedHashMap<String, String> readFileAsPair(String path) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (var line : readFileAsString((path))) {
            String[] xx = line.split(";");
            map.put(xx[0], xx[1]);
        }
        return map;
    }
}
