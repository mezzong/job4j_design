package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<V> {

    public static class Node<K, V> implements Map.Entry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public Node<K, V> getNext() {
            return next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private int capacity = 16;
    private Node<K, V>[] table = new Node[capacity];
    private int size;
    private int modCount = 0;
    private float loadFactor = 0.75f;

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() ^ (key.hashCode() >>> 16);
    }

    public boolean insert(K key, V value) {
        if (key == null) {
            return false;
        }
        int hash = hash(key);
        Node<K, V> newItem = new Node<>(hash, key, value, null);
        if (addValue(table, newItem)) {
            size++;
            modCount++;
            if (((double) size / (double) capacity) > loadFactor) {
                reSize();
            }
            return true;
        }
        return false;
    }

    private boolean addValue(Node<K, V>[] table, Node<K, V> node) {
        if (table == null || node == null || table.length == 0) {
            return false;
        }
        int index = node.hash & (table.length - 1);
        Node<K, V> first = table[index];
        if (first == null) {
            table[index] = node;
            return true;
        }
        Node<K, V> lastElement = first;
        while (lastElement.next != null) {
            lastElement = lastElement.next;
        }
        lastElement.next = node;
        return true;
    }

    public V get(K key) {
        Node<K, V> node = getNode(hash(key), key);
        return node != null ? node.value : null;
    }

    private Node<K, V> getNode(int hash, K key) {
        Node<K, V> first = table[(table.length - 1) & hash];
        Node<K, V> element;
        if (first == null) {
            return null;
        }
        if (first.hash == hash && key.equals(first.key)) {
            return first;
        }
        if (first.next != null) {
            element = first.next;
            do {
                element = element.next;
                if (element.hash == hash && key.equals(element.key)) {
                    return element;
                }
            } while (element.next != null);
        }
        return null;
    }

    public boolean delete(K key) {
        Node<K, V> prev = null;
        Node<K, V> current;
        int index = hash(key) & (table.length - 1);
        current = table[index];
        if (key == null) {
            return false;
        }
        if (current == null) {
            return false;
        }
        if (current.next == null) {
            table[index] = null;
            size--;
            modCount++;
            return true;
        }
        while (current.next != null) {
            if (key.equals(current.key)) {
                prev.next = current.next;
                size--;
                modCount++;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    private void reSize() {
        capacity = capacity * 2;
        Node<K, V>[] newTable = new Node[capacity];
        Node<K, V> element;
        for (Node<K, V> kvNode : this.table) {
            if (kvNode == null) {
                continue;
            }
            element = kvNode;
            if (element.next == null) {
                addValue(newTable, element);
                continue;
            }
            while ((element = kvNode.next) != null) {
                addValue(newTable, element);
            }
        }
        this.table = newTable;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleHashMap<?, ?> that = (SimpleHashMap<?, ?>) o;
        return size == that.size
                && Arrays.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }

    @Override
    public Iterator<V> iterator() {
        return new SimpleHashMapIterator();
    }

    private class SimpleHashMapIterator implements Iterator<V> {

        private final int expectedModCount = modCount;
        private int cursor = 0;
        private int quantity = 0;
        private Node<K, V> currentElement = table[cursor];

        @Override
        public boolean hasNext() {
            return quantity < size;
        }

        @Override
        public V next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (currentElement == null) {
                while (cursor < table.length) {
                    if (table[cursor] != null) {
                        currentElement = table[cursor];
                        cursor++;
                        break;
                    }
                    cursor++;
                }
            }
            V result = currentElement.value;
            currentElement = currentElement.next;
            quantity++;
            return result;
        }

        private void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}