package com.max.customproject.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReturnToIndexController {

    @Autowired
    private HttpSession session;

    @RequestMapping("returnIndex")
    private String returnIndexPage() {
        Object isAuthorize = session.getAttribute("isAuthorize");
        if (isAuthorize != null && (boolean) isAuthorize) {
            session.setAttribute("isAuthorize", false);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
