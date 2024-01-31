package com.example.myweb1.service;

import com.example.myweb1.entity.AImessage;
import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.mapper.GoogleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleService {

    private final GoogleMapper googleMapper;
    private final Functions functions;
    // 初始化
    @Autowired
    public GoogleService(GoogleMapper googleMapper) {
        this.googleMapper = googleMapper;
        this.functions = new Functions();
    }
    // 返回为list，而list中元素为定义好的对象
    public List<Selectresult> searchFromGoogle(AImessage aImessage) {
        List<Selectresult> resultList = new ArrayList<>();

        try {
            resultList.clear();
            // 定义文件路径
            String pythonScriptPath = "E:/python/pythonProject6/spider/google.py";
            // 新建进程执行
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, aImessage.getTexture());
            // 开始进程并等待结束
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            // 读取输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // 输出执行结果
            System.out.println("Selectresult script exited with code: " + exitCode);
            // 写入日志
            functions.writeToLog("google", aImessage.getTexture());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        resultList = googleMapper.selectFromGoogle();
        return resultList;
    }
}
