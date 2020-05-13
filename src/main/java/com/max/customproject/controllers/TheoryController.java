package com.max.customproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TheoryController {

    @Autowired
    private HttpSession session;

    @RequestMapping("theory")
    private String theoryPage() {
        Object isAuthorize = session.getAttribute("isAuthorize");
        if (isAuthorize != null && (boolean) isAuthorize) {
            return "theory";
        } else {
            return "redirect:/";
        }
    }
}
