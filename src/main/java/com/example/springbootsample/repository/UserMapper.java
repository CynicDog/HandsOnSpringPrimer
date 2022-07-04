package com.example.springbootsample.repository;

import com.example.springbootsample.domain.user.model.MUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public int insertOne(MUser user);
}