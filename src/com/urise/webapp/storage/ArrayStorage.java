package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {

    private static final int STORAGE_LIMIT = 10000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int id = getIdOfElement(r.getUuid());
        if (needToExist(id, r.getUuid())) {
            storage[id] = r;
        }
    }

    public void save(Resume r) {
        int id = getIdOfElement(r.getUuid());
        if (doNotNeedToExist(id, r.getUuid())) {
            if (size < storage.length) {
                storage[size++] = r;
            }
        }
    }

    public Resume get(String uuid) {
        int id = getIdOfElement(uuid);
        if (needToExist(id, uuid)) {
            return storage[id];
        }
        return null;
    }

    public void delete(String uuid) {
        int id = getIdOfElement(uuid);
        if (needToExist(id, uuid)) {
            size--;
            for (int i = id; i < size; i++) {
                storage[i] = storage[id + 1];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIdOfElement(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean needToExist(int id, String uuid) {
        if (id >= 0) {
            return true;
        } else {
            System.out.println("ERROR: resume " + uuid + " does not exist in storage");
        }
        return false;
    }

    private boolean doNotNeedToExist(int id, String uuid) {
        if (id < 0) {
            return true;
        } else {
            System.out.println("ERROR: resume " + uuid + " already exist in storage");
        }
        return false;
    }
}
