package org.scCloud.starter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.scCloud")
@EnableDiscoveryClient//这是除了奈菲(netflix)以外的注解
@EnableCircuitBreaker //最为关键的注解，但是已经过时，需要注意的是，在这里添加了注解仍然不够，还需要在对应的控制层的方法前加@HystrixCommand注解
public class HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class,args);
    }
}
