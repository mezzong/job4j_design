package ru.job4j.generics;

public class Role extends Base {

    private final String name;
    private final String access;

    public Role(String id, String name, String access) {
        super(id);
        this.name = name;
        this.access = access;
    }
}
