package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        for (T element : mem) {
            if (element.getId().equals(id)) {
                mem.set(mem.indexOf(element), model);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        for (T element : mem) {
            if (element.getId().equals(id)) {
                mem.remove(element);
                return true;
            }
        }
        return false;
    }

    @Override
    public T findById(String id) {
        for (T element : mem) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }
}
