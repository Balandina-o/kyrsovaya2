package servlets;

import java.nio.file.Path;

/**
 * Класс для хранения пути к папки ресурсов
 */

public enum AccessResourcePath {
    PATH_resources;
    private Path path = Path.of("./");

    public Path getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = Path.of(path);
    }
    public void setPath(Path path){
        if(path!=null) {
            this.path = path;
        }
    }

    @Override
    public String toString() {
        return ""+ path;
    }
}
