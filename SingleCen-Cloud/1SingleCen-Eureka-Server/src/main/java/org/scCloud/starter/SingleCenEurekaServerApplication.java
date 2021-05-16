package org.scCloud.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//必要的依赖
@ComponentScan("org.scCloud")//如果在自定义的文件下最好加上
@EnableEurekaServer//必要的依赖
public class SingleCenEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SingleCenEurekaServerApplication.class,args);
    }
}
