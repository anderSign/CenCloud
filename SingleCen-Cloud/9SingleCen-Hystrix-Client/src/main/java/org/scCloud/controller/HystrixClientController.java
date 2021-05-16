package org.scCloud.controller;


import org.scCloud.feignclient.ServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("Client")
public class HystrixClientController {
    //添加jdk的日志
    private Logger logger=Logger.getLogger("HystrixClientController");

    //导入需要的对象
    @Autowired
    private ServerClient serverClient;

    @GetMapping()
    public String toCall1(int id){
        logger.log(Level.INFO,"正在执行第一层调用\t调用对象:id="+id);
        return serverClient.doCallServer1(id);
    }

}
