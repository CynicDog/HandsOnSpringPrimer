package com.example.springbootsample.controller;

import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.form.UserDetailForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserDetailController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // Display user details screen
    @GetMapping("/detail/{userId:.+}")
    public String getUser(UserDetailForm userDetailForm, Model model,
                          @PathVariable("userId") String userId) {

        // Get user
        MUser user = userService.getUserOne(userId);
        user.setPassword(null);

        // Convert MUser to userDetailForm
        userDetailForm = modelMapper.map(user, UserDetailForm.class);
        userDetailForm.setSalaryList(user.getSalaryList());

        // Register in Model
        model.addAttribute("userDetailForm", userDetailForm);

        return "user/detail";
    }

    // User update process
    @PostMapping(value = "/detail/**", params = "updatePost")
    public String updateUser(UserDetailForm userDetailForm, Model model) {
        // Update user
        try {
            userService.updateUserOne(
                    userDetailForm.getUserId(),
                    userDetailForm.getPassword(),
                    userDetailForm.getUserName());
        } catch (Exception error) {
            log.error("Error occurred in user update process", error);
        }

        return "redirect:/user/list";
    }

    // User delete process
    @PostMapping(value = "/detail/**", params = "deletePost")
    public String deleteUser(UserDetailForm userDetailForm, Model model) {
        // Delete user
        userService.deleteUserOne(userDetailForm.getUserId());

        return "redirect:/user/list";
    }
}

