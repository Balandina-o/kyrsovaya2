package UtilFiles;

import java.util.List;
@FunctionalInterface
public interface ReadFile {
    List<String> readFileAsString(String path);
}
