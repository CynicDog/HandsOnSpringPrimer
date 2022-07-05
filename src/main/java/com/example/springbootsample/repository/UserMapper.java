package com.example.springbootsample.repository;

import com.example.springbootsample.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // Signup user
    public int insertOne(MUser user);

    // Get user
    public List<MUser> findMany();
}