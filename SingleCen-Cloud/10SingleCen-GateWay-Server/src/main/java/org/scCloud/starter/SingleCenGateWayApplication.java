package org.scCloud.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;

@SpringBootApplication
@ComponentScan("org.scCloud")
@EnableDiscoveryClient//这是除了奈菲(netflix)以外的注解
public class SingleCenGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SingleCenGateWayApplication.class,args);
    }
}
