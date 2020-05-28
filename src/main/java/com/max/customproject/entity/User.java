package com.max.customproject.entity;

/**
 * Данный класс является классом сущностью.
 * В нем создается обьект user - новый зарегистрированный пользователь
 * user играет главную роль в проекте. Из них создается база данных зарегистрированных пользователей.
 */

public class User {

    /**
     * Поле класса предназначенное для хранения логина пользователя
     */
    public String login;

    /**
     * Поле класса предназначенное для хранения пароля пользователя
     */
    public String password;

    /**
     * Поле класса предназначенное для хранения емаила пользователя
     */
    public String email;

    /**
     * Конструктор для создания вручную пользователей в коллекции
     */
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    /**
     * Конструктор для создания пользователей через сайт клиента
     */
    public User() {
    }
}


