package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void deleteImpl(int index, String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected Resume getImpl(int index, String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void saveImpl(int index, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected int getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    protected void updateImpl(int index, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
