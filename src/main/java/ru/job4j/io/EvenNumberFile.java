package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("./dataio/even.txt")) {
            int read;
            StringBuilder text = new StringBuilder();
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String s : lines) {
                int num = Integer.parseInt(s);
                if (num % 2 == 0) {
                    System.out.println(num + " even");
                } else {
                    System.out.println(num + " odd");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
