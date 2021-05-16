package org.scCloud.controller;


import org.scCloud.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ProductProc")
public class ProductControllerByProc {
    //接受器
    private Logger logger= LoggerFactory.getLogger(ProductControllerByAbstract.class);


    private final static List<Product> list=new ArrayList<>();

    //一个map集合的底层实现类
    private Properties properties=new Properties();

    //一个map集合的底层实现类
    private Map<String,Object> map=new HashMap<>();

    public ProductControllerByProc() {
        //加入参数
        list.add(new Product("天使","门珀","19",new Date(),"遥远世界最早期的神明，拥有无上的权力及智慧!"));
        list.add(new Product("恶魔","血色之海","19",new Date(),"听说是由失去信仰的天使转换而来，由于世界观的崩塌，它们所追求已经不再是智慧，就连处理问题也变成了暴制暴的方式!"));
        list.add(new Product("痕","永恒之地","?",new Date(),"具体来历不详，但似乎是后时代毁灭的催化剂。"));
    }

    /**
     * 通过价格获取价格等于目标价格的参数对象
     * @return
     */
    @GetMapping("/{price}")
    public ArrayList<Object> getTest1(@PathVariable("price") String price){
        ArrayList<Object> objects = new ArrayList<>();
        //直接使用lambuda表单式赋值
        list.forEach(obj->{
            if (price.equals(obj.getPrice()))objects.add(obj);
        });
        logger.info("参数获取成功!对象是:{}",objects);
        return objects;
    }


    /**
     * 通过价格获取价格等于目标价格的参数对象
     * @return
     */
    @GetMapping("/Properties/{price}")
    public Properties getTest2(@PathVariable("price") String price){
        ArrayList<Object> objects = new ArrayList<>();
        //直接使用lambuda表单式赋值
        list.forEach(obj->{
            if (price.equals(obj.getPrice()))objects.add(obj);
        });
        properties.put("value",objects);
        properties.put("sum",list.size());
        properties.put("index",1);
        properties.put("length",3);
        logger.info("参数获取成功!对象是:{}",properties);
        return properties;
    }


    /**
     * 通过价格获取价格等于目标价格的参数对象
     * @return
     */
    @GetMapping("/Map/{price}")
    public Map<String, Object> getTest3(@PathVariable("price") String price){
        ArrayList<Object> objects = new ArrayList<>();
        //直接使用lambuda表单式赋值
        list.forEach(obj->{
            if (price.equals(obj.getPrice()))objects.add(obj);
        });
        map.put("value",objects);
        map.put("sum",list.size());
        map.put("index",1);
        map.put("length",3);
        logger.info("参数获取成功!对象是:{}",map);
        return map;
    }


    /**
     * 通过价格获取价格等于目标价格的参数对象
     * @return
     */
    @GetMapping("/PropertiesByObj/{price}")
    public Properties getTest4(@PathVariable("price") String price){
        ArrayList<Object> objects = new ArrayList<>();
        //直接使用lambuda表单式赋值
        list.forEach(obj->{
            if (price.equals(obj.getPrice()))objects.add(obj);
        });
        properties.put("value",objects);
        properties.put("sum",list.size());
        properties.put("index",1);
        properties.put("length",3);
        logger.info("参数获取成功!对象是:{}",properties);
        return properties;
    }


    /**
     * 测试超时
     * @return
     */
    @GetMapping("/TimeOut")
    public String getTest4() throws InterruptedException {
        logger.info("正在测试超时");
        //超时3秒
        Thread.sleep(10001);
        logger.info("测试超时结束");
        return "测试结束";
    }
}
