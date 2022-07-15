package com.example.springbootsample.domain.user.service.impl;

import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import com.example.springbootsample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl2 implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    /** Signup User */
    @Transactional
    @Override
    public void signup(MUser user) {
        // existence check
        boolean exists = userRepository.existsById(user.getUserId());
        if (exists) { throw new DataAccessException("User already exists"){}; }

        // set default department and role
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");

        // password encryption
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));

        // insert
        userRepository.save(user);
    }

    /** Get Users*/
    @Override
    public List<MUser> getUsers(MUser user) {
        // search conditions
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        return userRepository.findAll(Example.of(user, exampleMatcher));
    }

    /** Get A User */
    @Override
    public MUser getUserOne(String userId) {
        Optional<MUser> option = userRepository.findById(userId);
        MUser user = option.orElse(null);

        return user;
    }

    /** Update User */
    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        // password encryption
        String encryptPassword = passwordEncoder.encode(password);

        // user update
        userRepository.updateUser(userId, encryptPassword, userName);
    }

    /** Delete User */
    @Override
    public void deleteUserOne(String userId) {
        userRepository.deleteById(userId);
    }

    /** Get Login User */
    @Override
    public MUser getLoginUser(String userId) {
        return userRepository.findLoginUser(userId);
    }
}
