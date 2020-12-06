package ru.job4j.collection;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();
    private int size = 0;

    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        size++;
        linked.addFirst(value);
    }

    public int getSize() {
        return size;
    }
}