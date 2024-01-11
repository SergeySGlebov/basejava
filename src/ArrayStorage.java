/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    int size = 0;

    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        int id = getIdOfElement(uuid);
        if (id >= 0) {
            return storage[id];
        }
        return null;
    }

    void delete(String uuid) {
        int id = getIdOfElement(uuid);
        if (id >= 0) {
            size--;
            for (int i = id; i < size; i++) {
                storage[i] = storage[id + 1];
            }
        }
    }

    int getIdOfElement(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumeArray = new Resume[size];
        System.arraycopy(storage, 0, resumeArray, 0, size);
        return resumeArray;
    }

    int size() {
        return size;
    }
}
