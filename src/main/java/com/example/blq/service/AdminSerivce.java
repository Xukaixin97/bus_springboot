package com.example.blq.service;

import com.example.blq.mapper.AdminMapper;
import com.example.blq.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSerivce {
    @Autowired
    private AdminMapper adminMapper;

    public Admin login(String username, String password) {
        return adminMapper.login(username, password);
    }

    public int register(Admin admin) {
        return adminMapper.regist(admin);
    }

    public List checkUsernameIfExit(String username) {
        return adminMapper.checkUsernameIfExit(username);
    }
}