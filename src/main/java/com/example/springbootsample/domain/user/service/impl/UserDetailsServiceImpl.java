package com.example.springbootsample.domain.user.service.impl;

import com.example.springbootsample.domain.user.model.MUser;
import com.example.springbootsample.domain.user.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Get user information
        MUser loginUser = userService.getLoginUser(username);

        // If the user does not exist
        if (loginUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Create authority list
        GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        // Generate UserDetails
        return (UserDetails) new User(loginUser.getUserId(), loginUser.getPassword(), authorities);
    }
}
