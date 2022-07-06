package com.example.springbootsample.domain.user.service.impl;

import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    // User signup
    @Override
    public void signup(MUser user) {
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");
        mapper.insertOne(user);
    }

    // Get users
    @Override
    public List<MUser> getUsers(MUser user) {
        return mapper.findMany(user);
    }
    // Get a user
    @Override
    public MUser getUserOne(String userId) {
        return mapper.findOne(userId);
    }

    // Update user
    @Override
    public void updateUserOne(String userId,
                              String password,
                              String userName) {
        mapper.updateOne(userId, password, userName);
    }

    // Delete user
    @Override
    public void deleteUserOne(String userId) {
        int count = mapper.deleteOne(userId);
    }
}
