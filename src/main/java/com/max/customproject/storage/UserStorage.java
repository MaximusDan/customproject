package com.max.customproject.storage;

import com.max.customproject.entity.User;

import java.util.ArrayList;

/*
 * В классе хранится коллекция зарегистрированных пользователей
 * */
public class UserStorage {
    public static ArrayList<User> users = new ArrayList<>();

    /*
     * Метод добавляет ползоваеля в коллекцию
     */
    public static void saveUser(User newUser1) {
        users.add(newUser1);
    }

    /*
    ищем пользователя. если находим то возвращаем. Если не находим то возвращаем null
     */
    public static User checkPeopleInCollection(String log, String pass) {

        User user = null;
        for (int i = 0; i < users.size(); i++) {
            User user1 = users.get(i);
            if (user1.login == log && user1.password == pass) {
                user = user1;
                break;
            }
        }
        return user;
    }
}
