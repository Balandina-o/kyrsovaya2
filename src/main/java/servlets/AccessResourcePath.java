package servlets;

import java.nio.file.Path;

public enum AccessResourcePath {
    PATH_resources;
    private Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = Path.of(path);
    }
    public void setPath(Path path){
        this.path=path;
    }

    @Override
    public String toString() {
        return ""+ path;
    }
}
