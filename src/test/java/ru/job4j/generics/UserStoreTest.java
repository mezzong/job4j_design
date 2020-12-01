package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void add() {
        UserStore users = new UserStore();
        User user = new User("1", "User1");
        users.add(user);
        assertThat(user, is(users.findById("1")));
    }

    @Test
    public void replace() {
        UserStore users = new UserStore();
        User user1 = new User("1", "User1");
        User user2 = new User("2", "User2");
        users.add(user1);
        users.replace("1", user2);
        assertThat(user2, is(users.findById("2")));
    }

    @Test
    public void delete() {
        UserStore users = new UserStore();
        User user1 = new User("1", "User1");
        users.add(user1);
        users.delete("1");
        assertNull(users.findById("1"));
    }
}