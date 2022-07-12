package com.example.springbootsample.domain.user.model;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.Date;
import java.util.List;

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
    private Department department;
    private List<Salary> salaryList;
}