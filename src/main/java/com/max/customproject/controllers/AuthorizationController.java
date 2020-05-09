package com.max.customproject.controllers;
import com.max.customproject.entity.Authorization;
import com.max.customproject.entity.User;
import com.max.customproject.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Данный класс является классом контроллером.
 * Предназначен для непосредственной обработки запросов при авторизации клиента и возвращения результатов.
 * autorization - при нажатии на кнопку "Войти" работает данный класс
 * redirect:main - просим браузер предоставить запрос на вывод страницы main
 */
@Controller
public class AuthorizationController {

    @Autowired
    private HttpSession session;

    @RequestMapping("autorization")
    public ModelAndView authorizationPage(@RequestParam String login, String password) {
        ModelAndView model = new ModelAndView();

        User checkUserInCollection = UserStorage.checkPeopleInCollection(login,password);
        if (checkUserInCollection == null) {
            model.addObject("finishMessageAuthorization", "Такого пользователя нет");
            model.setViewName("index");
        } else {
            session.setAttribute("isAuthorize", true);
            model.setViewName("redirect:main");
        }
        return model;
    }


    public boolean checkAutorizationUser(){
        Object isAuthorize = session.getAttribute("isAuthorize");
        if (isAuthorize != null && (boolean) isAuthorize) {
            return true;
        } else {
            return false;
        }
    }
}
