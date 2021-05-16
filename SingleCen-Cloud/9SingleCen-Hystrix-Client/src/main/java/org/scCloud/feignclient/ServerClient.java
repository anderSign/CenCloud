package org.scCloud.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 注意此接口虽然有写到但是并未真正使用发送请求放仍然是类别模块详见类别模块
 */
@FeignClient(value = "HystrixApplication",fallback = DefaultServerClient.class)//当目标不可用时转到自定义的类中去做处理。
public interface ServerClient {

    @GetMapping("/Hystrix")
    String doCallServer1(@RequestParam int id);

}
