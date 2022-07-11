package com.example.springbootsample.domain.user.service.impl;

import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // User signup
    @Override
    public void signup(MUser user) {
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");

        // Password encryption
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));

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
    @Transactional
    @Override
    public void updateUserOne(String userId,
                              String password,
                              String userName) {
        String encryptPassword = passwordEncoder.encode(password);

        mapper.updateOne(userId, encryptPassword, userName);
    }

    // Delete user
    @Override
    public void deleteUserOne(String userId) {
        int count = mapper.deleteOne(userId);
    }

    // Get Login user information
    @Override
    public MUser getLoginUser(String userId) {
        return mapper.findLoginUser(userId);
    }
}