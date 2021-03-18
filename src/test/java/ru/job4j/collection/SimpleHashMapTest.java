package ru.job4j.collection;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleHashMapTest {

    @Test
    public void whenSuccessInsertAndGet() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");
        simpleHashMap.insert(3, "three");
        assertThat(simpleHashMap.get(2), is("two"));
    }

    @Test
    public void whenFailedGet() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");
        simpleHashMap.insert(3, "three");
        assertNull(simpleHashMap.get(4));
    }

    @Test
    public void delete() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(2, "two");
        simpleHashMap.insert(3, "three");
        simpleHashMap.delete(2);
        assertNull(simpleHashMap.get(2));
    }

    @Test
    public void whenTriggeredResize() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        for (int i = 0; i < 13; i++) {
            simpleHashMap.insert(i, "item " + i);
        }
        assertThat(simpleHashMap.getCapacity(), is(32));
    }
}