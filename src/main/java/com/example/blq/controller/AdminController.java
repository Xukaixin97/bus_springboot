package com.example.blq.controller;


import com.example.blq.pojo.Admin;
import com.example.blq.service.AdminSerivce;
import com.example.blq.utils.GetSMS;
import com.example.blq.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    //s
    @Autowired
    private AdminSerivce adminSerivce;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/login")
    public Admin login(HttpServletRequest request, String username, String password) {
        System.out.println("用户名:" + username + "密码:" + password);
        Admin admin = adminSerivce.login(username, password);
        if(admin !=null) {
            return admin;
        }
        return admin;
    }

    /**
     * 小程序用户注册
     * @param admin 前端提交的用户信息
     * @return
     */
    @RequestMapping("/regist")
    public boolean register(@RequestBody Admin admin) {
        admin.setId(UUIDUtil.getUUID());
        System.out.println("管理员" + admin);
        int regist = adminSerivce.register(admin);
        if (regist > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 获取手机验证码
     * @param email
     * @return
     */
    @RequestMapping("/sendMsg")
    public String getSMS(String email) {
        System.out.println(email);
        String code = GetSMS.randomCode();
        System.out.println(code);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("18751571355@163.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("这是一条验证码信息");
        simpleMailMessage.setText("欢迎您注册，验证码为:"+code+",请返回客户端输入");
        mailSender.send(simpleMailMessage);
        return code;
    }
    /**
     * 判断管理员名是否存在
     * @param username
     * @return
     */
    @RequestMapping("/checkUsernameIfExit")
    public boolean checkUsernameIfExit(String username) {
        System.out.println(username);
        List admin = adminSerivce.checkUsernameIfExit(username);
        if (admin.size() >0) {
            return true;
        }
        return false;
    }
}