package com.example.blq.mapper;

import com.example.blq.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List login(@Param("username") String username, @Param("password") String password);

    int register(User user);

    int deleteUser(@Param("id") String id);

    int queryCount();

    List getUserInfo(@Param("nameKeyWord") String nameKeyWord,
                     @Param("telephoneKeyWord") String telephoneKeyWord);

    List checkUsernameIfExit(@Param("username") String username);
}
