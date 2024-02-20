package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PathStorage extends AbstractPathStorage{
    private SerializeStrategy serializeStrategy;

    protected PathStorage(String directory, SerializeStrategy serializeStrategy) {
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
