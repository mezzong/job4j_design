package ru.job4j.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgZip {

    private final String[] args;
    private final Map<String, String> values = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
        String example = "Example: java -jar pack.jar "
                + "-d=c:\\projects\\job4j\\ -e=class -o=project.zip";
        if (args.length == 0) {
            throw new IllegalArgumentException("Specify arguments"
                    + System.lineSeparator()
                    + example);
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("Not contain \"=\""
                        + System.lineSeparator()
                        + example);
            }
            String[] pair = arg.split("=");
            if (pair.length != 2) {
                throw new IllegalArgumentException(example);
            }
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    public boolean valid() {
        List<String> keys = List.of("d", "e", "o");
        if (!(values.size() == 3)) {
            return false;
        }
        for (String key : keys) {
            if (!values.containsKey(key)) {
                return false;
            }
        }
        return true;
    }

    public String directory() {
        return values.get("d");
    }

    public String exclude() {
        return values.get("e");
    }

    public String output() {
        return values.get("o");
    }
}
