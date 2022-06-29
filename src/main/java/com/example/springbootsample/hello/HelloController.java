package com.example.springbootsample.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
// A class that accepts HTTP requests from a screen is called a controller.
// Classes annotated with @Controller become controllers.

public class HelloController {
    @Autowired
    private HelloService service;

    @GetMapping("/hello")
    // @GetMapping: To accept an HTTP requests for the GET method, use the @GetMapping annotation.
    public String getHello() {
        return "hello";
    }

    @PostMapping("/hello")
    // @PostMapping: To receive an HTTP request for the POST method, use the @PostMapping annotation.
    //              Set the URL to be accepted in the @PostMapping argument.
    public String postRequest(@RequestParam("text1") String str, Model model) {
        // 1. @RequestParam: Used to receive the values entered.
        //                  Specify the argument of @RequestParam annotation to match the HTML name attribute.
        // 2. ui.Model class: Allows you to pass values to another screen.
        //                  Specify the key name and value in the addAttribute method of the class.
        model.addAttribute("sample", str);

    return "hello/response";
    }

    @PostMapping("/hello/db")
    public String postDbRequest(@RequestParam("text2") String id, Model model) {
        Employee employee = service.getEmployee(id);
        model.addAttribute("employee", employee);
        // The instance of Employee got from the service layer is registered in Model.
        return "hello/db";
    }
}

