package com.example.myweb1.controller;

import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.service.JavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class JavaController {

    private final JavaService javaService;

    @Autowired
    public JavaController(JavaService javaService) {
        this.javaService = javaService;
    }

    @PostMapping("/learning/java")
    public List<Selectresult> selectFunc() {
        return javaService.executeQuery();
    }

    @PostMapping("/update/java")
    public void update() {
        javaService.executeUpdate();
    }
}
