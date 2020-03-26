package com.max.customproject.controllers;

import com.max.customproject.entity.User;
import com.max.customproject.storage.UserStorage;
import com.max.customproject.validators.RegistrationValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @RequestMapping("registration")
    public ModelAndView registrationPage(@RequestParam String login1, String email, String psw, String psw1) {
        ModelAndView model = new ModelAndView();

        boolean rezultQuantityLettersLogin = RegistrationValidator.checkQuantityLettersLogin(login1);
        boolean rezultLettersLogin = RegistrationValidator.checkLettersLogin(login1);
        boolean rezultLogin = rezultQuantityLettersLogin && rezultLettersLogin;  //проверяем логин
        if (rezultLogin == false) {
            model.addObject("loginErrorMessage", "Логин должен состоять из 5-15 символов\n" +
                    "Должен состоять только из букв и цифр и символа @ и . (точка)");
        } else {
            model.addObject("loginErrorMessage", "Логин создан успешно");
            model.addObject("login", login1);
        }

        boolean rezultQuantityPass = RegistrationValidator.checkQuantityPass(psw);
        boolean rezultLetterPass = RegistrationValidator.checkLetterPass(psw);
        boolean rezultPass = rezultQuantityPass && rezultLetterPass;   //проверяем пароль
        if (rezultPass == false) {
            model.addObject("passErrorMessage", "Пароль должен состоять из 8-20 символов\n" +
                    "Содержать хотя бы одну цифру(можно и больше)\n" +
                    "Содержать хотя бы одну заглавную букву\n" +
                    "Сожет содержать след символы: !@.,$ (но они не обязательны)");
        } else {
            model.addObject("passErrorMessage", "Пароль создан успешно");
            model.addObject("pass", psw);
        }

        boolean rezultDoublePass = RegistrationValidator.checkDoublePass(psw1, psw); //проверяем дубликат пароля
        if (rezultDoublePass == false) {
            model.addObject("doublePassErrorMessage", "Поле повторите пароль и пароль не совпадают");
        } else {
            model.addObject("doublePassErrorMessage", "Поле повторите введено успешно");
            model.addObject("doublePass", psw1);
        }

        boolean rezultQuantityMail = RegistrationValidator.checkQuantityMail(email);
        boolean rezultLetterMail = RegistrationValidator.checkLetterMail(email);
        boolean rezultMail = rezultQuantityMail && rezultLetterMail;    //проверяем мыло*/
        if (rezultMail == false) {
            model.addObject("mailErrorMessage", "Емаил должен состоять из 5-100 символов\n" +
                    "Обязательно содержать символ @ и символ точка\n" +
                    " @ должна идти раньше символа точка\n" +
                    "Может содержать буквы, цифры, любые символы");
        } else {
            model.addObject("mailErrorMessage", "Емаил создан успешно");
            model.addObject("mail", email);
        }

        boolean rez = rezultLogin && rezultPass && rezultDoublePass && rezultMail;
        if (rez) {
            User newUser = new User();
            newUser.login = login1;
            newUser.password = psw;
            newUser.email = email;

            UserStorage.saveUser(newUser);
            //UserStorage.checkPeopleInCollection(newPeople.login,newPeople.pass);
        }
        model.setViewName("index");
        return model;
    }
}

