package org.scCloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //手动刷新,可以理解成目标为git仓库的热部署
public class ClientController {

    //直接从云端拿数据
    @Value("${singlecen.developer.public}")
    private String common;

    @GetMapping
    public String getInfo(){
        return "singlecen.developer.public="+common;
    }

}
