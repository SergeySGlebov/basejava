package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage implements Storage{
    private List<Resume> storage = new LinkedList<>();
    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void update(Resume r) {
        if(storage.contains(r)) {
            storage.set(storage.indexOf(r), r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        if(!storage.contains(r)){
            storage.add(r);
        } else {
            throw new ExistStorageException(r.getUuid());
        }

    }

    @Override
    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        if(!storage.contains(resume)) {
            throw new NotExistStorageException(uuid);
        }
        return storage.get(storage.indexOf(resume));
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        if(!storage.contains(resume)) {
            throw new NotExistStorageException(uuid);
        }
        storage.remove(resume);
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
