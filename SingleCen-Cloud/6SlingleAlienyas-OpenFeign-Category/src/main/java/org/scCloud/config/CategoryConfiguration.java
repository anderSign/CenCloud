package org.scCloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class CategoryConfiguration {


    @Bean//需要配置解析器
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartRe = new CommonsMultipartResolver();
        multipartRe.setMaxUploadSize(1024000000);
        multipartRe.setResolveLazily(true);
        multipartRe.setMaxInMemorySize(4096);
        multipartRe.setDefaultEncoding("UTF-8");//设置默认字符集
        return multipartRe;
    }

}
