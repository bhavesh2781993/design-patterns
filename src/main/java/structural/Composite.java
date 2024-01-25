package structural;

import java.util.ArrayList;
import java.util.List;

public class Composite {
    public static void main(String[] args) {
        File border = new File();
        border.filename = "Border";

        Directory comedyMovies = new Directory();
        comedyMovies.directoryName = "comedy_movies";
        File gaddar = new File();
        gaddar.filename = "Gaddar";
        comedyMovies.add(gaddar);

        Directory movies = new Directory();
        movies.add(border);
        movies.add(comedyMovies);

        movies.ls();
    }

}

interface FileSystem {
    void ls();
}

class File implements FileSystem {
    String filename;

    @Override
    public void ls() {
        System.out.println("File: " + filename);
    }
}

class Directory implements FileSystem {
    String directoryName;
    List<FileSystem> fileSystems;

    public void add(FileSystem fileSystem) {
        if (fileSystems == null) {
            fileSystems = new ArrayList<>();
        }
        fileSystems.add(fileSystem);
    }

    @Override
    public void ls() {
        System.out.println("Directory: " + directoryName);
        fileSystems.forEach(FileSystem::ls);
    }
}
