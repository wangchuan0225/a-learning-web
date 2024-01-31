package com.example.myweb1.controller;

import com.example.myweb1.entity.AImessage;
import com.example.myweb1.entity.Selectresult;
import com.example.myweb1.service.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GoogleController {

    private final GoogleService googleService;

    @Autowired
    public GoogleController(GoogleService googleService) {
        this.googleService = googleService;
    }

    @PostMapping("/google")
    public List<Selectresult> searchFromGoogle(@RequestBody AImessage aImessage) {
        return googleService.searchFromGoogle(aImessage);
    }
}
