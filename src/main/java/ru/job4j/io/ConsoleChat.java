package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean isActive = true;
        boolean isRun = true;
        try (BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader answersIn = new BufferedReader(new FileReader(botAnswers));
             BufferedWriter log = new BufferedWriter(new FileWriter(new File(path)))) {
            List<String> lines = new ArrayList<>();
            while (answersIn.ready()) {
                lines.add(answersIn.readLine());
            }
            while (isRun) {
                String question = consoleIn.readLine();
                log.write(question + System.lineSeparator());
                if (question.equals(STOP)) {
                    isActive = false;
                }
                if (question.equals(OUT)) {
                    isActive = false;
                    isRun = false;
                }
                if (isActive) {
                    int rand = (int) (Math.round((lines.size() - 1) * Math.random()));
                    String answer = lines.get(rand);
                    System.out.println(answer);
                    log.write(answer + System.lineSeparator());
                }
                if (question.equals(CONTINUE)) {
                    isActive = true;
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./dataio/chatlog.txt", "./dataio/botAnswer.txt");
        cc.run();
    }
}
