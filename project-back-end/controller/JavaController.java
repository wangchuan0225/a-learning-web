package com.example.myweb1.controller;


import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.mapper.JavaMapper;
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
public class JavaController {
    JavaMapper javaMapper;
    List<Selectresult>list=new ArrayList<>();
    Functions functions;
    @Autowired
    JavaController(JavaMapper a){this.javaMapper=a;functions=new Functions();}
    void updatedata(){
        this.javaMapper.deletejava();
        try {
            // 执行exe文件
            this.javaMapper.deletejava();
            Process proc = Runtime.getRuntime().exec("E:/python/pythonProject6/spider/dist/demo2.exe java");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/learning/java")
    public List<Selectresult>selectFunc()
    {
        this.list.clear();
        //this.updatedata();
        Random random = new Random();
        int[] numbers = random.ints(5, 0, 19).toArray();
        for (int num : numbers) {
            Selectresult temp=this.javaMapper.select(String.valueOf(num));
            this.list.add(temp);
        }
        Iterator<Selectresult> iterator = list.iterator();
        while (iterator.hasNext()) {
            Selectresult selectresult = iterator.next();
            System.out.println(selectresult);
        }
        functions.writeToLog("check","java");
        return list;
    }
    @PostMapping("/update/java")
    public void update()
    {
        this.updatedata();
        functions.writeToLog("updata","java");
    }

}
