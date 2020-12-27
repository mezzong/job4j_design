package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        List<String> answers = new ArrayList<>();
        List<String> listLog = new ArrayList<>();
        try (BufferedReader answersIn = new BufferedReader(new FileReader(botAnswers))) {
            while (answersIn.ready()) {
                answers.add(answersIn.readLine());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isActive = true;
            boolean isRun = true;
            while(isRun) {
                    String question = consoleIn.readLine();
                    listLog.add(question);
                    if (question.equals(STOP)) {
                        isActive = false;
                    }
                    if (question.equals(OUT)) {
                        isActive = false;
                        isRun = false;
                    }
                    if (isActive) {
                        int rand = (int) (Math.round((answers.size() - 1) * Math.random()));
                        String answer = answers.get(rand);
                        System.out.println(answer);
                        listLog.add(answer);
                    }
                    if (question.equals(CONTINUE)) {
                        isActive = true;
                    }
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
        try(BufferedWriter log = new BufferedWriter(new FileWriter(new File(path)))){
            for (String line : listLog) {
                log.write(line + System.lineSeparator());
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
