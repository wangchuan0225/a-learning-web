package com.example.myweb1.service;

import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.mapper.JavaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class JavaService {

    private final JavaMapper javaMapper;
    private final Functions functions;

    @Autowired
    public JavaService(JavaMapper javaMapper) {
        this.javaMapper = javaMapper;
        this.functions = new Functions();
    }

    public List<Selectresult> executeQuery() {
        List<Selectresult> resultList = new ArrayList<>();
        Random random = new Random();
        int[] numbers = random.ints(5, 0, 19).toArray();
        for (int num : numbers) {
            Selectresult temp = javaMapper.select(String.valueOf(num));
            resultList.add(temp);
        }
        functions.writeToLog("check", "java");
        return resultList;
    }

    public void executeUpdate() {
        updatedata();
        functions.writeToLog("update", "java");
    }

    private void updatedata() {
        try {
            // Clear original database data
            this.javaMapper.deletejava();

            // Execute exe file
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
}
