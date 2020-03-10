package storage;

import com.max.customproject.User;

import java.util.ArrayList;

public class UserStorage {

    public static ArrayList<User> people = new ArrayList<>();

    public static void createPeopleCollection(User www) {    //метод добавляет пользователя в коллекцию
        people.add(www);
        System.out.println("----------------------------------------------");
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).login + "  " + people.get(i).pass + "  " + people.get(i).doublePass + "  " + people.get(i).email);
        }
        System.out.println("----------------------------------------------");
    }

    public static User checkPeopleInCollection(String log, String pass) {   //ищем пользователя. если находим то возвращаем. Если не находим то возвращаем null
        int havePeople = 0;
        User user = null;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).login == log && people.get(i).pass == pass) {
                havePeople++;
                user = people.get(i);
            }
        }

        if (havePeople == 1) {
            return user;
        } else {
            return null;
        }
    }
}
