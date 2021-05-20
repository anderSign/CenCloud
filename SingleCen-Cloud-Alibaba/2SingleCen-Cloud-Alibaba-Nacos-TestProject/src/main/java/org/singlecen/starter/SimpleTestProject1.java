package org.singlecen.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.singlecen")
public class SimpleTestProject1 {
    public static void main(String[] args) {
        SpringApplication.run(SimpleTestProject1.class,args);
    }
}
