package org.singlecen.controller;

import org.singlecen.feignclient.MyRequestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RequestTestController {
    private static Logger logger=Logger.getGlobal();


    //注入一个RestTemplate
    @Autowired
    private RestTemplate restTemplate;
    //ribbon的服务发现接口
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    //设置一个计数器
    private int index;
    @Autowired
    private MyRequestFeign myRequestFeign;

    @GetMapping
    public String getRequest1(){
        logger.log(Level.INFO,"正在尝试发送请求");
        //无负载均衡，路径写死不利于后续的集群部署和负载均衡
        String result=null;//1.restTemplate.getForObject("http://localhost:13000/13/谢孟伟",String.class)
        //2.restTemplate.getForObject("http://[服务名]/13/谢孟伟",String.class)  必须添加注解@LoadBalanced
        //使用ribbon  获取指定服务名下的所有可用的集群实例
        List<ServiceInstance> instances = discoveryClient.getInstances("Test1");
        //自定义轮询策略的实现
        // result=doRestTemplate(instances);
        //比较完善的操作 通过轮询的loadbalanceclient来实现的自动抓取客户端信息
        result=restTemplate.getForObject(loadBalancerClient.choose("Test1").getUri()+"/13/谢孟伟",String.class);
        logger.log(Level.INFO,"正在尝试接受请求");
        //二次请求
        result+="\n二次请求"+ myRequestFeign.doCallPublic(1,"张三");
        return result;
    }

    /**
     * 轮询策略
     * @param instances
     * @return
     */
    private String doRestTemplate(List<ServiceInstance> instances) {
        String str=null;
        //如果index大于等于链表的长度直接重置为0
        if (index>=instances.size())index=0;
        for (int i = 0; i < instances.size(); i++) {
            if (i==index) {
                str=restTemplate.getForObject(instances.get(i).getUri()+"/13/谢孟伟",String.class);
                break;
            }
        }
        index++;
        return str;
    }
}
