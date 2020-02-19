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

        //System.out.println(login1);
        //System.out.println(email);
        //System.out.println(psw);
        //System.out.println(psw1);

        model.addObject("login", login1);
        model.addObject("mail", email);
        model.addObject("pass", psw);
        model.addObject("doublePass", psw1);

        model.setViewName("index");
        return model;
    }
}

