package com.chainxi.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.chainxi.system.mapper")
@SpringBootApplication
public class ChainXiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChainXiApplication.class);
    }
}
