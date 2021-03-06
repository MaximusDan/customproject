package com.max.customproject.controllers;

import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;
import java.util.ArrayList;

@Controller
public class CalculatorController {
    public static String number = "";
    public static String result = "";
    public static ArrayList<String> collectionNumber = new ArrayList<String>();
    public static ArrayList<String> collectionNewNumber = new ArrayList<String>();
    public static ArrayList<String> collectionNewSymbol = new ArrayList<String>();
    public static String resultCollection = "";
    public static String resultСalculation = "";

    @Autowired
    private HttpSession session;

    @RequestMapping("calculator")
    public String calculatorPage() {
        Object isAuthorize = session.getAttribute("isAuthorize");
        if(isAuthorize != null && (boolean)isAuthorize){
            return "calculator/calculator";
        }else{
            return "redirect:/";
        }
    }

    @RequestMapping("test3")
    public ModelAndView testPage(@RequestParam String submit) {
        Object isAuthorize = session.getAttribute("isAuthorize");
        ModelAndView model = new ModelAndView();
        if (isAuthorize != null && (boolean) isAuthorize) {

        number = number + submit;
        result = result + submit;//5+4

        System.out.println("Сработал 1 метод");
        System.out.println("number = " + submit);
        System.out.println("result = " + result);
       for (int i = 0; i < collectionNumber.size(); i++) {
           System.out.println("Номер коллекции " + i + " = " + collectionNumber.get(i));
       }
        model.addObject("max", CalculatorController.result);
        model.setViewName("calculator/calculator");
        return model;
        } else {
            model.setViewName("redirect:/");
            return model;
        }
    }

    @RequestMapping("math")
    public ModelAndView mathPage(@RequestParam String submit) {
        Object isAuthorize = session.getAttribute("isAuthorize");
        ModelAndView model = new ModelAndView();
        if (isAuthorize != null && (boolean) isAuthorize) {

        collectionNumber.add(number);
        number = "";
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
        } else {
            model.setViewName("redirect:/");
            return model;
        }
    }

    @RequestMapping("rezult")
    public ModelAndView rezultPage(@RequestParam String submit) {
        int number1 = 0;
        int number2 = 0;
        int index = 0;

        collectionNumber.add(number); //сохраняю в общую коллекцию последнее введенное число до знака равно
        number = "";

        CalculatorController.collection();  //разбиваю общую коллекцию на две
        ModelAndView model = new ModelAndView();

        while (collectionNewSymbol.contains("/")) {
            index = collectionNewSymbol.indexOf("/");  //нахожу индекс элемента в коллекции символов
            collectionNewSymbol.remove(index);  //удаляю элемент с символом из коллекции символов

            number1 = Integer.parseInt(collectionNewNumber.get(index));   //сохраняем элемент в переменную
            number2 = Integer.parseInt(collectionNewNumber.get(index + 1));  //сохраняем элемент в переменную

            collectionNewNumber.remove(index + 1);   // удаляем элемент из переменной
            collectionNewNumber.remove(index);  // удаляем элемент из переменной

            int a = number1 / number2;
            collectionNewNumber.add(index, Integer.toString(a));
        }
        while (collectionNewSymbol.contains("*")) {
            index = collectionNewSymbol.indexOf("*");  //нахожу индекс элемента в коллекции символов
            collectionNewSymbol.remove(index);  //удаляю элемент с символом из коллекции символов

            number1 = Integer.parseInt(collectionNewNumber.get(index));   //сохраняем элемент в переменную
            number2 = Integer.parseInt(collectionNewNumber.get(index + 1));  //сохраняем элемент в переменную

            collectionNewNumber.remove(index + 1);   // удаляем элемент из переменной
            collectionNewNumber.remove(index);  // удаляем элемент из переменной

            int a = number1 * number2;
            collectionNewNumber.add(index, Integer.toString(a));
        }
        while (collectionNewSymbol.contains("+")) {
            index = collectionNewSymbol.indexOf("+");  //нахожу индекс элемента в коллекции символов
            collectionNewSymbol.remove(index);  //удаляю элемент с символом из коллекции символов

            number1 = Integer.parseInt(collectionNewNumber.get(index));   //сохраняем элемент в переменную
            number2 = Integer.parseInt(collectionNewNumber.get(index + 1));  //сохраняем элемент в переменную

            collectionNewNumber.remove(index + 1);   // удаляем элемент из переменной
            collectionNewNumber.remove(index);  // удаляем элемент из переменной

            int a = number1 + number2;
            collectionNewNumber.add(index, Integer.toString(a));
        }
        while (collectionNewSymbol.contains("-")) {
            index = collectionNewSymbol.indexOf("-");  //нахожу индекс элемента в коллекции символов
            collectionNewSymbol.remove(index);  //удаляю элемент с символом из коллекции символов

            number1 = Integer.parseInt(collectionNewNumber.get(index));   //сохраняем элемент в переменную
            number2 = Integer.parseInt(collectionNewNumber.get(index + 1));  //сохраняем элемент в переменную

            collectionNewNumber.remove(index + 1);   // удаляем элемент из переменной
            collectionNewNumber.remove(index);  // удаляем элемент из переменной

            int a = number1 - number2;
            collectionNewNumber.add(index, Integer.toString(a));
        }

        model.addObject("max", CalculatorController.collectionNewNumber.get(0));
        model.setViewName("calculator/calculator");
        return model;
    }

    @RequestMapping("clean")
    public String cleanPage() {
        result = "";
        collectionNumber = new ArrayList<String>();
        collectionNewNumber = new ArrayList<String>();
        collectionNewSymbol = new ArrayList<String>();

        return "calculator/calculator";
    }

    /*Проверяем является ли элемент коллекии числом*/

    public static boolean isDigit(String string) {
        boolean bol = false;
        for (int i = 0; i < string.length(); i++) {
            char x = string.charAt(i);
            if (Character.isDigit(x)) {
                bol = true;
            }
        }
        return bol;
    }

    /*Разбиваем коллекцию на 2 коллекуии одна с цифрами, другая со знаками*/
    public static void collection() {
        for (int i = 0; i < collectionNumber.size(); i++) {
            boolean f = CalculatorController.isDigit(collectionNumber.get(i));
            if (f) {
                collectionNewNumber.add(collectionNumber.get(i));
            } else {
                collectionNewSymbol.add(collectionNumber.get(i));
            }
        }
    }
}
