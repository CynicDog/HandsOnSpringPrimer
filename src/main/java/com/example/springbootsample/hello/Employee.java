package com.example.springbootsample.hello;

import lombok.Data;

@Data
// @Data: An annotation provided by Lombok. With this, the following methods
//       are automatically generated: getter, setter, toString, hashCode, and equals.
public class Employee {
    private String employeeId;
    private String employeeName;
    private int employeeAge;
}



