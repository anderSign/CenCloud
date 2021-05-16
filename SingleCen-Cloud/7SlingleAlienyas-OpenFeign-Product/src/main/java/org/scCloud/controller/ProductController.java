package org.scCloud.controller;

import org.scCloud.feignclient.ProductClient;
import org.scCloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 该部分不会对极度特殊的对象进行处理，只会处理简单对象
 */
@RestController
@RequestMapping("Product")
public class ProductController {
    private static Logger logger= Logger.getLogger("OrderLogger");

    //获取到当前的端口号
    @Value("${server.port}")
    private int port;

    //导入openfeign代理客户端
    @Autowired
    private ProductClient productClient;

    /**
     * 无参数传递
     * @return
     */
    @GetMapping
    public String getTest(){//@PathVariable
        logger.log(Level.INFO,"产品信息");
        return "调用了产品:["+port+"]";
    }

    /**
     * 普通参数传递
     * @param info
     * @param info2
     * @return
     */
    @GetMapping("/Base")
    public String getTest1(String info, String info2){
        logger.log(Level.INFO,"产品信息:"+"["+info+"]+[{"+info2+"}]","info1");
        return "调用了产品:["+info+"]-["+port+"]";
    }
    /**
     * 普通参数传递   对于复杂数据类型的传参会get模式直接转换成也json字符串
     * @param info1
     * @param info2
     * @return
     */
    @GetMapping("/Base1")
    public String getTest1(String info1, String info2,@RequestParam("info3") Object info3){
        logger.log(Level.INFO,"产品信息:"+"["+info1+"]+[{"+info2+"}]","info1");
        return "调用了产品:["+info1+"]-["+port+"]";
    }

    /**
     * rest参数传递
     * @param info
     * @param info2
     * @return
     */
    @GetMapping("/Rest/{info}/{info2}")
    public String getTest2(@PathVariable String info,@PathVariable String info2){
        logger.log(Level.INFO,"产品信息:"+"["+info+"]+[{"+info2+"}]","info1");
        return "调用了产品:["+info+"]-["+port+"]";
    }

    /**
     * rest参数传递  对于复杂数据类型的传参会get模式直接转换成也json字符串
     * @param info
     * @param info2
     * @return
     */
    @GetMapping("/Rest/{info}/{info2}/{info3}")//需要注意的是我把{info1}改成了{info}但是不会影响结果的。
    public String getTest2(@PathVariable String info,@PathVariable String info2,@PathVariable Object info3){
        logger.log(Level.INFO,"产品信息:"+"["+info+"]+[{"+info2+"}]","info1");
        return "调用了产品:["+info+"]-["+port+"]";
    }

    /**
     * 实体类传递
     * 使用方式post方式
     */
    @PostMapping
    public String getTest3(String key,@RequestBody Product product){
        return "实体类测试成功\n实体类对象是:"+product.toString();
    }

    /**
     * 实体类传递
     * 使用方式get方式的Rest传参
     */
    @GetMapping("/proc/{info1}/{keys}")
    public String getTest4(@PathVariable String info1,@PathVariable String keys){
        return "实体类测试成功\n实体类对象是:"+info1.toString();
    }

    /**
     * 同类多实体类传递测试:测试失败,无法启动
     * 使用方式post方式
     */
    // @PostMapping("/Convert")
    // public String getTest3(String var1,@RequestBody Product var3,@RequestBody Product var2){
    //     return "实体类测试成功\n实体类对象是:"+var3.toString();
    // }

    /**
     * 非同类多实体类传递测试:测试失败,无法启动
     * 使用方式post方式
     */
    // @PostMapping("/Convert1")
    // public String getTest3(String var1,@RequestBody Product var3,@RequestBody Map<String,Object> var2){
    //     return "实体类测试成功\n实体类对象是:"+var3.toString();
    // }

    /**
     * 多字符串附件实体类的混乱传递:测试成功但是对参数及参数名有严格要求,注意一下下面var3和var6的值是相同的，而且var6不能没有注解否则会报错
     * 使用方式post方式
     */
    @PostMapping("/Convert0")//这里是打乱顺序的测试，且代码写的极度不规范
    public String getTest3(String var3,@RequestBody Product var1,@RequestParam("var3") String var6){
        return "实体类测试成功\n实体类对象是:"+var1.toString();
    }

    /**
     * form表单传递参数   传递的复杂度远远大于其他传递方式不推荐   注意此方法未使用到
     * 使用方式post方式
     */
    @PostMapping(value = "/Convert3",produces = "multipart/form-data;charset=UTF-8")//这里也是打乱顺序的测试，且代码写的极度不规范,@RequestPart("var1") Product var1
    public String getTest5(@RequestPart("var2") String var3,
                           @RequestPart("var3") String var6){
        return "实体类测试成功\n实体类对象是:"+var3.toString();
    }
}
