package ru.job4j.serialization.json;

public class Avatar {
    private String url;

    public Avatar(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Avatar{"
                + "url='" + url + '\''
                + '}';
    }
}
