package com.example.springbootsample.repository;

import com.example.springbootsample.domain.user.model.MUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository <MUser, String> {

    /** Search Login User */
    @Query("select user from MUser user where userId = :userId")
    public MUser findLoginUser(@Param("userId") String userId);

    /** Update User */
    @Modifying
    @Query("update MUser set password = :password, userName = :userName where userId = :userId")
    public Integer updateUser(@Param("userId") String userId,
                              @Param("password") String password,
                              @Param("userName") String userName);
}
