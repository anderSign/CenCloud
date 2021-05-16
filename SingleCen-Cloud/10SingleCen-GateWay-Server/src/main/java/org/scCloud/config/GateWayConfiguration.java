package org.scCloud.config;

import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfiguration {


/*    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes().route("product-router",r->r.path("/ProductProc/**","/ProductByAbstract/**").uri("http://localhost:9607")).build();//常规写法:http://localhost:9607
        //通用写法:lb://ProductApplication
    }*/

}
