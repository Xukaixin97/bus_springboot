package com.example.blq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.blq.mapper")
public class BlqApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlqApplication.class, args);
    }

}
