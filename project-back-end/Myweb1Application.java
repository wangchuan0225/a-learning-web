package com.example.myweb1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.myweb1.mapper")
public class Myweb1Application {

    public static void main(String[] args) {
        SpringApplication.run(Myweb1Application.class, args);
    }

}
