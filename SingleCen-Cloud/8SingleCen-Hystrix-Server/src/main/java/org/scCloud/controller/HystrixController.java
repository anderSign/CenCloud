package org.scCloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.jmx.export.metadata.InvalidMetadataException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("Hystrix")
public class HystrixController {

    //添加jdk的日志
    private Logger logger=Logger.getLogger("HystrixClientController");


    /**
     * 直接返回ok!    (无熔断器的情况下,是没有次数限制的)
     * @return
     */
    @GetMapping
    @HystrixCommand(fallbackMethod = "doSimpleTestByAutoCall")//注意你的返回对象和参数对象必须一致。
    public String doSimpleTest(int id){
        logger.log(Level.INFO,"正在执行第二层调用\t调用对象:id="+id);
        if (id<0){
            throw new InvalidMetadataException("怎么回事,好好说话");
        }
        return "That is ok!";
    }

    /**
     * 直接返回ok!    (无熔断器的情况下,是没有次数限制的)
     * @return
     */
    @GetMapping("/Default")
    @HystrixCommand(defaultFallback = "defaultAutoCall")//注意你的返回对象和参数对象必须一致。
    public String doSimpleTest1(int id){
        if (id<0){
            throw new InvalidMetadataException("怎么回事,好好说话");
        }
        return "That is ok!";
    }

    /**
     * 失败doSimpleTest时调用的方法
     * @param id
     * @return
     */
    public String doSimpleTestByAutoCall(int id){
        return "It`s not ok ,your id is "+ id;
    }

    /**
     * 失败doSimpleTest1时调用的默认方法
     * @return
     */
    public String defaultAutoCall(){
        return "系统繁忙请重试!";
    }
}
