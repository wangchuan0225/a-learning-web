package com.example.myweb1.controller;

import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PythonController {
    // 初始化service
    private final PythonService pythonService;
    @Autowired
    public PythonController(PythonService pythonService) {
        this.pythonService = pythonService;
    }
    // 当post请求查询时调用如下函数返回值为list
    @PostMapping("/learning/python")
    public List<Selectresult> executeQuery() {
        return pythonService.executeQuery();
    }
    // 更新请求时执行更新函数
    @PostMapping("/update/python")
    public void executeUpdate() {
        pythonService.executeUpdate();
    }
}
