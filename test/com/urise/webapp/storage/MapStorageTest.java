package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MapStorageTest extends AbstractArrayStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    @Ignore
    public void saveOverflow() {

    }

    @Override
    protected Resume[] sortResumes(Resume[] resumes) {
        Arrays.sort(resumes);
        return resumes;
    }
}