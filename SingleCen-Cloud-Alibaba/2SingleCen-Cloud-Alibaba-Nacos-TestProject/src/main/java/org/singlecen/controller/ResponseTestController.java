package org.singlecen.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ResponseTestController {
    private static Logger logger=Logger.getGlobal();

    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}/{name}")
    public String getRequest1(@PathVariable Integer id,@PathVariable String name){
        logger.log(Level.INFO,"数据调用成功:正在打印数据信息\nid:"+id+"\tname:"+name);
        return "数据调用成功:正在打印数据信息\nid:"+id+"\tname:"+name+"\npost:"+port;
    }
}
