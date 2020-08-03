package model.data.source;

import java.io.File;

public class LocalRepository {
    private final String fileName;
    private final File file;

    public LocalRepository(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }
}
