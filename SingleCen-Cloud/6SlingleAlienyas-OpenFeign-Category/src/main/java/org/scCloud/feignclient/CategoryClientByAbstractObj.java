package org.scCloud.feignclient;


import org.scCloud.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 测试数据的接口。
 * 子客户端特征:特殊对象处理
 * 注意:在依赖上只需要包含此依赖就可以了
 */
@FeignClient(value = "ProductApplication")
public interface CategoryClientByAbstractObj {
    /*
    ##########========== 改部分包含非标准特殊对象及普通对象的传递 ==========##########
     */


    /**
     * 1.普通模式的get传递参数
     * 特征:三无大佬
     * 运行结果:报错:Request method 'POST' not supported，原因:系统不知道这个对象是什么对象，是否需要转换成[url]/字段?ids=?&ids=?...这种对象
     */
    @GetMapping("/ProductByAbstract")
    String doTest0(String[] strings);

    /**
     * 1.普通模式的get传递参数
     * 特征:添加请求参数体
     * 运行结果:报错:Request method 'POST' not supported，原因:系统不知道这个对象是什么对象，是否需要转换成[url]/字段?ids=?&ids=?...这种对象
     */
    @GetMapping("/ProductByAbstract")
    String doTest1(@RequestParam("strs") String[] strings);

    /**
     * 2.普通模式的get传递参数
     * 特征:添加请求参数体
     * 运行结果:成功
     */
    @GetMapping("/ProductByAbstract/All")
    String doTest1(@RequestParam("strs") String[] strings,@RequestParam("var1") int var1,@RequestParam("var2") int[] var2,@RequestParam("var3") boolean var3);


    /**
     * 3.普通模式的post传递参数
     * 特征:添加请求参数体
     * 运行结果:成功
     * Post获取单一参数对象  添加requestparam注解版本
     * 在post中不添加requestparam注解，单个参数不会导致无法解析，但不一定报错
     */
    @PostMapping("/ProductByAbstract/One")//必须添加@RequestParam注解
    String doTest2(@RequestParam String[] strings);

    /**
     * 4.普通模式的post传递参数
     * 特征:添加请求参数体
     * 运行结果:成功
     * Post获取多参数对象  添加requestparam注解版本
     * 在post中不添加requestparam注解在多参数情况下会直接报错:Method has too many Body parameters!
     */
    @PostMapping("/ProductByAbstract/All")//必须添加@RequestParam注解
    String doTest2(@RequestParam("strs") String[] strings,@RequestParam("var1")int var1,@RequestParam("var2") int[] var2,@RequestParam("var3")boolean var3);

    /**
     * 5.带实体类模式下的Post传递参数
     * 特征:混合post请求
     * 运行结果:成功
     */
    @PostMapping("/ProductByAbstract/AllByProc")//必须添加@RequestParam注解
    String doTest2(@RequestBody Product product, @RequestParam("strs") String[] strings, @RequestParam("var1")int var1, @RequestParam("var2") int[] var2, @RequestParam("var3")boolean var3);

}
