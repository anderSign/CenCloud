package org.singlecen.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.singlecen")
@EnableFeignClients(basePackages = "org.singlecen.feignclient") //如果找不到你自定义的bean位置，那就最好加上这个
@EnableDiscoveryClient//未添加
public class SimpleTestProject {
    public static void main(String[] args) {
        SpringApplication.run(SimpleTestProject.class,args);
    }
}
