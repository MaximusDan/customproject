package com.max.customproject.controllers;

import com.max.customproject.entity.User;
import com.max.customproject.storage.UserStorage;
import com.max.customproject.validators.RegistrationValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Данный класс является классом контроллером.
 * Предназначен для непосредственной обработки запросов при регистрации клиента и возвращения результатов.
 * registration - при нажатии на кнопку Регистрация работает данный класс
 */
@Controller
public class RegistrationController {

    @RequestMapping("registration")
    public ModelAndView registrationPage(@RequestParam String login1, String email, String psw, String psw1) {
        ModelAndView model = new ModelAndView();

        boolean rezultLogin = RegistrationController.checkLogin(model, login1);
        boolean rezultMail = RegistrationController.checkMail(model, email);
        boolean rezultPass = RegistrationController.checkPassword(model, psw);
        boolean rezultDoublePass = RegistrationController.checkDoublePassword(model, psw1, psw);

        boolean rez = rezultLogin && rezultPass && rezultDoublePass && rezultMail;
        if (rez) {
            model.addObject("finishMessage", "Регистрация прошла успешно");
            User newUser = new User();
            newUser.login = login1;
            newUser.password = psw;
            newUser.email = email;

            User checkUserInCollection = UserStorage.checkPeopleInCollection(newUser.login, newUser.password);
            if (checkUserInCollection == null) {
                UserStorage.saveUser(newUser);
            }
        }
        model.setViewName("index");
        return model;
    }

    /**
     * Метод проверяет соответствует ли логин условию
     * rezultLogin - возвращает true либо false
     */
    private static boolean checkLogin(ModelAndView model, String login1) {

        boolean rezultQuantityLettersLogin = RegistrationValidator.checkQuantityLettersLogin(login1);
        boolean rezultLettersLogin = RegistrationValidator.checkLettersLogin(login1);
        boolean rezultLogin = rezultQuantityLettersLogin && rezultLettersLogin;  //проверяем логин
        if (rezultLogin) {
            model.addObject("login", login1);
        } else {
            model.addObject("loginErrorMessage", "Логин должен состоять из 5-15 символов\n" +
                    "Должен состоять только из букв и цифр и символа @ и . (точка)");
        }
        return rezultLogin;
    }

    /**
     * Метод проверяет соответствует ли почта условию
     * rezultMail - возвращает true либо false
     */
    private static boolean checkMail(ModelAndView model, String email) {
        boolean rezultQuantityMail = RegistrationValidator.checkQuantityMail(email);
        boolean rezultLetterMail = RegistrationValidator.checkLetterMail(email);
        boolean rezultMail = rezultQuantityMail && rezultLetterMail;    //проверяем мыло*/
        if (rezultMail) {
            model.addObject("mail", email);
        } else {
            model.addObject("mailErrorMessage", "Емаил должен состоять из 5-100 символов\n" +
                    "Обязательно содержать символ @ и символ точка\n" +
                    " @ должна идти раньше символа точка\n" +
                    "Может содержать буквы, цифры, любые символы");
        }
        return rezultMail;
    }

    /**
     * Метод проверяет соответствует ли пароль условию
     * rezultPass - возвращает true либо false
     */
    private static boolean checkPassword(ModelAndView model, String psw) {
        boolean rezultQuantityPass = RegistrationValidator.checkQuantityPass(psw);
        boolean rezultLetterPass = RegistrationValidator.checkLetterPass(psw);
        boolean rezultPass = rezultQuantityPass && rezultLetterPass;   //проверяем пароль
        if (rezultPass) {
            model.addObject("pass", psw);
        } else {
            model.addObject("passErrorMessage", "Пароль должен состоять из 8-20 символов\n" +
                    "Содержать хотя бы одну цифру(можно и больше)\n" +
                    "Содержать хотя бы одну заглавную букву\n" +
                    "Может содержать след символы: !@.,$ (но они не обязательны)");
        }
        return rezultPass;
    }

    /**
     * Метод проверяет соответствует ли поле повторите пароль условию
     * rezultDoublePass - возвращает true либо false
     */
    private static boolean checkDoublePassword(ModelAndView model, String psw1, String psw) {
        boolean rezultDoublePass = RegistrationValidator.checkDoublePass(psw1, psw); //проверяем дубликат пароля
        if (rezultDoublePass) {
            model.addObject("doublePass", psw1);
        } else {
            model.addObject("doublePassErrorMessage", "Поле повторите пароль и пароль не совпадают");
        }
        return rezultDoublePass;
    }
}

