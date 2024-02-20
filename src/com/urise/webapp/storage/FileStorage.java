package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class FileStorage extends AbstractFileStorage{
    private SerializeStrategy serializeStrategy;

    protected FileStorage(File directory, SerializeStrategy serializeStrategy) {
        super(directory);
        this.serializeStrategy = serializeStrategy;
    }

    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        serializeStrategy.saveToFile(r, os);
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        return serializeStrategy.readFromFile(is);
    }
}
