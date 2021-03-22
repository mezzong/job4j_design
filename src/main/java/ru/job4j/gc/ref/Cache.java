package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;

public interface Cache {

    String getValue(String key);

    void add(String key, SoftReference<String> value);
}
