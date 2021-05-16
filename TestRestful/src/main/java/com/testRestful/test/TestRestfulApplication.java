package com.testRestful.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.testRestful")
@MapperScan("com.testRestful.dao")
public class TestRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestRestfulApplication.class, args);
    }

}
