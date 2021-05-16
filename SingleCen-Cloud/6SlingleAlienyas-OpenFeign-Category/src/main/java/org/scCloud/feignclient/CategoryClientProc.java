package org.scCloud.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/**
 * 虚拟参数的获取
 * 子客户端特征:综合处理(rest)
 * 注意:在依赖上只需要包含此依赖就可以了
 */
@FeignClient(value = "ProductApplication")
public interface CategoryClientProc {

    /**
     * 1.通过指定参数获取所有对象集合(实体类相同)
     * 特征:包含自定义实体类链表对象
     * 运行结果:成功
     */
    @GetMapping("/ProductProc/{price}")
    ArrayList<Object> getTest1(@PathVariable String price);


    /**
     * 2.通过指定参数获取Properties集合
     * 特征:包含自定义实体类链表对象,包含map
     * 运行结果:失败   报错: Cannot deserialize instance of `java.lang.String` out of START_ARRAY token
     * 报错原因:spring官方底层实体类的传参一般都是json字符串+Object的方式，当出现较为复杂类型数据的情况下，系统将会因为无法解析字符串而报错。
     * 异常类型:MismatchedInputException 缺失匹配的输入参数异常。
     * 解决方法 详见4操作
     */
    @GetMapping("/ProductProc/Properties/{price}")
    Properties getTest2(@PathVariable String price);


    /**
     * 3.通过指定参数获取Map集合
     * 特征:包含自定义实体类链表对象,包含map
     * 运行结果:成功
     */
    @GetMapping("/ProductProc/Map/{price}")
    Map<String,Object> getTest3(@PathVariable String price);


    /**
     * 4.自定义解析并获取Properties集合
     * 特征:包含自定义实体类链表对象,包含map,转换json字符串
     * 运行结果:成功
     */
    @GetMapping("/ProductProc/PropertiesByObj/{price}")
    String getTest4(@PathVariable String price);

    /**
     * 5.测试超时
     * 特征:使用Thread.sleep()方法来刻意设置超时时长
     * 运行结果:无影响(因为需要手动设置的)  老版本有影响的
     */
    @GetMapping ("/ProductProc/TimeOut")
    String getTest4();
}
