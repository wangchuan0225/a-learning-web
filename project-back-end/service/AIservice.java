package com.example.myweb1.service;

import com.example.myweb1.entity.AImessage;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class AIservice {

    private final Functions functions;

    public AIservice() {
        this.functions = new Functions();
    }

    public String processAIRequest(AImessage aImessage) {
        String result = "";
        try {
            System.out.println("use ai");
            // 定义ai脚本位置
            String pythonScriptPath = "E:/python/pythonProject6/spider/chatgpt.py";
            // 使用processBuilder创建进程，使用python执行
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath,
                    "use english to answer me," + aImessage.getTexture());

            // 等待进程结束
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            // 读出输出并使用utf-8编码
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            StringBuilder lines = new StringBuilder();
            String line;
            // 将输出结果连成字符串
            while ((line = reader.readLine()) != null) {
                lines.append("\n").append(line);
            }
            result = lines.toString();

            // 输出结束代码并将操作写入日志
            System.out.println("Selectresult script exited with code: " + exitCode);
            functions.writeToLog("ai", aImessage.getTexture());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
