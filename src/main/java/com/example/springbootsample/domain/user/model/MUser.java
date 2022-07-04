package com.example.springbootsample.domain.user.model;

import lombok.Data;

import java.sql.Date;

@Data
public class MUser {
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Integer departmentId;
    private String role;
}
