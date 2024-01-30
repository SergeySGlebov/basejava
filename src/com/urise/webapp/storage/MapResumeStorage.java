package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private Map<Resume, Object> storage = new HashMap<>();

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((Resume) searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put(r, null);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        for (Map.Entry<Resume, Object> pair : storage.entrySet()) {
            if (pair.getKey().getUuid().equals(uuid)) {
                return pair.getKey();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.remove((Resume) searchKey);
        storage.put(r, null);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        Resume[] resumes = storage.keySet().toArray(new Resume[0]);
        Arrays.sort(resumes, resumeComparator);
        return Arrays.asList(resumes);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((Resume) searchKey);
    }
}
