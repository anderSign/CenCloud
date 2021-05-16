package org.scCloud.controller;


import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.scCloud.feignclient.CategoryClientByAbstractObj;
import org.scCloud.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("CategoryByAbstract")
public class CategoryControllerByAbstract {

    //使用slf4j的日志
    private Logger logger=LoggerFactory.getLogger(CategoryControllerByAbstract.class);

    @Autowired
    private CategoryClientByAbstractObj categoryClientByAbstractObj;

    //数组对象
    private final String[] strings={"张三","李四","王五","赵六","田七"};

    //int对象
    private final int var1=15;

    //int数组对象
    private final int[] var2={1,3,5,7,9};

    //boolean对象
    private final boolean var3=true;

    //可以指定标记数来主导程序走不同的区间
    int input=5;

    //一个普通的实体对象
    private Product obj=new Product("天使","门珀","19",new Date(),"遥远世界最早期的神明，拥有无上的权力及智慧!");

    @GetMapping
    public String doTest(){
        logger.trace("现在已经进入测试阶段");
        logger.error("当前数组对象:[strings: {{}} ]",strings.toString());
        logger.debug("当前数字对象:[var1: {{}} ]",var1);
        logger.debug("当前布尔对象:[var2: {{}} ]",var2.toString());
        logger.debug("当前布尔对象:[var3: {{}} ]",var3);
        logger.debug("当前布尔对象:[obj: {{}} ]",obj);
        String value=null;
        switch (input){
            case 0:{//报错:Request method 'POST' not supported，原因:系统不知道这个对象是什么对象，是否需要转换成[url]/字段?ids=?&ids=?...这种对象
                value=categoryClientByAbstractObj.doTest0(strings);
                break;
            }
            case 1:{//不会报错
                value=categoryClientByAbstractObj.doTest1(strings);
                break;
            }
            case 2:{//不会报错数据正常传递
                value=categoryClientByAbstractObj.doTest1(strings,var1,var2,var3);
                break;
            }
            case 3:{//正常
                value=categoryClientByAbstractObj.doTest2(strings);
                break;
            }
            case 4:{//正常
                value=categoryClientByAbstractObj.doTest2(strings,var1,var2,var3);
                break;
            }
            case 5:{//正常
                value=categoryClientByAbstractObj.doTest2(obj,strings,var1,var2,var3);
                //转换成初始对象

                break;
            }
            case 6:{
                break;
            }
            default:{

            }
        }
        return value;
    }

}
