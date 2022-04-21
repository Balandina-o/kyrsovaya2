package UtilFiles;


import java.util.LinkedHashMap;
import java.util.List;
/**
 * @author Artyom Zlygostev
 * @version 1.0
 * <br>Интерфейс для чтения Строк и Пар
 */
public interface ReadFile {
    List<String> readFileAsString(String path);

    LinkedHashMap<String, String> readFileAsPair(String path);
}
