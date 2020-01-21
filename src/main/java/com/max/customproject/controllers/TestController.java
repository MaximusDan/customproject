package com.max.customproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("test2")
    private String test2Page(){
        System.out.println("test2");
        return "test";
    }
}
