package ru.job4j.gc;

public class User {
    private final int age;
    private final String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Object remove %nname: %s age: %d%n", name, age);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            new User(i, "name" + i);
        }
    }
}
