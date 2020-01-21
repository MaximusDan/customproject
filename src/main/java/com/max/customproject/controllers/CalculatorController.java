package com.max.customproject.controllers;

import net.minidev.json.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class CalculatorController {
    public static String number = "";
    public static String result = "";
    public static ArrayList<String> collectionNumber = new ArrayList<String>();
    public static String resultCollection = "";
    public static String resultСalculation = "";
    //public static int b = 0;
    @RequestMapping("calculator")
    public String calculatorPage() {
        return "calculator/calculator";
    }

    @RequestMapping("test3")
    public ModelAndView testPage(@RequestParam String submit) {
        ModelAndView model = new ModelAndView();

        result = result + submit;//5+4
        collectionNumber.add(submit);

        System.out.println("Сработал 1 метод");
        System.out.println("number = " + submit);
        System.out.println("result = " + result);
       for (int i = 0; i < collectionNumber.size(); i++) {
           System.out.println("Номер коллекции " + i + " = " + collectionNumber.get(i));
       }
        model.addObject("max", CalculatorController.result);
        model.setViewName("calculator/calculator");
        return model;
    }

    @RequestMapping("math")
    public ModelAndView mathPage(@RequestParam String submit) {
        ModelAndView model = new ModelAndView();

        result = result + submit;
        collectionNumber.add(submit);

        System.out.println("Сработал 2 метод");
        System.out.println("number = " + submit);
        System.out.println("result = " + result);
        for (int i = 0; i < collectionNumber.size(); i++) {
            System.out.println("Номер коллекции " + i + ": " + collectionNumber.get(i));
        }
        model.addObject("max", CalculatorController.result);
       /* resultCollection = "";*/
        model.setViewName("calculator/calculator");
        return model;
    }

    @RequestMapping("rezult")
    public ModelAndView rezultPage(@RequestParam String submit) {
        ModelAndView model = new ModelAndView();
        int number1 = 0;
        int number2 = 0;
        char symbol = 'q';

        for (int i = 0; i < collectionNumber.size(); i++) {
            char x = collectionNumber.get(i).charAt(0);
            if(Character.isDigit(x)){
                if(number1 == 0 ){
                    number1 = Integer.parseInt(collectionNumber.get(i));
                }else{
                    number2 = Integer.parseInt(collectionNumber.get(i));
                }
            }else{
                symbol = collectionNumber.get(i).charAt(0);
            }
        }

        int a = 0;
        switch (symbol) {
            case '+':
                a = number1 + number2;
                break;
            case '-':
                a = number1 - number2;
                break;
            case '/':
                a = number1 / number2;
                break;
            case '*':
                a = number1 * number2;
                break;
        }

        collectionNumber = new ArrayList<>();

        result = Integer.toString(a);
        collectionNumber.add(Integer.toString(a));
        System.out.println("Сработал 3 метод");
        System.out.println("number = " + submit);
        System.out.println("result = " + result);
        for (int i = 0; i < collectionNumber.size(); i++) {
            System.out.println("Номер коллекции " + i + ": " + collectionNumber.get(i));
        }
        model.addObject("max", CalculatorController.result);
        model.setViewName("calculator/calculator");
        return model;
    }
    @RequestMapping("clean")
    public String cleanPage() {
        result = "";
        collectionNumber = new ArrayList<String>();


        System.out.println("Чистка");
        System.out.println("result = " + result);
        for (int i = 0; i < collectionNumber.size(); i++) {
            System.out.println("Номер коллекции " + i + ": " + collectionNumber.get(i));
        }
        return "calculator/calculator";
    }

}
