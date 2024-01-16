package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int size = 0;

    private Resume[] storage = new Resume[10000];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int id = getIdOfElement(r.getUuid());
        if (id >= 0) {
            storage[id] = r;
        } else {
            System.out.println("ERROR: resume " + r.getUuid() + " does not exist in storage");
        }
    }

    public void save(Resume r) {
        int id = getIdOfElement(r.getUuid());
        if (id < 0) {
            if (size < storage.length) {
                storage[size++] = r;
            }
        } else {
            System.out.println("ERROR: resume " + r.getUuid() + " already exist in storage");
        }
    }

    public Resume get(String uuid) {
        int id = getIdOfElement(uuid);
        if (id >= 0) {
            return storage[id];
        } else {
            System.out.println("ERROR: resume " + uuid + " does not exist in storage");
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
        } else {
            System.out.println("ERROR: resume " + uuid + " does not exist in storage");
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
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
