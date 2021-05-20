package org.singlecen.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BaseConfig {

    @Bean
    // @LoadBalanced//添加了这个注解后将会把服务名作为指定策略的地址,这将会导致普通的地址查询无效
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
