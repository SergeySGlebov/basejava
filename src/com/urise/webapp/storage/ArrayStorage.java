package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int size = 0;

    private Resume[] storage = new Resume[10000];

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if (size < storage.length) {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int id = getIdOfElement(uuid);
        if (id >= 0) {
            return storage[id];
        }
        return null;
    }

    public void delete(String uuid) {
        int id = getIdOfElement(uuid);
        if (id >= 0) {
            size--;
            for (int i = id; i < size; i++) {
                storage[i] = storage[id + 1];
            }
        }
    }

    public int getIdOfElement(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumeArray = new Resume[size];
        System.arraycopy(storage, 0, resumeArray, 0, size);
        return resumeArray;
    }

    public int size() {
        return size;
    }
}
