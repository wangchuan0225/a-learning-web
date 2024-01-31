package com.example.myweb1.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class Account {
    String account;
    String password;
    void setAccount(String a)
    {
        this.account=a;
    }
    void setPassword(String a)
    {
        this.password=a;
    }
    public String getAccount()
    {
        return this.account;
    }
    public String getPassword()
    {
        return this.password;
    }
}
