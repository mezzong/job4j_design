package ru.job4j.collection.analysis;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnalizeTest {

    Analize.Info info = new Analize.Info();
    List<Analize.User> previous = new ArrayList<>();
    List<Analize.User> current = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 6; i++) {
            previous.add(new Analize.User());
        }

        for (int i = 0; i < 7; i++) {
            current.add(new Analize.User());
        }

        info.added = 2;
        info.changed = 3;
        info.deleted = 1;

        int id = 1;

        for (Analize.User user : previous) {
            user.id = id;
            user.name = "User " + id;
            id++;
        }

        current.get(0).id = 1;
        current.get(1).id = 2;
        current.get(2).id = 3;
        current.get(3).id = 4;
        current.get(4).id = 5;
        current.get(5).id = 7;
        current.get(6).id = 8;

        current.get(0).name = "User 1 ed";
        current.get(1).name = "User 2 ed";
        current.get(2).name = "User 3 ed";
        current.get(3).name = "User 4";
        current.get(4).name = "User 5";
        current.get(5).name = "User 7";
        current.get(6).name = "User 8";
    }

    @Test
    public void diff() {
        Analize analize = new Analize();
        Analize.Info result = analize.diff(previous, current);
        assertEquals(info.deleted, result.deleted);
        assertEquals(info.added, result.added);
        assertEquals(info.changed, result.changed);
    }
}