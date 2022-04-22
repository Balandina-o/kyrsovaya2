package UtilFiles;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
/**
 * @author Artyom Zlygostev
 * @version 1.0
 * <br>Интерфейс для чтения Строк и Пар
 */
public interface ReadingFile {
    List<String> readFileAsString(String path);

    LinkedHashMap<String, String> readFileAsPair(String path);
    ArrayList<Triple<String, String, String>> readFileAsTriple(String path);
}
