package com.example.springbootsample.rest;

import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.form.GroupOrder;
import com.example.springbootsample.form.SignupForm;
import com.example.springbootsample.form.UserDetailForm;
import com.example.springbootsample.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MessageSource messageSource;

    // Search User
    @GetMapping("/get/list")
    public List<MUser> getUserList(UserListForm userListForm) {
        // convert user list form to MUser class
        MUser user = modelMapper.map(userListForm, MUser.class);

        // get user list
        List<MUser> userList = userService.getUsers(user);

        return userList;
    }

    // Signup User
    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(GroupOrder.class) SignupForm signupForm,
                                 BindingResult bindingResult,
                                 Locale locale) {

        // input check result
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, locale);
                errors.put(error.getField(), message);
            }
            return new RestResult(90, errors);
        }

        MUser user = modelMapper.map(signupForm, MUser.class);
        userService.signup(user);

        return new RestResult(0, null);
    }

    // Update User
    @PutMapping("/update")
    public int updateUser(UserDetailForm userDetailForm) {
        userService.updateUserOne(
                userDetailForm.getUserId(),
                userDetailForm.getPassword(),
                userDetailForm.getUserName());

        return 0;
    }

    // Delete User
    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm userDetailForm) {
        userService.deleteUserOne(userDetailForm.getUserId());

        return 0;
    }
}
