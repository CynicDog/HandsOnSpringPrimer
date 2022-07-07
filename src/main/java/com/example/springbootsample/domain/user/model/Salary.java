package com.example.springbootsample.domain.user.model;

import lombok.Data;

@Data
public class Salary {
    private String UserId;
    private String yearMonth;
    private Integer salary;
}
