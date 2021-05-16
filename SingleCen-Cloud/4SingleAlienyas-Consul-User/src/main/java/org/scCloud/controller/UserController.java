package org.scCloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("User")
public class UserController {
    //很普通的日志没什么新意
    private static final Logger logger= Logger.getLogger("OrderLogger");

    //服务注册和发现客户端对象(注意该对象一般只是用户当前注册对象中特点的服务名集合，使用该方法，具体的负载均衡还是需要用户来实现的)
    @Autowired
    private DiscoveryClient discoveryClient;
    //负载均衡客户端(听名字就是已经实现负载均衡的客户端，在这种情况下，用户无需在其的基础上进行开发)
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    //直接获取负载均衡的对象
    @Autowired//但是这种操作仍然无法处理路径写死的情况
    private RestTemplate restTemplate;

    @GetMapping
    public String getTest(){
        String[] strs=new String[]{"张三","张四","张五"};
        logger.log(Level.INFO,"用户信息被调用! [info]:{"+strs.toString()+"}","张三");
        //原始写法
        // String data = restTemplate.getForObject("http://localhost:"+getRandom()+"/Order/" + "李四", String.class);
        String info1 = toOrderByDiscoveryClient();
        toOrderByLoadBalancerClient();
        String info3 = toOrderByLoadBalanceClient();
        logger.log(Level.FINER,"订单信息调用成功!");
        return "\t调用端口:"+info1+"和"+",["+info3+"]";
    }

    /**
     * 最终的写法
     * @return
     */
    private String toOrderByLoadBalanceClient() {
        return restTemplate.getForObject("http://OrdersApplication:"+"/Order/" + "李四", String.class);
    }

    /**
     * 测试负载均衡
     * @return
     */
    private String toOrderByLoadBalancerClient() {
        ServiceInstance choose = loadBalancerClient.choose("OrdersApplication");
        return "[port:]"+choose.getPort();
    }

    /**
     * 获取需要负载均衡的对象
     */
    private String toOrderByDiscoveryClient() {
        List<ServiceInstance> serviceInstances=discoveryClient.getInstances("OrdersApplication");
        //打印对象
        Arrays.asList(serviceInstances).forEach(System.out::println);
        return "并未指定";
    }

    /**
     * 随机的端口号
     * @return
     */
    private String getRandom() {
        List<String> hosts=new ArrayList<>();
        hosts.add("9604");
        hosts.add("9605");
        Random random=new Random();
        int index = random.nextInt(hosts.size());
        return hosts.get(index);
    }
}
