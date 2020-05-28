package com.max.customproject.storage;

import com.max.customproject.controllers.HomeController;
import com.max.customproject.entity.User;

import java.util.ArrayList;

/**
 * Класс предназначен для хранения коллекии зарегистрированных пользователей
 */
public class UserStorage {

    /**
     * Коллекция новых зарегистрированных пльзователей
     */
    public static ArrayList<User> users = new ArrayList<User>() {{
        add(new User("1", "1", "maxum@tut.by"));
        add(new User("2", "2", "maxyra@tut.by"));
    }};

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

    /**
     * Ищем пользователя по логину и возвращаем его логин пароль и мыло
     */
    public static User  getPeopleInCollection(String login) {
        for (int i = 0; i < users.size(); i++) {
            User user1 = users.get(i);
            if (user1.login.equals(login)){
                return user1;
            }
        }
        return null;
    }
}
