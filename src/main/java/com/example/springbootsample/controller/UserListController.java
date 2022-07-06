package com.example.springbootsample.controller;

import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.form.UserListForm;
import org.apache.catalina.users.MemoryUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // Display user list screen
    @GetMapping("/list")
    public String getUserList(@ModelAttribute UserListForm userListForm, Model model) {
        // Convert form to MUser class
        MUser user = modelMapper.map(userListForm, MUser.class);

        // Get user list
        List<MUser> userList = userService.getUsers(user);

        // Register in Model
        model.addAttribute("userList", userList);

        // Display user list on screen
        return "user/list";
    }

    // User search process
    @PostMapping("/list")
    public String postUserList(@ModelAttribute UserListForm userListForm, Model model) {
        // Convert form to MUser class
        MUser user = modelMapper.map(userListForm, MUser.class);

        // Get user list
        List<MUser> userList = userService.getUsers(user);

        // Register in Model
        model.addAttribute("userList", userList);

        return "user/list";
    }
}
