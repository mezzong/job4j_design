package ru.job4j.generics;

import java.util.Iterator;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final T[] data;
    private int cursor = 0;
    private int size;

    public SimpleArrayIterator(T[] data, int size) {
        this.data = data;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return cursor < size;
    }

    @Override
    public T next() {
        return data[cursor++];
    }
}
