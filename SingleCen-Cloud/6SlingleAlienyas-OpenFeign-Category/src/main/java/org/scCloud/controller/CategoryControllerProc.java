package org.scCloud.controller;


import com.alibaba.fastjson.JSONObject;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.scCloud.feignclient.CategoryClientProc;
import org.scCloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("CategoryByProc")
public class CategoryControllerProc {

    //使用slf4j的日志
    private Logger logger= LoggerFactory.getLogger(CategoryControllerByAbstract.class);

    //注入测试的实例对象
    @Autowired
    private CategoryClientProc categoryClientProc;

    //可以指定标记数来主导程序走不同的区间
    int input=3;

    @GetMapping
    public Object doTest(HttpServletRequest request, HttpServletResponse response){
        //X-Request-Id red X-Response-Host
        String red = request.getParameter("red");
        String requestHeader = request.getHeader("X-Request-Id");
        String responseHeader = response.getHeader("X-Response-Host");
        logger.debug("get配置文件的参数:{"+requestHeader+"}，{"+red+"},{"+responseHeader+"}");
        logger.debug("现在已经进入测试阶段注意debug测试的参数变化。。。。。。");
        Object var1=null;
        switch (input){
            case 0:{//正常
                var1=categoryClientProc.getTest1("19").toString();
                break;
            }
            case 1:{//报错  Cannot deserialize instance of `java.lang.String` out of START_ARRAY token
                var1=(Properties)categoryClientProc.getTest2("19");
                break;
            }
            case 2:{//正常
                var1=categoryClientProc.getTest3("19");
                break;
            }
            case 3:{//正常
                var1=categoryClientProc.getTest4("19");
                //反序列化,必须记下来
                JSONObject jsonObject0=JSONObject.parseObject((String) var1);
                List<Product> value = JSONObject.parseArray((String) jsonObject0.get("value").toString(), Product.class);
                break;
            }
            case 4:{//测试超时  无影响（老版本有影响的）
                var1=categoryClientProc.getTest4();
                break;
            }
            case 5:{//正常
                break;
            }
            case 6:{
                break;
            }
            default:{

            }
        }
        return var1;
    }

}
