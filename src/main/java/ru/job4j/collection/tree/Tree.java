package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Node<E> parentNode = findBy(parent).orElse(null);
        if (parentNode == null) {
            throw new NoSuchElementException();
        }
        if (findBy(child).isPresent()) {
            return false;
        }
        return parentNode.children.add(new Node<>(child));
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(el -> el.value.equals(value));
    }

    public boolean isBinary() {
        return findByPredicate(el -> el.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
