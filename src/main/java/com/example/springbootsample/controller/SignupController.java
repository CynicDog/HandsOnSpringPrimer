package com.example.springbootsample.controller;

import com.example.springbootsample.application.service.UserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class SignupController {

    @Autowired
    private final UserApplicationService userApplicationService;

    public SignupController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    // ** Display user signup screen ** //
    @GetMapping("/signup")
    public String getSignup(Model model) {
        // Get gender
        Map<String, Integer> genderMap = userApplicationService.getGenderMap();
        model.addAttribute("genderMap", genderMap);

        return "user/signup";
    }

    // ** User signup process ** //
    @PostMapping("/signup")
    public String postSignUp() {
        // React to login screen
        return "redirect:/login";
    }
}
