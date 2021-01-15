package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TestJSON {
    public static void main(String[] args) {
        User user = new User(false, 23, "lisa", new Avatar("/img/photo.jpg"), 6, 7, 3);
        JSONObject jsonObject = new JSONObject("{\"url\":\"/img/avatar.jpg\"}");

        List<Integer> favoriteNumbers = new ArrayList<>();
        favoriteNumbers.add(1);
        favoriteNumbers.add(2);
        favoriteNumbers.add(3);
        favoriteNumbers.add(31);

        JSONArray jsonArray = new JSONArray(favoriteNumbers);
        JSONObject jsonUser = new JSONObject();

        jsonUser.put("sex", true);
        jsonUser.put("age", 25);
        jsonUser.put("nickname", "nick");
        jsonUser.put("avatar", jsonObject);
        jsonUser.put("favoriteNumbers", jsonArray);

        System.out.println(jsonUser.toString());
        System.out.println(new JSONObject(user).toString());
    }
}
