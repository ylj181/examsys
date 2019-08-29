package com.qfedu.examsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qfedu.examsys.dao")
public class ExamsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamsysApplication.class, args);
    }

}
