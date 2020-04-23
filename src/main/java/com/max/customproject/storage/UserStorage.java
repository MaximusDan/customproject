package com.max.customproject.storage;

import com.max.customproject.entity.User;

import java.util.ArrayList;

/**
 * Класс предназначен для хранения коллекии зарегистрированных пользователей
 */
public class UserStorage {

    /**
     * users - коллекция новых зарегистрированных пльзователей
     */
    public static ArrayList<User> users = new ArrayList<>();

    /**
     * Метод добавляет ползоваеля в коллекцию
     */
    public static void saveUser(User newUser) {
        users.add(newUser);
    }

    /**
     * Ищем пользователя. если находим то возвращаем. Если не находим то возвращаем null
     */
    public static User checkPeopleInCollection(String login, String password) {

        User user = null;
        for (int i = 0; i < users.size(); i++) {
            User user1 = users.get(i);
            if (user1.login.equals(login) && user1.password.equals(password)) {
                user = user1;
                break;
            }
        }
        return user;
    }
}
