package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new LinkedList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveImpl(int index, Resume r) {
        storage.add(r);
    }

    @Override
    public Resume getImpl(int index) {
        return storage.get(index);
    }

    @Override
    public void deleteImpl(int index) {
        storage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void updateImpl(int index, Resume r) {
        storage.set(index, r);
    }
}
