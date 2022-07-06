package com.example.springbootsample.domain.user.service;

import com.example.springbootsample.domain.user.model.MUser;

import java.util.List;

public interface UserService {
    // Signup user
    public void signup(MUser user);

    // Get users
    public List<MUser> getUsers(MUser user);

    // Get a user
    public MUser getUserOne(String userId);

    // Update user
    public void updateUserOne(String userId,
                              String password,
                              String userName);

    // Delete user
    public void deleteUserOne(String userId);
}
