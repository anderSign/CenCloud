package org.singlecen.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.singlecen")
@EnableDiscoveryClient
public class NacosSentinel {
    public static void main(String[] args) {
        SpringApplication.run(NacosSentinel.class,args);
    }
}
