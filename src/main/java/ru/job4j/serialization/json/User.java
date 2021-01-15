package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class User {
    private final boolean sex;
    private final int age;
    private final String nickname;
    private final Avatar avatar;
    private final int[] favoriteNumbers;

    public User(boolean sex, int age, String nickname, Avatar avatar, int... favoriteNumbers) {
        this.sex = sex;
        this.age = age;
        this.nickname = nickname;
        this.avatar = avatar;
        this.favoriteNumbers = favoriteNumbers;
    }

    @Override
    public String toString() {
        return "User{"
                + "sex=" + sex
                + ", age=" + age
                + ", nickname='" + nickname + '\''
                + ", avatar=" + avatar
                + ", favoriteNumbers=" + Arrays.toString(favoriteNumbers)
                + '}';
    }

    public static void main(String[] args) {
        User user = new User(true, 25, "nick", new Avatar("/img/avatar.jpg"), 1, 2, 31);
        Gson gson = new GsonBuilder().create();
        String jsonUser = gson.toJson(user);
        System.out.println(jsonUser);
        User fromJson = gson.fromJson(jsonUser, User.class);
        System.out.println(fromJson);
    }
}
