package org.singlecen.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.metadata.InvalidMetadataException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    private final Logger logger= LoggerFactory.getLogger(TestController.class);

    @GetMapping
    @SentinelResource(value = "测试的数据",blockHandler = "blockHandler",fallbackClass = TestController.class,fallback = "toFallback")//这里遗留了一个问题那就是fallback无效的问题。defaultFallback = "defaultFallback"
    public String doTest(String id){
        logger.info("好!");
        if (Integer.parseInt(id)<0) throw new InvalidMetadataException("元数据访问错误");
        return "好";
    }

    public String blockHandler(String id,BlockException e){
        logger.info("{}号不可用!错误信息是:{}",id,e.getMessage());
        if (e instanceof FlowException){
            return "访问太多";
        }
        if (e instanceof ParamFlowException){
            return "参数限流";
        }
        if (e instanceof DegradeException){
            return "降级";
        }
        if (e instanceof AuthorityException){
            return "权限不够";
        }
        if (e instanceof SystemBlockException){
            return "系统调用异常";
        }

        return "不可用";
    }

    public String toFallback(){
        logger.info("返回异常!");
        return "返回异常";
    }
    public String defaultFallback(){
        logger.info("默认的返回异常!");
        return "默认的返回异常";
    }



    @GetMapping("/test")
    public String doTest1(){
        logger.info("很好!");
        return "很好";
    }
}
