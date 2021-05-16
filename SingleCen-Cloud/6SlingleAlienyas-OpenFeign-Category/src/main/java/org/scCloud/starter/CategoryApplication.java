package org.scCloud.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.scCloud")
@EnableDiscoveryClient//这是除了奈菲(netflix)以外的注解
@EnableFeignClients({"org.scCloud.feignclient","org.scCloud"})//开启openfeign客户端的调用
public class CategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class,args);
    }
}
