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

        boolean rezultQuantityLettersLogin = RegistrationController.checkQuantityLettersLogin(login1);
        boolean rezultLettersLogin = RegistrationController.checkLettersLogin(login1);
        boolean rezultLogin = rezultQuantityLettersLogin && rezultLettersLogin;  //проверяем логин

        boolean rezultQuantityPass = RegistrationController.checkQuantityPass(psw);
        boolean rezultLetterPass = RegistrationController.checkLetterPass(psw);
        boolean rezultPass = rezultQuantityPass && rezultLetterPass;   //проверяем пароль

        boolean rezultDoublePass = RegistrationController.checkDoublePass(psw1, psw); //проверяем дубликат пароля

        boolean rezultQuantityMail = RegistrationController.checkQuantityMail(email);
        boolean rezultLetterMail = RegistrationController.checkLetterMail(email);
        boolean rezultMail =  rezultQuantityMail && rezultLetterMail;    //проверяем мыло

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
        boolean bol1 = false;
        boolean bol2 = false;
        boolean bol3 = false;
        boolean bol4 = false;
        boolean bol5 = true;
        String[] result = login.split("");

        for (int i = 0; i < login.length(); i++) {
            if (Character.isDigit(Integer.parseInt(result[i]))) {
                bol1 = true;
                if (Character.isLetter(Integer.parseInt(result[i]))) {
                    bol2 = true;
                    if (result[i].equals("@")) {
                        bol3 = true;
                        if (result[i].equals(".")) {
                            bol4 = true;
                        }
                    }
                }
            } else {
                bol5 = false;
                System.out.println("Недопустимый символ");
                break;
            }
        }
        if (bol1 && bol2 && bol3 && bol4 && bol5) {
            bol = true;
        }
        return bol;
    }

    public static boolean checkQuantityPass(String pass) {
        boolean bol = false;
        if (pass.length() < 21 && pass.length() > 7) {
            bol = true;
        }
        return bol;
    }

    public static boolean checkLetterPass(String pass) {
        boolean bol = false;
        boolean bol1 = false;
        boolean bol2 = false;
        boolean bol3 = false;
        boolean bol4 = false;
        boolean bol5 = true;
        String[] result = pass.split("");

        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(Integer.parseInt(result[i]))) {
                bol1 = true;
                if (Character.isUpperCase(Integer.parseInt(result[i]))) {
                    bol2 = true;
                    if (Character.isUpperCase(Integer.parseInt(result[i]))) {
                        bol3 = true;
                        if (result[i].equals("@") || result[i].equals(",") || result[i].equals(".") || result[i].equals("!") || result[i].equals("$") || Character.isLetter(Integer.parseInt(result[i]))) {
                            bol4 = true;
                        }
                    }
                }
            } else {
                bol5 = false;
                System.out.println("Недопустимый символ");
                break;
            }
        }
        if (bol1 && bol2 && bol3 && bol5) {
            bol = true;
        }
        return bol;
    }

    public static boolean checkDoublePass(String pass1, String pass) {
        boolean bol = false;
        if (pass1.equals(pass)) {
            bol = true;
        }
        return bol;
    }


    public static boolean checkQuantityMail(String mail) {
        boolean bol = false;
        if (mail.length() < 101 && mail.length() > 4) {
            bol = true;
        }
        return bol;
    }


    public static boolean checkLetterMail(String mail) {
        boolean bol = false;
        boolean bol1 = false;
        boolean bol2 = false;
        boolean bol3 = false;
        String[] result = mail.split("");
        for (int i = 0; i < mail.length(); i++) {
            if(result[i].equals("@")){
                bol1 = true;
            }
            if(result[i].equals(".")){
                bol2 = true;
            }
        }
        for (int i = 0; i < mail.length(); i++) {   //этим циклом проверяю стоит ли 2 раньше точки
            if(result[i].equals("@")){
                bol3 = true;
                break;
            }
            if(result[i].equals(".")){
                break;
            }
        }
        if (bol1 && bol2 && bol3) {
            bol = true;
        }
        return bol;
    }
}

