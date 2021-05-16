package org.scCloud.feignclient;

import org.scCloud.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 类别的FeignClient是主要的请求发送端，目标对象是产品模块，另外这与产品模块是否需要回调没有任何之间关系，只是测试数据的接口。
 * 子客户端特征:常规对象及自定义实体类处理
 * 注意:在依赖上只需要包含此依赖就可以了
 */
@FeignClient(value = "ProductApplication")
public interface CategoryClient {

    /*
    ##########========== 改部分包含非标准特殊对象及普通对象的传递 ==========##########
     */
    /**
     * 普通的访问没什么新意
     */
    @GetMapping("/Product")
    String getProduct0();

    /**
     * 带有简单参数的访问
     */
    @GetMapping("/Product/Base")
    String getProduct1(@RequestParam("info") String info1,@RequestParam("info2") String info2);

    /**
     * 带有特殊对象的访问
     */
    @GetMapping("/Product/Base1")
    String getProduct1(@RequestParam("info1") String info1,@RequestParam("info2") String info2,@RequestParam("info3") Object info3);

    /**
     * Rest普通访问
     */
    @GetMapping("/Product/Rest/{info1}/{info2}")
    String getProduct2(@PathVariable String info1, @PathVariable String info2);

    /**
     * Rest带有特殊对象访问(不包含数组)
     */
    @GetMapping("/Product/Rest/{info1}/{info2}/{info3}")
    String getProduct3(@PathVariable String info1, @PathVariable String info2,@PathVariable Object info3);

    /**
     * Post带有自定义实体类的访问
     */
    @PostMapping("/Product")
    String getProduct4(@RequestBody Product info,@RequestParam("key") String key);

    /**
     * Rest带有自定义实体类的访问
     */
    @GetMapping("/Product/proc/{info1}/{keys}")
    String getProduct5(@PathVariable("info1") Product info1,@PathVariable("keys") String key);


    /**
     * 同类多实体类传递测试:测试失败无法正常启动服务
     */
    // @PostMapping("/Product/Convert")//注意:这种写法是不对的，尽管再多个相同类型的实体类参数再编译时不会报错，但这在spring框架中是不被允许的，因此不会成功的
    // void getProduct6(@RequestBody Product var1,@RequestBody Product var2, String var3);

    /**
     * 非同类多实体类传递测试:测试失败无法正常启动服务
     */
    // @PostMapping("/Product/Convert1")
    // void getProduct7(@RequestBody Product var1, @RequestBody Map<String, Object> var2, String var3);

    /**
     * 多字符串附件实体类的混乱传递:测试成功但是对参数及参数名有严格要求
     */
    @PostMapping("/Product/Convert0")//去掉varx的@RequestParam("varx")将无法正常启动
    void getProduct8(@RequestBody Product var1,@RequestParam("var2") String var2, @RequestParam("var3") String var3);

    /**
     * 请求体传递参数   强行传递参数会有一定的难度，这个后面说
     *///后面说
    @PostMapping(value="/Product/Convert3",produces = "multipart/form-data;charset=UTF-8")//去掉varx的@RequestParam("varx")将无法正常启动@RequestPart("var1") Product product2,
    void getProduct9(@RequestPart("var2") String var2, @RequestPart("var1") String var3);//name = MediaType.MULTIPART_FORM_DATA_VALUE





}
