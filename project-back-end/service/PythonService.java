package com.example.myweb1.service;

import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.mapper.PythonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PythonService {

    private final PythonMapper pythonMapper;
    private final Functions functions;

    @Autowired
    public PythonService(PythonMapper pythonMapper) {
        this.pythonMapper = pythonMapper;
        this.functions = new Functions();
    }
    // 执行查询时返回值为list
    public List<Selectresult> executeQuery() {
        List<Selectresult> resultList = new ArrayList<>();
        Random random = new Random();
        // 随机出5个编号在数据库中选出对应条目
        int[] numbers = random.ints(5, 0, 19).toArray();
        for (int num : numbers) {
            Selectresult temp = pythonMapper.select(String.valueOf(num));
            resultList.add(temp);
        }
        functions.writeToLog("check", "Python");
        return resultList;
    }

    public void executeUpdate() {
        // Execute the update operation
        updatedata();
        functions.writeToLog("update", "Python");
    }

    private void updatedata() {
        try {
            // 清理数据库中数据
            this.pythonMapper.deletepython();

            // 执行生成好的exe文件
            Process proc = Runtime.getRuntime().exec("E:/python/pythonProject6/spider/dist/demo2.exe python");

            // 读取输出
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
