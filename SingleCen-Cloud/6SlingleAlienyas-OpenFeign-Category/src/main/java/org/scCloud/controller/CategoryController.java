package org.scCloud.controller;

import org.scCloud.feignclient.CategoryClient;
import org.scCloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("Category")
public class CategoryController {
    //JDK官方提供的注解不好用,但也不是不能用
    private static Logger logger= Logger.getLogger("OrderLogger");
    //获取到当前的端口号
    @Value("${server.port}")
    private int port;
    //导入openfeign代理客户端
    @Autowired
    private CategoryClient categoryClient;

    Map<String,Object> map;

    public CategoryController() {
        map=new HashMap<>();
        map.put("key1","张三");
        map.put("key2","石头人");
        map.put("key3","开心果");
    }

    //自定义一个对象
    private Product product1=new Product("天使","门珀","19",new Date(),"遥远世界最早期的神明，拥有无上的权力及智慧!");
    private Product product2=new Product("恶魔","血色之海","19",new Date(),"听说是由失去信仰的天使转换而来，由于世界观的崩塌，它们所追求已经不再是智慧，就连处理问题也变成了暴制暴的方式!");

    //无参数传递的方法
    @GetMapping
    public String getTest1(){
        logger.log(Level.INFO,"无参数传递的方法","info1");
        /*
        注意下面被注释掉的部分就是因为缺乏数据的验证性
         */
        // categoryClient.getProduct1("张三","李四");
        /*
        这部分包含了一个map对象
         */
        categoryClient.getProduct1("张三","李四",map);
        return "调用了类别:["+port+"]";
    }

    //含有参数传递的方法
    @GetMapping("/Rest/{name}/{age}")
    public String getTest2(@PathVariable String name,@PathVariable String age){
        logger.log(Level.INFO,"含有参数的传递方法","info1");
        //rest参数传递  普通传递可能看不出什么不同，但是如果传递的是复杂数据类型就会发现rest传参的方式就是简单的json字符串。
        // categoryClient.getProduct2(name,age);
        categoryClient.getProduct3(name,age,map);
        return "调用了类别:["+port+"]";
    }

    //含有对象参数的方法
    @GetMapping("/Post")
    public String getTestByObjPost(){
        logger.log(Level.INFO,"对象参数Post","info1");
        //反转测试:结果成功
        categoryClient.getProduct4(product1,"1");
        //同类多实体类传递测试:测试失败无法正常启动服务  后端代码已经注掉
        // categoryClient.getProduct6(product1,product2,"1");
        //非同类多实体类传递测试:测试失败无法正常启动服务  后端代码已经注掉
        // categoryClient.getProduct7(product1,map,"1");
        //多字符串附件实体类的混乱传递
        categoryClient.getProduct8(product2,"key","value");
        //form表单传递   传递的复杂度远远大于其他传递方式不推荐
        // categoryClient.getProduct9("key","value");//product2,
        return "调用了类别:["+port+"]";
    }
    //含有对象参数的方法
    @GetMapping("/Get")
    public String getTestByObjGet(){
        logger.log(Level.INFO,"对象参数Get","info1");
        categoryClient.getProduct5(product1,"2");
        return "调用了类别:["+port+"]";
    }
}
