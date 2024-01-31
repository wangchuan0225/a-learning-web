package com.example.myweb1.controller;

import com.example.myweb1.entity.AImessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@CrossOrigin
public class AIcontroller {
    private AImessage message;Functions functions;
    public AIcontroller(AImessage a){this.message=a;functions=new Functions();}
    @PostMapping("/queryai")
    public String getai(@RequestBody AImessage aImessage){
        String result="";
        try {
            System.out.println("use ai");
            // 指定要运行的Python脚本文件路径
            String pythonScriptPath = "E:/python/pythonProject6/spider/chatgpt.py";
            // 创建ProcessBuilder对象
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath,"use english to answer me," +
                    aImessage.getTexture());
            // 设置工作目录，如果有必要
            //processBuilder.directory(new File("C:/Users//wcnb/.spyder-py3/opencvtry"));

            // 启动进程并等待其完成
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            // 读取Python脚本的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String lines="";String line;
            while ((line = reader.readLine()) != null) {
                lines=lines+"\n"+line;
            }
            result=lines;
            // 打印Python脚本的退出码
            System.out.println("Selectresult script exited with code: " + exitCode);
            functions.writeToLog("ai",aImessage.getTexture());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
