package com.example.springbootsample.controller;

import com.example.springbootsample.application.service.UserApplicationService;
import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.form.UserListForm;
import org.apache.catalina.users.MemoryUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserApplicationService userApplicationService;

    /** Display User List Screen */
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

    /** User Search Process */
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

    /** User List Download Process */
    @PostMapping("/list/download")
    public ResponseEntity<byte[]> downloadUserList(@ModelAttribute UserListForm userListForm) throws IOException {
        // Convert form to MUser class
        MUser user = modelMapper.map(userListForm, MUser.class);

        // Search user
        List<MUser> userList = userService.getUsers(user);

        // Save CSV File
        String fileName = "user.csv";
        userApplicationService.saveUserCSV(userList, fileName);

        // Get CSV File
        byte[] bytes = userApplicationService.getCSV(fileName);

        // HTTP header setting
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", MediaType.ALL_VALUE + "; charset=utf-8");
        httpHeaders.setContentDispositionFormData("filename", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }
}