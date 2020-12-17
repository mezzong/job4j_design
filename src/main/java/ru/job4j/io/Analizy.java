package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            List<String> result = new ArrayList<>();
            String start = "";
            String end = "";
            boolean isDown = false;
            while (in.ready()) {
                String line = in.readLine();
                String[] lineArr = line.split(" ");
                String code = lineArr[0];
                String time = lineArr[1];
                if ((code.equals("400") || code.equals("500")) && !isDown) {
                    isDown = true;
                    start = time;
                }
                if ((code.equals("200") || code.equals("300")) && isDown) {
                    isDown = false;
                    end = time;
                    result.add(start + ";" + end);
                }
            }
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                result.forEach(out::println);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("./dataio/alog.txt", "./dataio/unavailable.csv");
    }
}
