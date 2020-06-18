package com.max.customproject.controllers;

import com.max.customproject.entity.User;
import com.max.customproject.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private HttpSession session;

    @RequestMapping("home")
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView();
        Object isAuthorize = session.getAttribute("isAuthorize");
        if (isAuthorize != null && (boolean) isAuthorize) {
            User returnUser = UserStorage.getPeopleInCollection((String) session.getAttribute("getLogin"));
            model.addObject("login", returnUser.login);
            model.addObject("password", returnUser.password);
            model.addObject("email", returnUser.email);
            model.setViewName("home");
        } else {
            model.setViewName("redirect:/");
        }
        return model;
    }
}

