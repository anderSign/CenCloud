package org.scCloud.controller;

import org.scCloud.feignclient.ProductClient;
import org.scCloud.pojo.Product;
import org.scCloud.vo.ProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 复杂对象处理
 */
@RestController
@RequestMapping("ProductByAbstract")
public class ProductControllerByAbstract {
    //slf4j另一个路径下的日志工厂
    private Logger logger= LoggerFactory.getLogger(ProductControllerByAbstract.class);

    //获取到当前的端口号
    @Value("${server.port}")
    private int port;

    //导入openfeign代理客户端  未使用
    @Autowired
    private ProductClient productClient;


    /**
     * get获取数组对象  不添加requestparam注解版本
     * 首先不添加requestparam注解的前提就是你的字段值必须对应，否则就会出现拿不到值的情况
     * @return
     */
    @GetMapping
    public String getTest(String[] strs){
        logger.info("打印获取到的值:{}", Arrays.toString(strs));
        return "调用成功，端口号:["+port+"]";
    }

    /**
     * get获取多参数对象  不添加requestparam注解版本
     * 首先不添加requestparam注解的前提就是你的字段值必须对应，否则就会出现拿不到值的情况
     * @return
     */
    @GetMapping("/All")
    public String getTest1(String[] strs,int var1,int[] var2,boolean var3){
        logger.info("打印获取到的值:strs= {}", Arrays.toString(strs));
        logger.info("打印获取到的值:var1= {}", var1);
        logger.info("打印获取到的值:var2= {}", Arrays.toString(var2));
        logger.info("打印获取到的值:var3= {}", var3);
        return "调用成功，端口号:["+port+"]";
    }

    /**
     * Post获取多参数对象  添加requestparam注解版本
     * 在post中获取对象时不添加requestparam注解，会获取不到值
     * 注意:当注解RequestParam中没有指定value的值的时候,那么你的字段名称必须和之前的对应否则就会出现拿不到值的情况
     * @return
     */
    @PostMapping("/One")
    public String getTest2(@RequestParam(value = "strings") String[] strs){
        logger.info("打印获取到的值:strs= {}", Arrays.toString(strs));
        return "调用成功，端口号:["+port+"]";
    }

    /**
     * Post获取多参数对象  添加requestparam注解版本
     * 在post中不添加requestparam注解，单个参数不会有影响，但是多参数会直接报错:Method has too many Body parameters!
     * @return
     */
    @PostMapping("/All")//注意前面的RequestParam一定不能忘记写，否则会直接报错:Method has too many Body parameters!
    public String getTest2(@RequestParam("strs") String[] strs, @RequestParam("var1")int var1, @RequestParam("var2") int[] var2, @RequestParam("var3")boolean var3){
        logger.info("打印获取到的值:strs= {}", Arrays.toString(strs));
        logger.info("打印获取到的值:var1= {}", var1);
        logger.info("打印获取到的值:var2= {}", Arrays.toString(var2));
        logger.info("打印获取到的值:var3= {}", var3);
        return "调用成功，端口号:["+port+"]";
    }

    /**
     * Patch获取多参数对象  添加requestparam和requestbody注解版本
     * @return
     */
    @PostMapping("/AllByProc")//注意前面的RequestParam和RequestBody一定不能忘记写，否则会直接报错
    public String getTest2(@RequestBody Product product, @RequestParam("strs") String[] strs, @RequestParam("var1")int var1, @RequestParam("var2") int[] var2, @RequestParam("var3")boolean var3){
        logger.info("打印获取到的值:proc= {}", product.toString());
        logger.info("打印获取到的值:strs= {}", Arrays.toString(strs));
        logger.info("打印获取到的值:var1= {}", var1);
        logger.info("打印获取到的值:var2= {}", Arrays.toString(var2));
        logger.info("打印获取到的值:var3= {}", var3);
        return "调用成功，端口号:["+port+"]";
    }

    /**
     * url传递list对象  可以不同添加任何注解  但是需要创建值对象(vo)
     * @return
     */
    //http://localhost:9607/ProductByAbstract/httpList?ids=1&ids=2&ids=3
    @GetMapping("/httpList")//需要注意的是当前端直接传递的是数组对象但是现在需要转换成list对象时需要注意的是list必须是list集合的实现类(set也是)
    public String getTest2(ProductVO ids){//不能直接接收集合类型的参数
        logger.info("打印获取到的值:list= {}", ids.toString());
        return "调用成功，端口号:["+port+"]";
    }

    /**
     * url传递list对象  可以不同添加任何注解  但是需要创建值对象(vo)
     * @return
     */
    //http://localhost:9607/ProductByAbstract/httpArray?ids=1&ids=2&ids=3
    @GetMapping("/httpArray")//需要注意的是当前端直接传递的是数组对象但是现在需要转换成list对象时需要注意的是list必须是list集合的实现类(set也是)
    public String getTest3(@RequestParam("ids") String[] ids){//不能直接接收集合类型的参数
        logger.info("打印获取到的值:list= {}", ids.toString());
        return "调用成功，端口号:["+port+"]";
    }

}
