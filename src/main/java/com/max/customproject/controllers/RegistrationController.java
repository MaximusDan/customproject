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
        //System.out.println(rezultLogin);

        boolean rezultQuantityPass = RegistrationController.checkQuantityPass(psw);
        boolean rezultLetterPass = RegistrationController.checkLetterPass(psw);
        boolean rezultPass = rezultQuantityPass && rezultLetterPass;   //проверяем пароль
        //System.out.println("итого  " + rezultPass);

        boolean rezultDoublePass = RegistrationController.checkDoublePass(psw1, psw); //проверяем дубликат пароля
        //System.out.println("итого 2  " + rezultDoublePass);*/

        boolean rezultQuantityMail = RegistrationController.checkQuantityMail(email);
        boolean rezultLetterMail = RegistrationController.checkLetterMail(email);
        boolean rezultMail =  rezultQuantityMail && rezultLetterMail;    //проверяем мыло*/
        //System.out.println("итого  " + rezultMail);

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
        int a = 0;
        boolean bol = false;
        boolean bol1 = false;
        boolean bol2 = false;
        boolean bol3 = false;
        boolean bol4 = false;
        boolean bol5 = true;
        char[] result = login.toCharArray();

        for (int i = 0; i < login.length(); i++) {
            if (Character.isDigit(result[i])) {
                bol1 = true;
            } else {
                a++;
            }
            if (Character.isLetter(result[i])) {
                bol2 = true;
            } else {
                a++;
            }
            if (result[i] == '@') {
                bol3 = true;
            } else {
                a++;
            }
            if (result[i] == '.') {
                bol4 = true;
            } else {
                a++;
            }
            if (a == 4) {
                bol5 = false;
                System.out.println("Недопустимый символ");
                break;
            }
            a = 0;
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
        int a = 0;
        boolean bol = false;
        boolean bol1 = false;
        boolean bol2 = false;
        boolean bol3 = false;
        boolean bol4 = false;
        boolean bol5 = false;
        boolean bol6 = true;
        char[] result = pass.toCharArray();

        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(result[i])) {
                bol1 = true;
            }else {
                a++;
            }
            if (Character.isLetter(result[i])) {
                bol2 = true;
            } else {
                a++;
            }
            if (Character.isUpperCase(result[i])) {
                bol3 = true;
            } else {
                a++;
            }
            if (Character.isUpperCase(result[i])) {
                bol4 = true;
            } else {
                a++;
            }
            if (result[i] == '@' || result[i] == ',' || result[i] == '.' || result[i] == '!' || result[i] =='$'){
                bol5 = true;
            }else{
                a++;
            }
            if (a == 5) {
                bol6 = false;
                System.out.println("Недопустимый символ");
                break;
            }
            a = 0;
        }
        /*System.out.println("bol1  " + bol1);
        System.out.println("bol2  " + bol2);
        System.out.println("bol3  " + bol3);
        System.out.println("bol4  " + bol4);
        System.out.println("bol6  " + bol6);*/

        if (bol1 && bol2 && bol3 && bol4 && bol6) {
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
        int first = 0;
        int second = 0;
        boolean bol = false;
        boolean bol1 = false;
        boolean bol2 = false;
        char[] result = mail.toCharArray();

        for (int i = 0; i < mail.length(); i++) {
            if (result[i] == '@') {
                bol1 = true;
                first = i;
            }
            if (result[i] == '.') {
                bol2 = true;
                second = i;
            }
        }
        if (first < second && bol1 && bol2) {
            bol = true;
        }
        return bol;
    }
}

