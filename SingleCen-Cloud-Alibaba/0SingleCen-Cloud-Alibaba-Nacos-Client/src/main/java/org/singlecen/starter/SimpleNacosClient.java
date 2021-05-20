package org.singlecen.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
// import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@ComponentScan("org.singlecen")
@EnableDiscoveryClient//可写可不写的注解
public class SimpleNacosClient {
    public static void main(String[] args) {
        SpringApplication.run(SimpleNacosClient.class,args);
    }
}
