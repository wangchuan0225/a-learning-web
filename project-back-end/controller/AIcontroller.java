package com.example.myweb1.controller;

import com.example.myweb1.entity.AImessage;
import com.example.myweb1.service.AIservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AIcontroller {

    private final AIservice aiService;

    @Autowired
    public AIcontroller(AIservice aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/queryai")
    public String getAIResponse(@RequestBody AImessage aImessage) {
        return aiService.processAIRequest(aImessage);
    }
}
