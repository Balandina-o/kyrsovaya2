package UtilFiles;

import java.util.List;

public interface ReadWriter {
    List<String> readFileAsString(String path);
    void write(String path);
}
