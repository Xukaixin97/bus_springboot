package com.example.blq.controller;

import com.example.blq.pojo.User;
import com.example.blq.service.UserService;
import com.example.blq.utils.GetSMS;
import com.example.blq.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 小程序用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public boolean login(String username, String password) {
        System.out.println("微信小程序调用接口！！！用户名:" + username + "密码:" + password);
        List login = userService.login(username, password);
//        System.out.println(login);
        if (login.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 小程序用户注册
     *
     * @param user 前端提交的用户信息
     * @return
     */
    @RequestMapping("/register")
    public boolean register(@RequestBody User user) {

        user.setId(UUIDUtil.getUUID());
//        System.out.println("微信小程序调用接口！！！用户名:" + user);
        int regist = userService.register(user);
        if (regist > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 获取手机验证码
     *
     * @param telephone
     * @return
     */
    @RequestMapping("/getSMS")
    public String getSMS(String telephone) {
        String code = GetSMS.getSMS(telephone);
        return code;
    }

    /**
     * 后台获取用户信息
     *
     * @return
     */
    @RequestMapping("/getUserInfo")
    public PageInfo<User> getUserInfo(int pageSize, int currentPage, String nameKeyWord, String telephoneKeyWord ) {

        PageHelper.startPage(currentPage,pageSize);
        List userInfo = userService.getUserInfo(nameKeyWord,telephoneKeyWord);
        PageInfo<User> result = new PageInfo<>(userInfo);
        return result;

    }

    /**
     * 后台删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    public boolean deleteUser(String id) {
//        System.out.println(id);
        int result = userService.deleteUser(id);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 小程序判断用户名是否存在
     *
     * @param username
     * @return
     */
    @RequestMapping("/checkUsernameIfExist")
    public boolean checkUsernameIfExist(String username) {
        System.out.println(username);
        List user = userService.checkUsernameIfExit(username);
        if (user.size() > 0) {
            return true;
        }
        return false;
    }




}