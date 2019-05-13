package com.example.blq.mapper;

import com.example.blq.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    Admin login(@Param("username") String username, @Param("password") String password);
    int regist(Admin admin);
    List checkUsernameIfExit(@Param("username") String username);
}
