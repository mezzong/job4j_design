package ru.job4j.generics;

import java.util.Iterator;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final T[] data;
    private int cursor = 0;

    public SimpleArrayIterator(T[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (cursor < data.length && data[cursor] == null) {
            cursor++;
        }
        return cursor < data.length;
    }

    @Override
    public T next() {
        return data[cursor++];
    }
}
