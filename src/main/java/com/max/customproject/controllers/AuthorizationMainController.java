package com.max.customproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorizationMainController {
    @RequestMapping("main")
    private String mainPage() {
        return "main";
    }
}
