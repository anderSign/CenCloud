package org.scCloud.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.scCloud")
@EnableDiscoveryClient//这是除了奈菲以外的注解
public class SingleCenConsulClient {
    public static void main(String[] args) {
        SpringApplication.run(SingleCenConsulClient.class,args);
    }
}
