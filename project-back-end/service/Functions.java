package com.example.myweb1.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 定义日志写入函数，向指定路径文件中写入操作，内容和时间
public class Functions {
    // 传入操作和内容
    public void writeToLog(String option,String text){
        String filePath = "E:/java/Myweb1/web/log.txt"; // 替换为你的文件路
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter); // 获取当前时间并转化为固定格式
        String contentToAppend = formattedNow+" "+option+" "+text; // 替换为你想要追加的内容
        try (FileWriter fw = new FileWriter(filePath, true); // true表示以追加模式打开文件
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(contentToAppend); // 将内容写入
            bw.newLine(); // 添加换行符
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
