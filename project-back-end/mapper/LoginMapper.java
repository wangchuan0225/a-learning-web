package com.example.myweb1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myweb1.controller.LoginController;
import com.example.myweb1.entity.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Configuration;

@Mapper
public interface LoginMapper extends BaseMapper<Account> {
    @Select("select password from info where account=#{account}")
    String checkpassword(Account a1);
}
