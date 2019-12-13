package com.example.blq.service;

import com.example.blq.mapper.UserMapper;
import com.example.blq.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List login(String username,String password) {
        return userMapper.login(username,password);
    }

    public int register(User user){
        return  userMapper.register(user);
    }

    public List getUserInfo(String nameKeyWord,String telephoneKeyWord ) {
        return userMapper.getUserInfo(nameKeyWord,telephoneKeyWord);
    }

    public int deleteUser(String id){
        return userMapper.deleteUser(id);
    }

    public List checkUsernameIfExit(String username){
        return userMapper.checkUsernameIfExit(username);
    }

    public int queryCount(){
        return  userMapper.queryCount();
    }

    //TODO 嗲版
}