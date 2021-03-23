package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> values, Comparator<T> comparator) {
        T rsl = values.get(0);
        for (T element : values) {
            if (comparator.compare(element, rsl) > 0) {
                rsl = element;
            }
        }
        return rsl;
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        return max(values, comparator.reversed());
    }
}
