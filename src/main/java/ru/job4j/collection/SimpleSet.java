package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    SimpleArray<E> array = new SimpleArray<>();

    public void add(E e) {
        for (E value : array) {
            if (value.equals(e)) {
                return;
            }
        }
        array.add(e);
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleSetIterator();
    }

    private class SimpleSetIterator implements Iterator<E> {

        Iterator<E> simpleArrayIterator = array.iterator();

        @Override
        public boolean hasNext() {
            return simpleArrayIterator.hasNext();
        }

        @Override
        public E next() {
            return simpleArrayIterator.next();
        }
    }
}
