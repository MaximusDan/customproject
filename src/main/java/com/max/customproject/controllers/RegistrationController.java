package com.max.customproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @RequestMapping("registration")
    public ModelAndView registrationPage(@RequestParam String login1, String email, String psw, String psw1) {
        ModelAndView model = new ModelAndView();

        boolean rezultLogin = false;
        boolean rezultQuantityLettersLogin = RegistrationController.checkQuantityLettersLogin(login1);    //проверяет что логин должен состоять из 5-15 символов
        boolean rezultLettersLogin = RegistrationController.checkLettersLogin(login1);   //проверяет что логин должен состоять только из букв и цифр и символа @ и . (точка)
        System.out.println(rezultQuantityLettersLogin);
        System.out.println(rezultLettersLogin);

        model.addObject("login", login1);
        model.addObject("mail", email);
        model.addObject("pass", psw);
        model.addObject("doublePass", psw1);

        model.setViewName("index");
        return model;
    }

    public static boolean checkQuantityLettersLogin(String login) {
        boolean bol = false;
        if (login.length() < 16 && login.length() > 4) {
            bol = true;
        }
        return bol;
    }

    public static boolean checkLettersLogin(String login) {
        boolean bol = false;
        char[] result = login.toCharArray();
        boolean bol1 = false;
        boolean bol2 = false;
        boolean bol3 = false;
        boolean bol4 = false;

        for (int i = 0; i < login.length(); i++) {
            int a = (int) result[i];
            if ((a > 64 && a < 91) || (a > 96 && a < 123)) {
                bol1 = true;
            }
            if (a > 47 && a < 58) {
                bol2 = true;
            }
            if (a == 64) {
                bol3 = true;
            }
            if (a == 46) {
                bol4 = true;
            }
        }

        if (bol1 == true && bol2 == true && bol3 == true && bol4 == true) {
            bol = true;
        }
        return bol;
    }
}

