package com.example.myweb1.controller;

import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.mapper.PythonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import  java.io.*;
@RestController
@CrossOrigin
// 对于python页面有查询和更新两个操作，分别为两个操作定义接口
public class PythonController {
    // 对于返回值来说需要以list方式将查询到结果返回到前端
    List<Selectresult>list=new ArrayList<>();
    PythonMapper pythonMapper;
    Functions functions;
    @Autowired
    // 初始化该controller
    public PythonController(PythonMapper a)
    {
        this.pythonMapper=a;
        functions=new Functions();
    }
    void updatedata(){
        // 清空原数据库数据
        this.pythonMapper.deletepython();
        try {
            // 执行exe文件
            this.pythonMapper.deletepython();
            // 执行爬虫进行更新
            Process proc = Runtime.getRuntime().exec("E:/python/pythonProject6/spider/dist/demo2.exe python");
            // 读入输出
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    @PostMapping("/learning/python")
    // 执行查询操作的接口
        public List<Selectresult> loginfunc()
    {
        this.list.clear();
        // 生成五个随机数并将其传入mapper中进行数据查询出五条结果
        Random random = new Random();
        int[] numbers = random.ints(5, 0, 19).toArray();
        for (int num : numbers) {
            Selectresult temp=this.pythonMapper.select(String.valueOf(num));
            this.list.add(temp);
        }
        // 操作写入日志
       functions.writeToLog("check","Python");

        return list;
    }
    @PostMapping("/update/python")
    // 更新操作接口
        public void update()
    {
        // 执行更新操作
        this.updatedata();
        functions.writeToLog("updata","python");
    }
}
