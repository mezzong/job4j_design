package ru.job4j.serialization.json;

public class Avatar {
    private String url;

    public Avatar(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Avatar{"
                + "url='" + url + '\''
                + '}';
    }
}
