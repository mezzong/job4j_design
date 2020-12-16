package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> array = new SimpleArray<>();

    public void add(E e) {
        if (!contains(e)) {
            array.add(e);
        }
    }

    public boolean contains(E value) {
        for (E el : array) {
            if (Objects.equals(el, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}
