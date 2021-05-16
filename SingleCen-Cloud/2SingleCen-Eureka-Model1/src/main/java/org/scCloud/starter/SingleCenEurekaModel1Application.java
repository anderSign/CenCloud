package org.scCloud.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.scCloud")
@EnableEurekaClient
public class SingleCenEurekaModel1Application {
    public static void main(String[] args) {
        SpringApplication.run(SingleCenEurekaModel1Application.class,args);
    }
}
