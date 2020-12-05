package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            last = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        this.last = node;
    }

    public T deleteFirst() {
        if (head != null) {
            T rsl = head.value;
            head = head.next;
            return rsl;
        } else {
            throw new NoSuchElementException();
        }
    }

    public T deleteLast() {
        if (head != null) {
            if (head == last) {
                T rsl = head.value;
                head = null;
                last = null;
                return rsl;
            }
            Node<T> currentNode = head;
            while (currentNode.next != last) {
                currentNode = currentNode.next;
            }
            T rsl = last.value;
            currentNode.next = null;
            last = currentNode;
            return rsl;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}