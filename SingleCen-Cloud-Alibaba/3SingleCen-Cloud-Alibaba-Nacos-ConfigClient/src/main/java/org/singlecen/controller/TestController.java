package org.singlecen.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RefreshScope//允许远端刷新
public class TestController {
    private static Logger logger=Logger.getGlobal();

    @Value("${sc.code.info}")
    private String str;

    @RequestMapping
    public String doTest0(){
        logger.log(Level.INFO,"调用成功");
        return "调用成功:"+str;
    }
}
