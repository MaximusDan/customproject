package com.max.customproject.controllers;
import com.max.customproject.entity.Authorization;
import com.max.customproject.entity.User;
import com.max.customproject.storage.UserStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Данный класс является классом контроллером.
 * Предназначен для непосредственной обработки запросов при авторизации клиента и возвращения результатов.
 * autorization - при нажатии на кнопку "Войти" работает данный класс
 * redirect:main - просим браузер предоставить запрос на вывод страницы main
 */
@Controller
public class AuthorizationController {

    @RequestMapping("autorization")
    public ModelAndView authorizationPage(@RequestParam String login, String password) {
        ModelAndView model = new ModelAndView();

        User people = new User();
        people.login = login;
        people.password = password;

        User checkUserInCollection = UserStorage.checkPeopleInCollection(people.login, people.password);
        if (checkUserInCollection == null) {
            model.addObject("finishMessageAuthorization", "Такого пользователя нет");
            model.setViewName("index");
        } else {
            model.setViewName("redirect:main");
        }
        return model;
    }
}
