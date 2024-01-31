package com.example.myweb1.entity;

import org.springframework.stereotype.Service;

@Service
public class AImessage {
    String texture;
    public void setTexture(String a) {this.texture=a;}
    public String getTexture(){return this.texture;}
}
