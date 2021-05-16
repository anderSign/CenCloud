package org.scCloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("Order")
public class OrderController {
    private static Logger logger= Logger.getLogger("OrderLogger");

    @GetMapping("/{name}")
    public String getTest(@PathVariable String name){
        logger.log(Level.SEVERE,"商品信息被调用! "+"["+name+"]","石块");
        return "调用了商品:"+"石块";
    }
}
