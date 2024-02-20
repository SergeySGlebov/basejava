package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializeStrategy {
    void saveToFile(Resume r, OutputStream os) throws IOException;
    Resume readFromFile(InputStream is) throws IOException;
}
