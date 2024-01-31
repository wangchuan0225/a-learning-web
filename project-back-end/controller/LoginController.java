package com.example.myweb1.controller;
import java.io.*;

import com.example.myweb1.entity.Account;
import com.example.myweb1.mapper.LoginMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LoginController {
    private Account account;
    private LoginMapper mapper;
    Functions functions;
    @Autowired

    public LoginController(Account a1, LoginMapper l1) {
        // 定义mapper向数据库中写入，function向日志中写入以及实体对象
        this.account = a1;
        this.mapper = l1;
        functions=new Functions();
    }

    @PostMapping("/login") //当访问login时调用该部分，用account接收输入的账号密码信息
    public String loginfunc(@RequestBody Account account) {
        String result = "";
        try {
            // 指定要运行的Python脚本文件路径，调用pca进行人脸识别
            String pythonScriptPath = "C:/Users/wcnb/.spyder-py3/opencvtry/test.py";
            // 创建ProcessBuilder对象
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
            // 设置工作目录，如果有必要
            processBuilder.directory(new File("C:/Users//wcnb/.spyder-py3/opencvtry"));
            // 启动进程并等待其完成
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            // 读取Python脚本的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                result = line; // 接收程序返回结果并将其返回到前端进行判断
                break;
            }
            // 打印Python脚本的退出码
            System.out.println("Selectresult script exited with code: " + exitCode);
            // 向日志中写入操作
            functions.writeToLog("Login",account.getAccount());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        String i1 = mapper.checkpassword(account);
        if(i1!=null&&i1.equals(account.getPassword()))
        {
            return result;
        }
        else
            return "0";

    }
}
