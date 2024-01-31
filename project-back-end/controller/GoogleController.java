package com.example.myweb1.controller;

import com.example.myweb1.entity.AImessage;
import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.mapper.GoogleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class GoogleController {
    private AImessage message;
    Functions functions;
    List<Selectresult>list=new ArrayList<>();
    private GoogleMapper mapper;
    @Autowired
    public GoogleController(GoogleMapper a){this.mapper=a;functions=new Functions();}
    @PostMapping("/google")
    public List<Selectresult> searchFormGoogle(@RequestBody AImessage aImessage){
        String message=aImessage.getTexture();
        try {
            this.list.clear();
            // 指定要运行的google爬虫脚本文件路径
            String pythonScriptPath = "E:/python/pythonProject6/spider/google.py";
            // 创建ProcessBuilder对象
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath,message);
            // 启动进程并等待其完成
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            // 读取Python脚本的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // 打印Python脚本的退出码
            System.out.println("Selectresult script exited with code: " + exitCode);
            // 写入日志
            this.functions.writeToLog("google",aImessage.getTexture());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        this.list=mapper.selectFromGoogle();
        return this.list;
    }
}
