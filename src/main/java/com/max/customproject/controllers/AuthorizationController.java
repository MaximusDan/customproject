package com.max.customproject.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorizationController {

    @RequestMapping("autorization")
    public ModelAndView registrationPage(@RequestParam String login, String password) {
        ModelAndView model = new ModelAndView();


        model.addObject("log", login);
        model.addObject("pas", password);

        model.setViewName("registration/registration");
        return model;
    }
}
