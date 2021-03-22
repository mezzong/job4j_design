package ru.job4j.gc.ref;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class FileCache implements Cache {

    private final Map<String, SoftReference<String>> store = new HashMap<>();

    private void add(String key, SoftReference<String> value) {
        store.put(key, value);
    }

    @Override
    public String getValue(String key) {
        String result;
        SoftReference<String> ref = store.get(key);
        if (ref != null) {
            result = ref.get();
        } else {
            result = load(key);
            if (result != null) {
                add(key, new SoftReference<>(result));
            }
        }
        return result;
    }

    private String load(String fileName) {
        String result = null;
        try (BufferedReader bf = new BufferedReader(new FileReader("./dataio/cache/" + fileName))) {
            StringBuilder sb = new StringBuilder();
            while (bf.ready()) {
                sb.append(bf.readLine());
                sb.append(System.lineSeparator());
            }
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        FileCache fileCache = new FileCache();
        System.out.println(fileCache.getValue("test1.txt"));
        System.out.println(fileCache.getValue("test2.txt"));
    }
}
