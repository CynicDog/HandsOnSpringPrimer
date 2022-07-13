package com.example.springbootsample.rest;

import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.form.UserDetailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    // Update User
    public int updateUser(UserDetailForm userDetailForm) {
        userService.updateUserOne(
                userDetailForm.getUserId(),
                userDetailForm.getPassword(),
                userDetailForm.getUserName());

        return 0 ; }

    // Delete User
    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm userDetailForm) {
        userService.deleteUserOne(userDetailForm.getUserId());

        return 0; }
}
