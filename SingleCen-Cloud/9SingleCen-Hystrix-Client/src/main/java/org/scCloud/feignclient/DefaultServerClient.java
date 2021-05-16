package org.scCloud.feignclient;

import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class DefaultServerClient implements ServerClient{

    //添加jdk的日志
    private static Logger logger=Logger.getLogger("HystrixClientController");

    @Override//但是并未转到相应的方法中去，原因可能是版本不一致造成的
    public String doCallServer1(int id) {
        logger.log(Level.INFO,"出错了!["+id+"]");
        return "这个怕是不行吧!";
    }
}
