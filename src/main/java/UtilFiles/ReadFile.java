package UtilFiles;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

public interface ReadFile {
    List<String> readFileAsString(String path);

    LinkedHashMap<String, String> readFileAsPair(Path path);
}
