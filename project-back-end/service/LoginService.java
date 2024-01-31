package com.example.myweb1.service;
import java.io.*;
import com.example.myweb1.entity.Account;
import com.example.myweb1.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginService {

    private final Account account;
    private final LoginMapper mapper;
    private final Functions functions;

    @Autowired
    public LoginService(Account account, LoginMapper mapper) {
        this.account = account;
        this.mapper = mapper;
        this.functions = new Functions();
    }

    public String performLogin(Account inputAccount) {
        String result = executePythonScript();
        String storedPassword = mapper.checkpassword(inputAccount);

        if (storedPassword != null && storedPassword.equals(inputAccount.getPassword())) {
            functions.writeToLog("Login", inputAccount.getAccount());
            return result;
        } else {
            return "0";
        }
    }

    private String executePythonScript() {
        String result="";
        try {
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
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        // 向日志中写入操作
        functions.writeToLog("Login",account.getAccount());
        return result;  // Replace with the actual result from the Python script
    }
}
