package ru.job4j.collection;

import java.util.*;

public class SimpleArray<E> implements Iterable<E> {

    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[10];
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) container[index];
    }

    public void add(E model) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = model;
        size++;
    }

    private Object[] grow() {
        return Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleArrayIterator();
    }

    private class SimpleArrayIterator implements Iterator<E> {
        private int cursor = 0;
        private final int expectedModCount = modCount;

        SimpleArrayIterator() {
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) container[cursor++];
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
