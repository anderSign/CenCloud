package org.scCloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BaseBeanConfiguration {

    /**
     * 添加RestTemplate的同时额外添加@LoadBalanced注解
     * 这样就不用像之前一样写端口号了
     */
    @Bean
    @LoadBalanced//添加此注解后就可以自动查找了
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
