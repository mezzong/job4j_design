package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int size = 0;

    public SimpleArray(int capacity) {
        this.data = new Object[capacity];
    }

    public void add(T model) {
        data[size] = model;
        size++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, data.length);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, data.length);
        System.arraycopy(data, index + 1, data, index, size - index + 1);
        size = size - 1;
    }

    public T get(int index) {
        Objects.checkIndex(index, data.length);
        return (T) data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator(data);
    }
}
