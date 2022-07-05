package com.example.springbootsample.form;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetailForm {
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
}
