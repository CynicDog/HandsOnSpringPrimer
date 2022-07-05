package com.example.springbootsample.controller;

import com.example.springbootsample.application.service.UserApplicationService;
import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.form.GroupOrder;
import com.example.springbootsample.form.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

    @Autowired
    private final UserApplicationService userApplicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    public SignupController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    // ** Display user signup screen ** //
    @GetMapping("/signup")
    public String getSignup(Model model, Locale locale, SignupForm signupForm) {
        // Get gender
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);
        model.addAttribute("signupForm", signupForm);

        return "user/signup";
    }

    // ** User signup process ** //
    @PostMapping("/signup")
    public String postSignUp(Model model, Locale locale,
                             @ModelAttribute @Validated(GroupOrder.class) SignupForm signupForm,
                             BindingResult bindingResult) {

        // Input check result
        if (bindingResult.hasErrors()) {
            return getSignup(model, locale, signupForm);
        }

        log.info(signupForm.toString());

        // Convert from to MUser class
        MUser user = modelMapper.map(signupForm, MUser.class);

        // user signup
        userService.signup(user);

        // Redirect to login screen
        return "redirect:/login";
    }
}

