package com.example.myweb1.controller;

import com.example.myweb1.entity.Account;
import com.example.myweb1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    private final LoginService loginService;
    // 初始化service
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    // post请求进行登录
    @PostMapping("/login")
    public String login(@RequestBody Account account) {
        return loginService.performLogin(account);
    }
}
