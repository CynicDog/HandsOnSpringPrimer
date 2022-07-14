package com.example.springbootsample.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RestResult {
    // return code
    private int result;

    // error Map (key: field name, value: error message)
    private Map<String, String> errors;
}
