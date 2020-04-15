package com.max.customproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    private String indexPage(){
        return "index";
    }

    @RequestMapping("test")
    private String testPage(){
        return "test";
    }
}
