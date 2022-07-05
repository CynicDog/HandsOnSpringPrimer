package com.example.springbootsample.domain.user.service;

import com.example.springbootsample.domain.user.model.MUser;

import java.util.List;

public interface UserService {
    // Signup user
    public void signup(MUser user);

    // Get user
    public List<MUser> getUsers();
}
